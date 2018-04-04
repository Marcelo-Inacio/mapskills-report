/*
 * @(#)StudentResultRepository.java 1.0 1 27/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.studentresult;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.querydsl.core.types.Predicate;

/**
 * A classe {@link StudentResultRepository} possui metodos
 * para acesso aos documentos de <code>StudentReport</code>.
 *
 * @author Marcelo
 * @version 1.0 27/10/2017
 */
public interface StudentResultRepository extends MongoRepository<StudentResult, String>,
		QueryDslPredicateExecutor<StudentResult> {
	
	List<StudentResult> findAll(final Predicate predicate);
	StudentResult findById(final Long studentId);
}