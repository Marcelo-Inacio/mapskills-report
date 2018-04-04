/*
 * @(#)StudentsIndicatorByInstitutionLevel.java 1.0 1 17/09/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.indicator.institutionlevel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import br.gov.sp.fatec.mapskills.report.indicator.InstitutionLevel;
import lombok.Getter;

/**
 * A classe {@link StudentsIndicatorByInstitutionLevel} representa
 * indicador sobre o progresso dos alunos pelo grau da instituicao.
 * Ex.: Tecnico ou Superior.
 * 
 * Na pratica contem valores sobre a quantidade de alunos de finalizaram
 * e nao finalizaram o jogo de maneira geral.
 *
 * @author Marcelo
 * @version 1.0 17/09/2017
 */
@Getter
@Entity
@Immutable
@Table(name = "MAPSKILLS.ADMIN_GLOBAL_STUDENTS_PROGRESS_VIEW")
public class StudentsIndicatorByInstitutionLevel {
	
	@Id
	@Column(name = "ID")
	private final String id;
	
	@Column(name = "START_YEAR")
	private final Integer startYear;
	
	@Column(name = "START_SEMESTER")
	private final Integer startSemester;
	
	@Enumerated
	@Column(name = "LEVEL")
	private final InstitutionLevel level;
	
	@Column(name = "NOT_FINALIZED")
	private final Integer notFinalized;
	
	@Column(name = "FINALIZED")
	private final Integer finalized;
	
	@Column(name = "TOTAL")
	private final Integer total;
	
	private StudentsIndicatorByInstitutionLevel() {
		this.id = null;
		this.startYear = null;
		this.startSemester = null;
		this.level = null;
		this.notFinalized = null;
		this.finalized = null;
		this.total = null;
	}

	public boolean isInstitutionLevelSuperior() {
		return level.isSuperior();
	}

	/**
	 * @return O ano com o semestre formatado. 
	 */
	public String getYearSemester() {
		return String.format("%d/%d", startYear, startSemester);
	}
}