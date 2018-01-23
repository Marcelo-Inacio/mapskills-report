/*
 * @(#)StudentResultListWrapper.java 1.0 1 23/01/2018
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi.wrapper;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.gov.sp.fatec.mapskills.report.restapi.serializer.StudentResultListDeserializer;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResult;

/**
 * A classe {@link StudentResultListWrapper}
 *
 * @author Marcelo
 * @version 1.0 23/01/2018
 */
@JsonDeserialize(using = StudentResultListDeserializer.class)
public class StudentResultListWrapper {
	
	private final List<StudentResult> studentResults = new LinkedList<>();
	
	public StudentResultListWrapper(final List<StudentResult> studentResults) {
		this.studentResults.addAll(studentResults);
	}
	
	public List<StudentResult> getResults() {
		return Collections.unmodifiableList(studentResults);
	}
}