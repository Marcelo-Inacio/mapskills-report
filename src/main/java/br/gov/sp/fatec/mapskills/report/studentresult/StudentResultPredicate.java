/*
 * @(#)StudentResultPredicate.java 1.0 1 05/11/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.studentresult;

import com.querydsl.core.types.Predicate;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A classe {@link StudentResultPredicate} encapsula
 * um <code>Preidcate</code> para pesquisa de resultados
 * de alunos.
 *
 * @author Marcelo
 * @version 1.0 05/11/2017
 */
@Getter
@AllArgsConstructor
public class StudentResultPredicate {	
	private final Predicate predicate;
}