/*
 * @(#)StudentResultPageWrapper.java 1.0 1 28/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi.wrapper;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.sp.fatec.mapskills.report.restapi.serializer.StudentResultPageSerializer;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResult;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResultIndicator;
import lombok.Getter;

/**
 * A classe {@link StudentResultPageWrapper} encapsula
 * uma lista paginada de <code>StudentResult</code>
 * para serializacao.
 *
 * @author Marcelo Inacio
 * @version 1.0 28/10/2017
 */
@Getter
@JsonSerialize(using = StudentResultPageSerializer.class)
public class StudentResultPageWrapper {

	private final Page<StudentResult> studentsReport;
	
	public StudentResultPageWrapper(final Page<StudentResult> studentsReport) {
		this.studentsReport = studentsReport;
	}
	
	public List<StudentResult> getContent() {
		return Collections.unmodifiableList(studentsReport.getContent());
	}
	
	public int getRemaningPages() {
		return studentsReport.getTotalPages() - studentsReport.getNumber() - 1;
	}
	
	public int getNumberOfElements() {
		return studentsReport.getNumberOfElements();
	}
	
	public int getTotalPages() {
		return studentsReport.getTotalPages();
	}
	
	public int getCurrentPageNumber() {
		return studentsReport.getNumber();
	}
	
	public List<StudentResultIndicator> getFirstStudentIndicators() {
		final List<StudentResult> list = getContent();
		return CollectionUtils.isEmpty(list) ? Collections.emptyList() : list.get(0).getStudentIndicators();
	}
}