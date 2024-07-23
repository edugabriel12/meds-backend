package com.br.medsbackend.exception;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException() {
        super();
    }

    public AlreadyExistsException(String entity, String idName, String id) {
        super(String.format("The %s with %s %s already exists", entity, idName, id));
    }

}
