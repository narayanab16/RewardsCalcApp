package com.narayana.pointsapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class HttpRestEndpointTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Value("${server.port}")
    private int port;

    @Test
    public void noValidInputRewardCalc() {
        String text = this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class);
        Assertions.assertEquals("An error occurred, we will fix soon", text);
    }
    @Test
    public void validateCustomerCount() {
        Map<String, Map<Integer, Double>> map = this.restTemplate.getForObject("http://localhost:" + port +
                        "/points-api/calcRewards?startDate=2022-01-01&monthPeriod=3",
                Map.class);
        System.out.println(" map-- " + map.size());
        System.out.println(" map-- " + map);
        Assertions.assertEquals(2, map.size());
    }
}
