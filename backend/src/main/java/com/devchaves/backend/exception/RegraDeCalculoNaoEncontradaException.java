package com.devchaves.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegraDeCalculoNaoEncontrada extends RuntimeException {
    public RegraDeCalculoNaoEncontrada(String message) {
        super(message);
    }
}
