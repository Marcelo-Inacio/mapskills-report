/*
 * @(#)StudentsIndicatorByCourse.java 1.0 1 17/09/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.indicator.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.gov.sp.fatec.mapskills.report.indicator.InstitutionLevel;
import lombok.Getter;

/**
 * A classe {@link StudentsIndicatorByCourse} representa
 * os indicadores dos alunos por curso de uma instituicao.
 * 
 * Na pratica contem a quantidade de alunos que finalizaram e
 * nao finalizaram o jogo de um determinado curso de uma determinada
 * instituicao.
 *
 * @author Marcelo
 * @version 1.0 17/09/2017
 */
@Getter
@Entity
@Table(name = "MAPSKILLS.INSTITUTION_STUDENTS_PROGRESS_VIEW")
public class StudentsIndicatorByCourse {
	
	@Id
	@Column(name = "ID")
	private final String id;
	
	@Column(name = "START_YEAR")
	private final Integer startYear;
	
	@Column(name = "START_SEMESTER")
	private final Integer startSemester;
	
	@Column(name = "INSTITUTION_CODE")
	private final String institutionCode;
	
	@Enumerated
	@Column(name = "INSTITUTION_LEVEL")
	private final InstitutionLevel institutionLevel;
	
	@Column(name = "COURSE_CODE")
	private final String courseCode;
	
	@Column(name = "COURSE_NAME")
	private final String courseName;
	
	@Column(name = "NOT_FINALIZED")
	private final Integer notFinalized;
	
	@Column(name = "FINALIZED")
	private final Integer finalized;
	
	private StudentsIndicatorByCourse() {
		this.id = null;
		this.startYear = null;
		this.startSemester = null;
		this.institutionCode = null;
		this.institutionLevel = null;
		this.courseCode = null;
		this.courseName = null;
		this.notFinalized = null;
		this.finalized = null;
	}
}