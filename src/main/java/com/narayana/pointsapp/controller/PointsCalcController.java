package com.narayana.pointsapp.controller;

import com.narayana.pointsapp.service.PointsCalcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/points-api")
public class PointsCalcController {
    private Logger LOG = LoggerFactory.getLogger(PointsCalcController.class);

    @Autowired
    PointsCalcService pointsCalcService;

    @RequestMapping(value = "/calcRewards", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object calcRewards(@RequestParam(name = "startDate") String strStartDate, Integer monthPeriod) {
        LOG.debug("startDate " + strStartDate + ", monthPeriod : " + monthPeriod);
        LocalDate startDate = LocalDate.parse(strStartDate);
        return pointsCalcService.calcRewards(startDate, monthPeriod);
    }
}
