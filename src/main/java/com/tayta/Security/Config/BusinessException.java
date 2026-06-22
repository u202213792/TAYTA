package com.tayta.Security.Config;

/** Error de regla de negocio (se traduce a HTTP 400 con su mensaje). */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
