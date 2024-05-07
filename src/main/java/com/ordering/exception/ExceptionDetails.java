package com.ordering.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
public class ExceptionDetails {
    private LocalDateTime timeStamp;
    private String message;
    private String details;

}
