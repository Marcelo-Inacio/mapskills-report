/*
 * @(#)StudentsIndicatorByInstitutionSpecification.java 1.0 1 05/11/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.indicator.institution;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.gov.sp.fatec.mapskills.report.indicator.StudentsIndicatorSpecification;

/**
 * A classe {@link StudentsIndicatorByInstitutionSpecification}
 *
 * @author Marcelo
 * @version 1.0 05/11/2017
 */
public class StudentsIndicatorByInstitutionSpecification
				extends StudentsIndicatorSpecification<StudentsIndicatorByInstitution> {

	private final Integer institutionLevel;

	public StudentsIndicatorByInstitutionSpecification(final Integer startYear, final Integer startSemester,
			final Integer endYear, final Integer endSemester, final Integer institutionLevel) {
		super(startYear, startSemester, endYear, endSemester);
		this.institutionLevel = institutionLevel;
	}

	
	@Override
	public Predicate toPredicate(final Root<StudentsIndicatorByInstitution> root,
			final CriteriaQuery<?> query, final CriteriaBuilder builder) {
		final Predicate yearSemesterP = getYearSemesterPredicate(root, builder);
		final Predicate institutionLevelP = equal(root, builder, "level", institutionLevel);
		return and(builder, yearSemesterP, institutionLevelP);
	}
}