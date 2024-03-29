package ru.mylov.vacationCalculator.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class ResponseMessage {
    private final String responseMessageText;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private final LocalDateTime time;

    @Autowired
    public ResponseMessage(String responseMessage, LocalDateTime time) {
        this.responseMessageText = responseMessage;
        this.time = time;
    }

    public String getResponseMessageText() {
        return responseMessageText;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
