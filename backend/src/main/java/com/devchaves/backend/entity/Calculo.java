package com.devchaves.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "calculo_historico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    // Link para a regra EXATA que foi usada neste cálculo.
    // Isso garante a rastreabilidade e a auditoria perfeitas.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faixa_anexo_id", nullable = false)
    private FaixaAnexo faixaUtilizada;

    // Data em que o cálculo foi realizado no sistema.
    @Column(name = "data_calculo", nullable = false)
    private LocalDateTime dataCalculo;

    // Mês de referência para o qual o imposto foi calculado.
    @Column(name = "mes_referencia", nullable = false)
    private LocalDate mesReferencia;

    //Receita do Período de Apuração
    @Column(name = "rpa", nullable = false, precision = 19, scale = 2)
    private BigDecimal rpa;

    //Receita Bruta dos Últimos 12 Meses
    @Column(name = "rbt_12", nullable = false, precision = 19, scale = 2)
    private BigDecimal rbt12;

    // --- Resultados

    @Column(name = "aliquota_efetiva", nullable = false, precision = 19, scale = 2)
    private BigDecimal aliquotaEfetiva;

    @Column(name = "valor_das", nullable = false, precision = 19, scale = 2)
    private BigDecimal valorDas;


}
