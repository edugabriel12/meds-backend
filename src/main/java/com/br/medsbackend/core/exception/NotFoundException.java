package com.br.medsbackend.core.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String entity, String id) {
        super(String.format("The %s with %s was not found", entity, id));
    }

}
