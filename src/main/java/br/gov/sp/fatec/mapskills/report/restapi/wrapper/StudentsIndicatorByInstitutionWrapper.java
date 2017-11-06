/*
 * @(#)StudentsProgressLevelWrapper.java 1.0 01/03/2017
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.report.restapi.wrapper;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.sp.fatec.mapskills.report.indicator.institution.StudentsIndicatorByInstitution;
import br.gov.sp.fatec.mapskills.report.restapi.serializer.StudentsIndicatorByInstitutionSerializer;

/**
 * 
 * A classe {@link StudentsIndicatorByInstitutionWrapper} contem o
 * result set que representa a quantidade de alunos que
 * finalzaram e nao finalizaram o jogo por nivel de instituicao
 * (superior e tecnico).
 *
 * @author Marcelo
 * @version 1.0 01/03/2017
 */
@JsonSerialize(using = StudentsIndicatorByInstitutionSerializer.class)
public class StudentsIndicatorByInstitutionWrapper {
	
	private final List<StudentsIndicatorByInstitution> indicatorResults = new LinkedList<>();
	
	public StudentsIndicatorByInstitutionWrapper(final List<StudentsIndicatorByInstitution> indicatorResults) {
		this.indicatorResults.addAll(indicatorResults);
	}
	
	public List<StudentsIndicatorByInstitution> getIndicatorResults() {
		return Collections.unmodifiableList(indicatorResults);
	}
}