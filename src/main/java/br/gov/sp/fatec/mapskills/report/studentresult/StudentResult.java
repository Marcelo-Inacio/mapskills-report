/*
 * @(#)StudentResult.java 1.0 1 21/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.studentresult;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;

import com.querydsl.core.annotations.QueryEntity;

import br.gov.sp.fatec.mapskills.report.studentresult.data.StudentResultData;
import lombok.Getter;

/**
 * A classe {@link StudentResult} representa um documento mongodb
 * com os dados de um aluno com seus resultados de cada competencia.
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
	private final Integer startYear;
	private final Integer startSemester;
	private final List<SkillResultIndicator> skillResultIndicators = new LinkedList<>();
	
	@SuppressWarnings("unused")
	private StudentResult() {
		this(null, null, null, null, null, null, null, null, null, null, Collections.emptyList());
	}
	
	public StudentResult(final Long id, final String ra, final String name,
			final String courseCode, final String courseName, final String institutionCode,
			final String institutionCompany, final String institutionLevel, final Integer startYear,
			final Integer startSemester, final List<SkillResultIndicator> skillResultIndicators) {
		this.id = id;
		this.ra = ra;
		this.name = name;
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.institutionCode = institutionCode;
		this.institutionCompany = institutionCompany;
		this.institutionLevel = institutionLevel;
		this.startYear = startYear;
		this.startSemester = startSemester;
		this.skillResultIndicators.addAll(CollectionUtils.isEmpty(skillResultIndicators) ? Collections.emptyList() : skillResultIndicators);		
	}
	
	public StudentResult(final StudentResultData data) {
		this(data.getStudentId(), data.getStudentRa(), data.getStudentName(),
				data.getCourseCode(), data.getCourseName(), data.getInstitutionCode(),
				data.getInstitutionCompany(), data.getInstitutionLevel().name(), data.getStartYear(),
				data.getStartSemester(), data.getSkillResultIndicators());
	}
	
	public List<SkillResultIndicator> getStudentIndicators() {
		return Collections.unmodifiableList(skillResultIndicators);
	}
}