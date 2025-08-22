package com.devchaves.backend.entity;

public enum Anexo {
    ANEXO_1(1),
    ANEXO_2(2),
    ANEXO_3(3),
    ANEXO_4(4),
    ANEXO_5(5);

    private final int valor;

    Anexo(int valor) {
        this.valor = valor;
    }

    public static Anexo fromValor(int valor) {
        for (Anexo anexo : Anexo.values()) {
            if (anexo.valor == valor) return anexo;
        }

        throw new IllegalArgumentException("Valor inválido para Anexo");
    }

}
