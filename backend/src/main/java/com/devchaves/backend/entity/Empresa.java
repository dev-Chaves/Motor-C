package com.devchaves.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String cnpj;

    @Enumerated(EnumType.STRING)
    private Anexo anexoPadrao;

    public Empresa(String cnpj, Anexo anexoPadrao) {
        this.cnpj = cnpj;
        this.anexoPadrao = anexoPadrao;
    }
}
