/*
 * @(#)StudentResultPredicateArgumentResolver.java 1.0 1 21/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.querydsl.core.types.dsl.BooleanExpression;

import br.gov.sp.fatec.mapskills.report.studentresult.QStudentResult;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResultPredicate;

/**
 * A classe {@link StudentResultPredicateArgumentResolver} e um interceptor
 * que monta um predicate para injecao no <code>ReportController</code>
 * para filtro de pesquisa de relatorio dos alunos.
 *
 * @author Marcelo
 * @version 1.0 21/10/2017
 */
public class StudentResultPredicateArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(final MethodParameter parameter) {
		return parameter.getParameterType().equals(StudentResultPredicate.class);
	}
	
	@Override
	public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
			final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {

		final QStudentResult query = QStudentResult.studentResult;
		final List<BooleanExpression> predicates = new LinkedList<>();
		
		institutionLevel(query, webRequest, predicates);
		institutionCode(query, webRequest, predicates);
		courseCode(query, webRequest, predicates);
		startYearSemesterRange(query, webRequest, predicates);
		endYearSemesterRange(query, webRequest, predicates);
		
		BooleanExpression result = predicates.isEmpty() ? null : predicates.get(0);
		for (int i = 1; i < predicates.size(); i++) {
            result = result.and(predicates.get(i));
        }		
		return new StudentResultPredicate(result);
	}
	
	private void institutionLevel(final QStudentResult query,
			final NativeWebRequest webRequest, final List<BooleanExpression> predicates) {
		final String level = webRequest.getParameter("institutionLevel");
		if (!StringUtils.isEmpty(level)) {
			predicates.add(query.institutionLevel.eq(level));
		}
	}
	
	private void institutionCode(final QStudentResult query,
			final NativeWebRequest webRequest, final List<BooleanExpression> predicates) {
		final String institutionCode = webRequest.getParameter("institutionCode");
		if (!StringUtils.isEmpty(institutionCode)) {
			predicates.add(query.institutionCode.eq(institutionCode));
		}
	}
	
	private void courseCode(final QStudentResult query,
			final NativeWebRequest webRequest, final List<BooleanExpression> predicates) {
		final String courseCode = webRequest.getParameter("courseCode");
		if (!StringUtils.isEmpty(courseCode)) {
			predicates.add(query.courseCode.eq(courseCode));
		}
	}
	
	private void startYearSemesterRange(final QStudentResult query,
			final NativeWebRequest webRequest, final List<BooleanExpression> predicates) {
		final String startYear = webRequest.getParameter("startYear");
		final String startSemester = webRequest.getParameter("startSemester");
		if (NumberUtils.isCreatable(startYear) || NumberUtils.isCreatable(startSemester)) {
			predicates.add(query.startYear.goe(Integer.valueOf(startYear)));
			predicates.add(query.startSemester.goe(Integer.valueOf(startSemester)));
		}
	}
	
	private void endYearSemesterRange(final QStudentResult query,
			final NativeWebRequest webRequest, final List<BooleanExpression> predicates) {
		final String endYear = webRequest.getParameter("endYear");
		final String endSemester = webRequest.getParameter("endSemester");
		if (NumberUtils.isCreatable(endYear) || NumberUtils.isCreatable(endSemester)) {
			predicates.add(query.startYear.loe(Integer.valueOf(endYear)));
			predicates.add(query.startSemester.loe(Integer.valueOf(endSemester)));
		}
	}
}