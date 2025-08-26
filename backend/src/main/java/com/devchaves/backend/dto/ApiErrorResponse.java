package com.devchaves.backend.dto;

public record ApiErrorResponse(
        int status,
        String message,
        Long timeStamp
) {
}
