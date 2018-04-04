/*
 * @(#)StudentResultWrapper.java 1.0 1 28/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi.wrapper;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.sp.fatec.mapskills.report.restapi.serializer.StudentResultDeserializer;
import br.gov.sp.fatec.mapskills.report.restapi.serializer.StudentResultSerializer;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResult;
import br.gov.sp.fatec.mapskills.report.studentresult.SkillResultIndicator;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A classe {@link StudentResultWrapper} contem o resultado
 * dos indicadores de um aluno encapsulados para serializacao/deserializacao.
 *
 * @author Marcelo
 * @version 1.0 28/10/2017
 */
@Getter
@AllArgsConstructor
@JsonSerialize(using = StudentResultSerializer.class)
@JsonDeserialize(using = StudentResultDeserializer.class)
public class StudentResultWrapper {
	
	private final StudentResult studentResult;
	
	public List<SkillResultIndicator> getStudentResultIndicators() {
		return studentResult.getStudentIndicators();
	}
}