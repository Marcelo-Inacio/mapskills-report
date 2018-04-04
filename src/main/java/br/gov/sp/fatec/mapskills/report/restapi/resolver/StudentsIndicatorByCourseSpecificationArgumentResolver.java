/*
 * @(#)StudentsIndicatorByCourseArgumentResolver.java 1.0 1 05/11/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi.resolver;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import br.gov.sp.fatec.mapskills.report.indicator.course.StudentsIndicatorByInstitutionCodeSpecification;

/**
 * A classe {@link StudentsIndicatorByCourseSpecificationArgumentResolver}
 *
 * @author Marcelo
 * @version 1.0 05/11/2017
 */
public class StudentsIndicatorByCourseSpecificationArgumentResolver implements StudentsIndicatorHandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(final MethodParameter parameter) {
		return parameter.getParameterType().equals(StudentsIndicatorByInstitutionCodeSpecification.class);
	}
	@Override
	public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
			final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {

		return new StudentsIndicatorByInstitutionCodeSpecification(getYear("startYear", webRequest), getSemester("startSemester", webRequest),
				getYear("endYear", webRequest), getSemester("endSemester", webRequest), getInstitutionCode(webRequest));
	}
	
	private Integer getInstitutionCode(final NativeWebRequest webRequest) {
		final String institutionCodeParam = webRequest.getParameter("institutionCode");
		if (NumberUtils.isCreatable(institutionCodeParam)) {
			return Integer.valueOf(institutionCodeParam);
		}
		return null;
	}
}