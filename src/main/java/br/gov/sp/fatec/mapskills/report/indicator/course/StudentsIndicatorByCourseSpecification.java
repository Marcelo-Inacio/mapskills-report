/*
 * @(#)StudentsIndicatorByCourseSpecification.java 1.0 1 05/11/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.indicator.course;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.gov.sp.fatec.mapskills.report.indicator.StudentsIndicatorSpecification;

/**
 * A classe {@link StudentsIndicatorByCourseSpecification}
 *
 * @author Marcelo
 * @version 1.0 05/11/2017
 */
public class StudentsIndicatorByCourseSpecification extends StudentsIndicatorSpecification<StudentsIndicatorByCourse> {

	private final Integer institutionCode;
	
	public StudentsIndicatorByCourseSpecification(final Integer startYear, final Integer startSemester,
			final Integer endYear, final Integer endSemester, final Integer institutionCode) {
		super(startYear, startSemester, endYear, endSemester);
		this.institutionCode = institutionCode;
	}

	@Override
	public Predicate toPredicate(final Root<StudentsIndicatorByCourse> root,
			final CriteriaQuery<?> query, final CriteriaBuilder builder) {
		final Predicate yearSemesterP = getYearSemesterPredicate(root, builder);
		final Predicate institutionCodeP = equal(root, builder, "institutionCode", institutionCode);
		return and(builder, yearSemesterP, institutionCodeP);
	}
}