/*
 * @(#)StudentResultData.java 1.0 18/03/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.studentresult.data;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import br.gov.sp.fatec.mapskills.report.studentresult.StudentResultIndicator;
import lombok.Getter;

/**
 * 
 * A classe {@link StudentResultData} representa a sumarizacao
 * dos resultados apurados das competencias avaliadas pelo
 * aluno.
 *
 * @author Marcelo
 * @version 1.0 18/03/2017
 */
@Getter
@Entity
@Table(name = "MAPSKILLS.CSV_REPORT_VIEW")
public class StudentResultData {

	@Id
	@Column(name = "STUDENT_ID")
	private final Long studentId;
	
	@Column(name = "STUDENT_RA")
	private final String studentRA;
	
	@Column(name = "STUDENT_NAME")
	private final String studentName;
	
	@Column(name = "COURSE_CODE")
	private final String courseCode;
	
	@Column(name = "COURSE_NAME")
	private final String courseName;
	
	@Column(name = "INSTITUTION_CODE")
	private final String institutionCode;
	
	@Column(name = "INSTITUTION_COMPANY")
	private final String institutionCompany;
	
	@Enumerated
	@Column(name = "INSTITUTION_LEVEL")
	private final InstitutionLevel institutionLevel;
	
	@Column(name = "START_YEAR")
	private final Integer startYear;
	
	@Column(name = "START_SEMESTER")
	private final Integer startSemester;
	
	@OneToMany
	@JoinColumn(name = "ID_STUDENT")
	@OrderBy("skillName")
	private final List<StudentResultIndicatorData> studentIndicators = new LinkedList<>();
	
	private StudentResultData() {
		this.studentId = null;
		this.studentRA = null;
		this.studentName = null;
		this.courseName = null;
		this.institutionCode = null;
		this.institutionCompany = null;
		this.institutionLevel = null;
		this.startYear = null;
		this.startSemester = null;
		this.courseCode = null;
	}
	
	public List<StudentResultIndicator> getStudentResultIndicator() {
		return studentIndicators.parallelStream()
					.map(StudentResultIndicator::new).collect(Collectors.toList());
	}
}