package com.narayana.pointsapp;

import com.narayana.pointsapp.controller.PointsCalcController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PointsappApplicationTests {
	@Autowired
	private PointsCalcController pointsCalcController;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(pointsCalcController);
	}

}
