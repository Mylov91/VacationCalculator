package ru.mylov.vacationCalculator.exceptions;

import org.springframework.beans.factory.annotation.Autowired;

public class EmptyVacationPeriodException extends RuntimeException {
    private final String message;

    @Autowired
    public EmptyVacationPeriodException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
