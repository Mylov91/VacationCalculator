package ru.mylov.vacationCalculator.controllers;

import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mylov.vacationCalculator.services.CalculationService;

@RestController
@Validated
public class CalculationController {
    private final CalculationService calculationService;

    @Autowired
    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam("salary") @Min(0) int averageSalary,
                            @RequestParam("start") String startDate,
                            @RequestParam("end") String endDate) {
        return calculationService.calculateVacationPay(averageSalary, startDate, endDate);
    }
}
