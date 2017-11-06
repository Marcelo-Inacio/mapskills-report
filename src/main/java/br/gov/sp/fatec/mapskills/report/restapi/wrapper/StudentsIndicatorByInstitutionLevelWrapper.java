/*
 * @(#)StudentsIndicatorByInstitutionLevelWrapper.java 1.0 01/03/2017
 *
 * Copyright (c) 2017, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.report.restapi.wrapper;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.sp.fatec.mapskills.report.indicator.institutionlevel.StudentsIndicatorByInstitutionLevel;
import br.gov.sp.fatec.mapskills.report.restapi.serializer.StudentsIndicatorByInstitutionLevelSerializer;

/**
 * 
 * A classe {@link StudentsIndicatorByInstitutionLevelWrapper} encapsula
 * a lista de resultset retornados da consulta da view
 * <i>ADMIN_GLOBAL_STUDENTS_PROGRESS_VIEW</i>
 * colunas: START_YEAR, START_SEMESTER, LEVEL, NOT_FINALIZED, FINALIZED e TOTAL.
 *
 * @author Marcelo
 * @version 1.0 01/03/2017
 */
@JsonSerialize(using = StudentsIndicatorByInstitutionLevelSerializer.class)
public class StudentsIndicatorByInstitutionLevelWrapper {
	
	private List<StudentsIndicatorByInstitutionLevel> indicatorResults = new LinkedList<>();
	
	public StudentsIndicatorByInstitutionLevelWrapper(final List<StudentsIndicatorByInstitutionLevel> indicatorResults) {
		this.indicatorResults.addAll(indicatorResults);
	}
	
	public List<StudentsIndicatorByInstitutionLevel> getIndicatorResults() {
		return Collections.unmodifiableList(indicatorResults);
	}
}