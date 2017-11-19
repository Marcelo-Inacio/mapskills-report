/*
 * @(#)InstitutionStudentsIndicator.java 1.0 1 17/09/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.indicator.institution;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Getter;

/**
 * A classe {@link StudentsIndicatorByInstitution} representa
 * indicador do progresso dos alunos por instituicao de ensino.
 *
 * @author Marcelo
 * @version 1.0 17/09/2017
 */
@Getter
@Entity
@Immutable
@Table(name = "MAPSKILLS.ADMIN_LEVEL_STUDENTS_PROGRESS_VIEW")
public class StudentsIndicatorByInstitution {
	
	@Id
	@Column(name = "ID")
	private final Long id;
	
	@Column(name = "START_YEAR")
	private final Integer startYear;
	
	@Column(name = "START_SEMESTER")
	private final Integer startSemester;
	
	@Column(name = "CODE")
	private final String institutionCode;
	
	@Column(name = "LEVEL")
	private final Integer level;
	
	@Column(name = "COMPANY")
	private final String institutionName;
	
	@Column(name = "NOT_FINALIZED")
	private final Double notFinalized;
	
	@Column(name = "FINALIZED")
	private final Double finalized;
	
	@Column(name = "TOTAL")
	private final Integer total;	
	
	private StudentsIndicatorByInstitution() {
		this.id = null;
		this.startYear = null;
		this.startSemester = null;
		this.institutionCode = null;
		this.level = null;
		this.institutionName = null;
		this.notFinalized = null;
		this.finalized = null;
		this.total = null;
	}
}