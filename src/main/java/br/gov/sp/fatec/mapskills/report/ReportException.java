/*
 * @(#)ReportException.java 1.0 1 28/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A classe {@link ReportException} representa
 * error ocorridos dentro da aplicacao.
 *
 * @author Marcelo
 * @version 1.0 28/10/2017
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ReportException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ReportException(final String message) {
		super(message);
	}
	
	public ReportException(final String message, final Throwable cause) {
		super(message, cause);
	}
}