package ru.mylov.vacationCalculator.exceptions;

import org.springframework.beans.factory.annotation.Autowired;

public class WrongVacationPeriodException extends RuntimeException {
    private final String message;

    @Autowired
    public WrongVacationPeriodException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
