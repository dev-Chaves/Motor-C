package com.devchaves.backend.dto;

import java.math.BigDecimal;

public record CalculoResponse(
        BigDecimal response,
        String data
) {
}
