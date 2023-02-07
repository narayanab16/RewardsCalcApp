package com.narayana.pointsapp.db;

import com.narayana.pointsapp.constants.AppConstants;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerMain {
    public static void main(String[] args) {
        Customer c1 = new Customer("Narayana", LocalDate.of(2022,1,1), 122.45 );
        Customer c2 = new Customer("Basetty", LocalDate.of(2022,1,1), 52.14 );
        System.out.println(c1.name());
        System.out.println(c1.txDate());
        System.out.println(c1.spendAmount());
        //
        LocalDate startDate = LocalDate.of(2022, 01,06);
        int monthsPeriod = 3;
        System.out.println("---> " + startDate.plusMonths(monthsPeriod-1));
        //LocalDate _3months = startDate.plusMonths(monthsPeriod - 1);
        List<LocalDate> dates = new ArrayList<>();
        Map<String, List<LocalDate>> map = new HashMap<>();
        for (int i = 1; i <= monthsPeriod; i++) {
             LocalDate nextMonthDt = startDate.plusMonths(1);
            while (startDate.isBefore(nextMonthDt)) {
                dates.add(startDate);
                startDate = startDate.plusDays(1);
            }
            map.put(i+"", dates);
            dates = new ArrayList<>();
        }
        System.out.println("---> " + map);
        List<Customer> customers = CustomerDB.getCustomers(null, null);
        final Map<String, Double> custTotRwdsMap = new HashMap<>();
        final Map<Integer, Map<String, Double>> monthCustTotRwdsMap = new HashMap<>();
        double spentOver1 = 100;
        double spentOver2 = 50;
        int perAge1 = 2;
        int perAge2 = 1;
        customers.stream().forEach(x -> {
            double totalRewards = 0;
            int month = x.txDate().getMonth().getValue();
            String name = x.name();
            if(monthCustTotRwdsMap.isEmpty()) {
                if(x.spendAmount() > spentOver1) {
                    totalRewards = (Math.ceil(x.spendAmount()) - spentOver1) * perAge1;
                    totalRewards = totalRewards + spentOver2 * perAge2;
                } else {
                    if(x.spendAmount() > spentOver2 && x.spendAmount() < spentOver1) {
                        totalRewards = Double.valueOf(spentOver2 * perAge2);
                    }
                }
                custTotRwdsMap.put(x.name(), totalRewards);
                monthCustTotRwdsMap.put(month, custTotRwdsMap);
            } else {
                if(monthCustTotRwdsMap.containsKey(month)) {
                    Map<String, Double> tmpCustTotRwdsMap = monthCustTotRwdsMap.get(month);
                    if (tmpCustTotRwdsMap.containsKey(name)) {

                    } else {
                        Map<String, Double> tmpCstTotRwdsMap = new HashMap<>();
                    }
                }
            }
        });





        /*String strDate1 = "2022-01-01";
        String strDate2 = "2022-03-31";
        LocalDate startDate = LocalDate.parse(strDate1);
        LocalDate endDate = LocalDate.parse(strDate2);
        System.out.println(" " + startDate + " , " + endDate + ", " + startDate.datesUntil(endDate).map(x -> x).collect(Collectors.toList()));
        LocalDate date = startDate; //LocalDate.now().withDayOfMonth(1);
        LocalDate end = date.plusMonths(2);
        System.out.println(" end : " + end);
        List<LocalDate> dates = new ArrayList<>();
        while(date.isBefore(end)) {
            dates.add(date);
            date = date.plusDays(1);
        }
        System.out.println(" " + dates);

        LocalDate bday = startDate;//LocalDate.of(1955, Month.MAY, 19);
        LocalDate today = endDate;//LocalDate.now();
        Period age = Period.between(bday, today);
        int years = age.getYears();
        int months = age.getMonths();
        System.out.println("number of years: " + years);
        System.out.println("number of months: " + months);
*/
    }
}
