package com.smartfleet.vehicle_service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ErrorResponse {
    LocalDateTime timeStamp;
    String message;
    int Status;
    String path;

}
