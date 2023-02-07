package com.narayana.pointsapp.db;

import java.time.LocalDate;

public record Customer(String name, LocalDate txDate, Double spendAmount) { }
