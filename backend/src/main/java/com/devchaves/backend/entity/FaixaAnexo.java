package com.devchaves.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaixaAnexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anexo_id", nullable = false)
    private Anexo anexo;

    @Column(name = "receita_bruta_min", nullable = false, precision = 19, scale = 2)
    private BigDecimal receitaBrutaMin;

    @Column(name = "aliquota_nominal", nullable = false, precision = 19, scale = 4)
    private BigDecimal aliquotaNominal;

    @Column(name = "data_inicio_vigencia", nullable = false)
    private LocalDate dataInicioVigencia;

    // Pode ser nulla para testes/e até sair realmente uma data de vigência
    @Column(name = "data_fim_vigencia", nullable = true)
    private LocalDate dataFimVigencia;



}
