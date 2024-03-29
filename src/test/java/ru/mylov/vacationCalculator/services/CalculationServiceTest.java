package ru.mylov.vacationCalculator.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CalculationServiceTest {
    private CalculationService calculationService;

    @BeforeEach
    void setUp() {
        calculationService = new CalculationService();
    }

    @Test
    void testCalculateVacationPay() {
        String salary = calculationService.calculateVacationPay(100000, "01.04.2023", "28.04.2023");
        Assertions.assertEquals("95563,14", salary);
    }

    @Test
    void testHolidayCheck() {
        int holidaysCounter = calculationService.holidayCheck(LocalDate.of(2024, 3, 8));
        Assertions.assertEquals(1, holidaysCounter);
    }
}
