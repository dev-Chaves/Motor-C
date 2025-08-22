package com.devchaves.backend.dto;

import com.devchaves.backend.entity.Anexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record CalculoRequest(

        @NotBlank
        @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "CNPJ inválido")
        String cnpj,

        @NotBlank
        @JsonFormat(pattern = "dd/MM/yyyy")
        @PastOrPresent(message = "A data deve ser no passado ou presente")
        LocalDate mesReferencia,
        @NotBlank(message = "RPA não pode ser vazio")
        String rpa,
        @NotBlank(message = "RBT12 não pode ser vazio")
        String rbt12,
        @NotBlank(message = "Anexo não pode ser vazio")
        Anexo anexo
) {
}
