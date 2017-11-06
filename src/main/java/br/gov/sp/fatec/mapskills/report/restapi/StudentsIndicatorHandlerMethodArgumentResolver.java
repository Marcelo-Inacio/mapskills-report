/*
 * @(#)StudentsIndicatorHandlerMethodArgumentResolver.java 1.0 1 05/11/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi;

import java.time.LocalDate;
import java.time.Year;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

/**
 * A classe {@link StudentsIndicatorHandlerMethodArgumentResolver}
 *
 * @author Marcelo
 * @version 1.0 05/11/2017
 */
public interface StudentsIndicatorHandlerMethodArgumentResolver extends HandlerMethodArgumentResolver {
	
	default Integer getYear(final String param, final NativeWebRequest webRequest) {
		final String yearParam = webRequest.getParameter(param);
		if (!NumberUtils.isCreatable(yearParam)) {
			return Year.now().getValue();
		}
		return Integer.valueOf(yearParam);
	}
	
	default Integer getSemester(final String param, final NativeWebRequest webRequest) {
		final String semesterParam = webRequest.getParameter(param);
		if (!NumberUtils.isCreatable(semesterParam)) {
			return LocalDate.now().getMonthValue() < 7 ? 1 : 2;
		}
		return Integer.valueOf(semesterParam);
	}
}