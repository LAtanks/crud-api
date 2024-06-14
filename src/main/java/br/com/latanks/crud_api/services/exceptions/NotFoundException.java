package br.com.latanks.crud_api.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.persistence.EntityNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends EntityNotFoundException {
    public NotFoundException(String message) {
        super((String) message, null);
    }

    public NotFoundException() {
        super();
    }
}
