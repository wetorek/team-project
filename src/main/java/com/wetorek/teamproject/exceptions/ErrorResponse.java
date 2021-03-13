package com.wetorek.teamproject.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    String errorCode;
    String errorMessage;
    int status;
    LocalDateTime timestamp;
}
