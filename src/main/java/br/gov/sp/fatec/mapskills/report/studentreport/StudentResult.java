/*
 * @(#)CsvReport.java 1.0 1 21/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.studentreport;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;

import com.querydsl.core.annotations.QueryEntity;

import lombok.Getter;

/**
 * A classe {@link StudentResult}
 *
 * @author Marcelo
 * @version 1.0 21/10/2017
 */
@Getter
@QueryEntity
@Document
public class StudentResult {
	
	@Id
	private String documentId;
		
	private final Long id;
	private final String ra;
	private final String name;
	private final String courseCode;
	private final String courseName;
	private final String institutionCode;
	private final String institutionCompany;
	private final String institutionLevel;
	private final String yearSemester;
	private final List<StudentResultIndicator> studentIndicators = new LinkedList<>();
	
	public StudentResult(final Long id, final String ra, final String name,
			final String courseCode, final String courseName, final String institutionCode,
			final String institutionCompany, final String institutionLevel,
			final String yearSemester, final List<StudentResultIndicator> studentIndicators) {
		this.id = id;
		this.ra = ra;
		this.name = name;
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.institutionCode = institutionCode;
		this.institutionCompany = institutionCompany;
		this.institutionLevel = institutionLevel;
		this.yearSemester = yearSemester;
		this.studentIndicators.addAll(CollectionUtils.isEmpty(studentIndicators) ? Collections.emptyList() : studentIndicators);		
	}
	
	public List<StudentResultIndicator> getStudentIndicators() {
		return Collections.unmodifiableList(studentIndicators);
	}
}