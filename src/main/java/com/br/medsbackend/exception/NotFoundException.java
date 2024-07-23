package com.br.medsbackend.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String entity, String id) {
        super(String.format("The %s with %s was not found", entity, id));
    }

}
