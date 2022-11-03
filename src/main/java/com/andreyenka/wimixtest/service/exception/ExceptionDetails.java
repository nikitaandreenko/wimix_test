package com.andreyenka.wimixtest.service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionDetails {
    private Date dateTime;
    private String message;
    private String details;
}
