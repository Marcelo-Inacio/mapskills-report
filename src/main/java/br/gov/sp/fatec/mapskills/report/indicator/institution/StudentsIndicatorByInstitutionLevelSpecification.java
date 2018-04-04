/*
 * @(#)StudentsIndicatorByInstitutionLevelSpecification.java 1.0 1 05/11/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.indicator.institution;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.gov.sp.fatec.mapskills.report.indicator.InstitutionLevel;
import br.gov.sp.fatec.mapskills.report.indicator.StudentsIndicatorSpecification;

/**
 * A classe {@link StudentsIndicatorByInstitutionLevelSpecification} sera
 * executada em {@link StudentsIndicatorByInstitutionLevelRepository} para trazer
 * a quantidade de alunos que finalizaram e nao finalizaram o jogo por um grau de instituicao.
 *
 * @author Marcelo
 * @version 1.0 05/11/2017
 */
public class StudentsIndicatorByInstitutionLevelSpecification 
		extends StudentsIndicatorSpecification<StudentsIndicatorByInstitution> {

	private final InstitutionLevel institutionLevel;

	public StudentsIndicatorByInstitutionLevelSpecification(final Integer startYear, final Integer startSemester,
			final Integer endYear, final Integer endSemester, final InstitutionLevel institutionLevel) {
		super(startYear, startSemester, endYear, endSemester);
		this.institutionLevel = institutionLevel;
	}

	
	@Override
	public Predicate toPredicate(final Root<StudentsIndicatorByInstitution> root,
			final CriteriaQuery<?> query, final CriteriaBuilder builder) {
		final Predicate yearSemesterP = getYearSemesterPredicate(root, builder);
		final Predicate institutionLevelP = equalPredicate(root, builder, "level", institutionLevel);
		return and(builder, yearSemesterP, institutionLevelP);
	}
}