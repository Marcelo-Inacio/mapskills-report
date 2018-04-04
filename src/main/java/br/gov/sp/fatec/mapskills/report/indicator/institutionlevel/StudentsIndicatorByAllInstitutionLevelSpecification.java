/*
 * @(#)StudentsIndicatorByAllInstitutionLevelSpecification.java 1.0 1 05/11/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.indicator.institutionlevel;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.gov.sp.fatec.mapskills.report.indicator.StudentsIndicatorSpecification;

/**
 * A classe {@link StudentsIndicatorByAllInstitutionLevelSpecification}
 *
 * @author Marcelo
 * @version 1.0 05/11/2017
 */
public class StudentsIndicatorByAllInstitutionLevelSpecification
		extends StudentsIndicatorSpecification<StudentsIndicatorByInstitutionLevel> {

	public StudentsIndicatorByAllInstitutionLevelSpecification(
			final Integer startYear, final Integer startSemester,
			final Integer endYear, final Integer endSemester) {
		super(startYear, startSemester, endYear, endSemester);
	}

	@Override
	public Predicate toPredicate(final Root<StudentsIndicatorByInstitutionLevel> root,
			final CriteriaQuery<?> query, final CriteriaBuilder builder) {
		return super.getYearSemesterPredicate(root, builder);
	}
}