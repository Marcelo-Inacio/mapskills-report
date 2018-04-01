/*
 * @(#)InstitutionLevel.java 1.0 26/02/2017
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved. Fatec Jessen Vidal
 * proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.indicator;

/**
 * A enum {@link InstitutionLevel} representa
 * os niveis de grau que uma instituicao pode assumir.
 *
 * @author Marcelo
 * @version 1.0 26/02/2017
 */
public enum InstitutionLevel {
	
	TECHNICAL, SUPERIOR;
	
	public static InstitutionLevel withLevel(final String level) {
		return level != null && level.equalsIgnoreCase("SUPERIOR") ? SUPERIOR : TECHNICAL;
	}	
}