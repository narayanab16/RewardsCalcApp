package com.narayana.pointsapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "app")
public class PointsCalcConfig {
    private Map<String, Integer> calcConfigData;

    public Map<String, Integer> getCalcConfigData() {
        return calcConfigData;
    }

    public void setCalcConfigData(Map<String, Integer> calcConfigData) {
        this.calcConfigData = calcConfigData;
    }
}
