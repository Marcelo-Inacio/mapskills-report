/*
 * @(#)RestExceptionHandler.java 1.0 1 28/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.gov.sp.fatec.mapskills.report.ReportException;

/**
 * A classe {@link RestExceptionHandler} e responsavel
 * por interceptar uma excecao lancada pela aplicacao,
 * retornando-a formatada no corpo da resposta.
 *
 * @author Marcelo
 * @version 1.0 28/10/2017
 */
@RestControllerAdvice
public class RestExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	@ExceptionHandler(value = { ReportException.class })
    protected ResponseEntity<ErrorResponse> handle(final RuntimeException ex) {
		final ResponseStatus status = ex.getClass().getAnnotation(ResponseStatus.class);
        final ErrorResponse responseBody = new ErrorResponse(status.value(), ex.getMessage());
        logger.error("Aconteceu um problema de dominio", ex);
		return new ResponseEntity<>(responseBody, status.value());
    }
	
	@ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<ErrorResponse> handle(final Exception ex) {
        final ErrorResponse responseBody = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        logger.error("Aconteceu um problema que não de dominio", ex);
		return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}