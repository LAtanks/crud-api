package br.com.latanks.crud_api.services.exceptions;

public class DataInvalidationException extends RuntimeException{
    public DataInvalidationException() {
        super("Dado inválido");
    }

    public DataInvalidationException(String message) {
        super(message);
    }
}
