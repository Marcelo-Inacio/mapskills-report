/*
 * @(#)StudentResultIndicator.java 1.0 1 21/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.studentresult;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A classe {@link StudentResultIndicator}
 *
 * @author Marcelo
 * @version 1.0 21/10/2017
 */
@Getter
@AllArgsConstructor
public class StudentResultIndicator {
	
	private final String skillName;
	private final String skillDescription;
	private final Integer total;
}