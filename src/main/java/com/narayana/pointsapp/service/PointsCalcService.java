package com.narayana.pointsapp.service;

import com.narayana.pointsapp.config.PointsCalcConfig;
import com.narayana.pointsapp.constants.AppConstants;
import com.narayana.pointsapp.db.Customer;
import com.narayana.pointsapp.db.CustomerDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PointsCalcService {
    private Logger LOG = LoggerFactory.getLogger(PointsCalcService.class);

    @Autowired
    PointsCalcConfig appConfigData;
      public Map<String, Map<Integer, Double>> calcRewards(final LocalDate startDate, Integer monthPeriod) {
        LOG.debug("app config map " + appConfigData.getCalcConfigData());
        LocalDate tmpDate = startDate.plusMonths(monthPeriod - 1);
        LocalDate endDate = YearMonth.of(tmpDate.getYear(), tmpDate.getMonth()).atEndOfMonth();
        Double spentOver1 = Double.valueOf(appConfigData.getCalcConfigData().get(AppConstants.SPEND_OVER1));
        Double spentOver2 = Double.valueOf(appConfigData.getCalcConfigData().get(AppConstants.SPEND_OVER2));
        List<Customer> customers = CustomerDB.getCustomers(startDate, endDate);
        //Map<String, List<LocalDate>> monthListDatesMap = getDatesForPeriodOfMonths(startDate, 3);
        Map<Integer, Double> monthTotRwdsMap = new HashMap<>();
        Map<String, Map<Integer, Double>> custMonthTotRwdsMap = new HashMap<>();
        customers.stream().forEach( cust -> {
            if(custMonthTotRwdsMap.isEmpty()) {
                monthTotRwdsMap.put(cust.txDate().getMonth().getValue(), calcRewards(cust.spendAmount(), spentOver1, spentOver2));
                custMonthTotRwdsMap.put(cust.name(), monthTotRwdsMap);
            } else {
                if(custMonthTotRwdsMap.containsKey(cust.name())) {
                    Map<Integer, Double> tmpMonthTotRwdsMap = custMonthTotRwdsMap.get(cust.name());
                    Integer monthKey = cust.txDate().getMonth().getValue();
                    Double totalRwds = 0.0;
                    if(tmpMonthTotRwdsMap.containsKey(monthKey)) {
                        totalRwds = tmpMonthTotRwdsMap.get(monthKey);
                        totalRwds = totalRwds + calcRewards(cust.spendAmount(), spentOver1, spentOver2);
                        tmpMonthTotRwdsMap.put(monthKey, totalRwds);
                    } else {
                        totalRwds = totalRwds + calcRewards(cust.spendAmount(), spentOver1, spentOver2);
                        tmpMonthTotRwdsMap.put(monthKey, totalRwds);
                    }
                    custMonthTotRwdsMap.put(cust.name(), tmpMonthTotRwdsMap);
                } else {
                    Map<Integer, Double> tmpMonthTotRwdsMap = new HashMap<>();
                    tmpMonthTotRwdsMap.put(cust.txDate().getMonth().getValue(), calcRewards(cust.spendAmount(), spentOver1, spentOver2));
                    custMonthTotRwdsMap.put(cust.name(), tmpMonthTotRwdsMap);
                }
            }
        });
        LOG.debug(" Total customer rewarded : " + custMonthTotRwdsMap.size());
        return custMonthTotRwdsMap;
    }

    private Double calcRewards(Double spendAmount, Double spentOver1, Double spentOver2) {
          Double totalRewards = 0.0;
        if(spendAmount > spentOver1) {
            totalRewards = (Math.ceil(spendAmount) - spentOver1) * appConfigData.getCalcConfigData().get(AppConstants.PER_AGE1);
            totalRewards = totalRewards + Double.valueOf(spentOver2 * appConfigData.getCalcConfigData().get(AppConstants.PER_AGE2));
        } else {
            if(spendAmount > spentOver2 && spendAmount < spentOver1) {
                totalRewards = Double.valueOf(spentOver2 * appConfigData.getCalcConfigData().get(AppConstants.PER_AGE2));
            }
        }
        return totalRewards;
    }

    public Map<String, List<LocalDate>> getDatesForPeriodOfMonths(LocalDate startDate, int periodOfMonths) {
        List<LocalDate> datesList = new ArrayList<>();
        Map<String, List<LocalDate>> monthListDatesMap = new HashMap<>();
        for (int i = 1; i <= periodOfMonths; i++) {
            LocalDate nextMonthDt = startDate.plusMonths(1);
            while (startDate.isBefore(nextMonthDt)) {
                datesList.add(startDate);
                startDate = startDate.plusDays(1);
            }
            monthListDatesMap.put(i+"", datesList);
            datesList = new ArrayList<>();
        }
        return monthListDatesMap;
    }

    public List<LocalDate> getMonthCalDates(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> datesList = startDate.datesUntil(endDate).collect(Collectors.toList());
        datesList.add(endDate);
        return datesList;
    }

}
