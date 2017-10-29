/*
 * @(#)ReportFilterArgumentResolver.java 1.0 1 21/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi;

import java.util.LinkedList;
import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import br.gov.sp.fatec.mapskills.report.studentreport.QStudentResult;

/**
 * A classe {@link ReportPredicateArgumentResolver} e um interceptor
 * que monta um predicate para injecao no <code>ReportController</code>
 * para filtro de pesquisa de relatorio dos alunos.
 *
 * @author Marcelo
 * @version 1.0 21/10/2017
 */
public class ReportPredicateArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(final MethodParameter parameter) {
		return parameter.getParameterType().equals(Predicate.class);
	}
	
	@Override
	public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
			final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {

		final QStudentResult query = QStudentResult.studentResult;
		final List<BooleanExpression> predicates = new LinkedList<>();
		
		institutionLevel(query, webRequest, predicates);
		institutionCode(query, webRequest, predicates);
		courseCode(query, webRequest, predicates);
		startDate(query, webRequest, predicates);
		endDate(query, webRequest, predicates);
		
		BooleanExpression result = predicates.isEmpty() ? null : predicates.get(0);
		for (int i = 1; i < predicates.size(); i++) {
            result = result.and(predicates.get(i));
        }		
		return result;
	}
	
	private void institutionLevel(final QStudentResult query,
			final NativeWebRequest webRequest, List<BooleanExpression> predicates) {
		final String level = webRequest.getParameter("institutionLevel");
		if(!StringUtils.isEmpty(level)) {
			predicates.add(query.institutionLevel.eq(level));
		}
	}
	
	private void institutionCode(final QStudentResult query,
			final NativeWebRequest webRequest, List<BooleanExpression> predicates) {
		final String institutionCode = webRequest.getParameter("institutionCode");
		if(!StringUtils.isEmpty(institutionCode)) {
			predicates.add(query.institutionCode.eq(institutionCode));
		}
	}
	
	private void courseCode(final QStudentResult query,
			final NativeWebRequest webRequest, List<BooleanExpression> predicates) {
		final String courseCode = webRequest.getParameter("courseCode");
		if(!StringUtils.isEmpty(courseCode)) {
			predicates.add(query.courseCode.eq(courseCode));
		}
	}
	
	private void startDate(final QStudentResult query,
			final NativeWebRequest webRequest, List<BooleanExpression> predicates) {
		final String startDate = webRequest.getParameter("startDate");
		if(!StringUtils.isEmpty(startDate)) {
			predicates.add(query.yearSemester.goe(startDate));
		}
	}
	
	private void endDate(final QStudentResult query,
			final NativeWebRequest webRequest, List<BooleanExpression> predicates) {
		final String endDate = webRequest.getParameter("endDate");
		if(!StringUtils.isEmpty(endDate)) {
			predicates.add(query.yearSemester.loe(endDate));
		}
	}
}