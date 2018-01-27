/*
 * @(#)StudentResultRetriever.java 1.0 1 26/01/2018
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.studentresult.data;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.gov.sp.fatec.mapskills.report.studentresult.StudentResult;
import lombok.AllArgsConstructor;

/**
 * A classe {@link StudentResultDataRetriever} e responsavel
 * por retornar os dados de {@link StudentResultData} adaptados
 * para {@link StudentResult}.
 *
 * @author Marcelo
 * @version 1.0 26/01/2018
 */
@Component
@AllArgsConstructor
public class StudentResultDataRetriever {
	
	private final StudentResultDataRepository repository;
	
	public List<StudentResult> retrieve() {
		final List<StudentResultData> studentsResultData = repository.findAll();
		return studentsResultData.parallelStream().map(StudentResult::new).collect(Collectors.toList());
	}
}