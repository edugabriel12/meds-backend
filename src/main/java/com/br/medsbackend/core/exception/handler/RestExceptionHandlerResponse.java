package com.br.medsbackend.core.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestExceptionHandlerResponse {

    private String httpStatusMessage;
    private int HttpStatusCode;
    private String message;
}
