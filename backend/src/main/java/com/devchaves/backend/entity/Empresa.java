package com.devchaves.backend.entity;

import jakarta.persistence.*;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cnpj;

    private String razaoSocial;

    @Enumerated(EnumType.STRING)
    private Anexo anexoPadrao;

}
