package com.devchaves.backend.controller;

import com.devchaves.backend.dto.CalculoRequest;
import com.devchaves.backend.service.CalculoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("calculos")
public class CalculosController {

    private final CalculoService calculoService;

    public CalculosController(CalculoService calculoService) {
        this.calculoService = calculoService;
    }

    @PostMapping("das")
    public ResponseEntity<BigDecimal> calculoDASPreReforma(@Valid @RequestBody CalculoRequest dto){

        return ResponseEntity.ok().body(calculoService.calculoDasModeloPreReforma(dto));

    }

}
