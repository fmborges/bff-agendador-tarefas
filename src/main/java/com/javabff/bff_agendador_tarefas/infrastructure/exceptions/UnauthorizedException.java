package com.javabff.bff_agendador_tarefas.infrastructure.exceptions;

import javax.naming.AuthenticationException;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
    public UnauthorizedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
