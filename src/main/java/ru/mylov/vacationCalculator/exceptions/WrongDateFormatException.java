package ru.mylov.vacationCalculator.exceptions;

import org.springframework.beans.factory.annotation.Autowired;

public class WrongDateFormatException extends RuntimeException {
    private final String message;

    @Autowired
    public WrongDateFormatException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
