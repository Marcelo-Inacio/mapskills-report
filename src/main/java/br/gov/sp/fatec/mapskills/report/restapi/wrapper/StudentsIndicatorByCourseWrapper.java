/*
 * @(#)StudentsIndicatorByCourseWrapper.java 1.0 01/03/2017
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.report.restapi.wrapper;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.sp.fatec.mapskills.report.indicator.course.StudentsIndicatorByCourse;
import br.gov.sp.fatec.mapskills.report.restapi.serializer.StudentsIndicatorByCourseSerializer;

/**
 * 
 * A classe {@link StudentsIndicatorByCourseWrapper} contem
 * a lista de array de objetos que sao os resultados da consulta
 * da VIEW <i>INSTITUTION_STUDENTS_PROGRESS_VIEW</i> que retorna
 * a quantidade de alunos que finalizaram e n�o finalizaram o jogo
 * de todos os cursos de uma instituicao.
 *
 * @author Marcelo
 * @version 1.0 01/03/2017
 */
@JsonSerialize(using = StudentsIndicatorByCourseSerializer.class)
public class StudentsIndicatorByCourseWrapper {
	
	private final List<StudentsIndicatorByCourse> indicators = new LinkedList<>();
	
	public StudentsIndicatorByCourseWrapper(final List<StudentsIndicatorByCourse> indicators) {
		this.indicators.addAll(indicators);
	}
	
	public List<StudentsIndicatorByCourse> getIndicators() {
		return Collections.unmodifiableList(indicators);
	}
}