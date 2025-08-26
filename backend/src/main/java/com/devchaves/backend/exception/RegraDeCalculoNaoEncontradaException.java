package com.devchaves.backend.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class RegraDeCalculoNaoEncontradaException extends RuntimeException {
    public RegraDeCalculoNaoEncontradaException(String message) {
        super(message);
    }
}
