package ru.mylov.vacationCalculator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.mylov.vacationCalculator.utils.ResponseMessage;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = WrongVacationPeriodException.class)
    public ResponseEntity<Object> handleWrongVacationPeriodException(WrongVacationPeriodException e) {
        ResponseMessage responseMessage = new ResponseMessage(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmptyVacationPeriodException.class)
    public ResponseEntity<Object> handleEmptyVacationPeriodException(EmptyVacationPeriodException e) {
        ResponseMessage responseMessage = new ResponseMessage(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = WrongDateFormatException.class)
    public ResponseEntity<Object> handleWrongDateFormatException(WrongDateFormatException e) {
        ResponseMessage responseMessage = new ResponseMessage(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }
}
