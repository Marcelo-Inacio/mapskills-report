/*
 * @(#)StudentsIndicatorByInstitutionArgumentResolver.java 1.0 1 05/11/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi;

import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import br.gov.sp.fatec.mapskills.report.indicator.InstitutionLevel;
import br.gov.sp.fatec.mapskills.report.indicator.institution.StudentsIndicatorByInstitutionSpecification;

/**
 * A classe {@link StudentsIndicatorByInstitutionSpecificationArgumentResolver}
 *
 * @author Marcelo
 * @version 1.0 05/11/2017
 */
public class StudentsIndicatorByInstitutionSpecificationArgumentResolver implements StudentsIndicatorHandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(final MethodParameter parameter) {
		return parameter.getParameterType().equals(StudentsIndicatorByInstitutionSpecification.class);
	}

	@Override
	public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
			final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {
		
		return new StudentsIndicatorByInstitutionSpecification(getYear("startYear", webRequest),
				getSemester("startSemester", webRequest), getYear("endYear", webRequest),
				getSemester("endSemester", webRequest), getInstitutionLevel(webRequest));
	}
	
	private InstitutionLevel getInstitutionLevel(final NativeWebRequest webRequest) {
		final String institutionLevelParam = webRequest.getParameter("institutionLevel");
		return StringUtils.isEmpty(institutionLevelParam) ? null : InstitutionLevel.withLevel(institutionLevelParam);
	}
}