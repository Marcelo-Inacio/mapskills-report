/*
 * @(#)StudentsIndicatorSpecification.java 1.0 1 05/11/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.indicator;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import lombok.AllArgsConstructor;

/**
 * A classe {@link StudentsIndicatorSpecification}
 *
 * @author Marcelo
 * @version 1.0 05/11/2017
 */
@AllArgsConstructor
public abstract class StudentsIndicatorSpecification<T> implements Specification<T> {

	private final Integer startYear;
	private final Integer startSemester;
	private final Integer endYear;
	private final Integer endSemester;
	
	protected Predicate getYearSemesterPredicate(final Root<T> root, final CriteriaBuilder builder) {
		final Predicate startYearP = builder.greaterThanOrEqualTo(root.get("startYear"), startYear);
		final Predicate startSemeP = builder.greaterThanOrEqualTo(root.get("startSemester"), startSemester);
		final Predicate endYearP = builder.lessThanOrEqualTo(root.get("startYear"), endYear);
		final Predicate endSemeP = builder.lessThanOrEqualTo(root.get("startSemester"), endSemester);
		return builder.and(startYearP, startSemeP, endYearP, endSemeP);
	}
	
	protected Predicate equal(final Root<T> root, final CriteriaBuilder builder,
			final String rootParam, final Object filterParam) {
		if (ObjectUtils.isEmpty(filterParam)) {
			return null;
		}
		return builder.equal(root.get(rootParam), filterParam);
	}
	
	protected Predicate and(final CriteriaBuilder builder, final Predicate... predicates) {
		final List<Predicate> predicateList = new LinkedList<>();
		for (final Predicate predicate : predicates) {
			if (ObjectUtils.isEmpty(predicate)) {
				continue;
			}
			predicateList.add(predicate);
		}
		return builder.and(predicateList.toArray(new Predicate[predicateList.size()]));
	}
}