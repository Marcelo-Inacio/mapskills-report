/*
 * @(#)StudentsIndicatorByInstitutionSerializer.java 1.0 01/03/2017
 *
 * Copyright (c) 2017, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi.serializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.gov.sp.fatec.mapskills.report.indicator.institution.StudentsIndicatorByInstitution;
import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentsIndicatorByInstitutionWrapper;

/**
 * A classe {@link StudentsIndicatorByInstitutionSerializer} e responsavel
 * por serializar os resutados contidos na classe
 * <code>StudentsProgressLevelWrapper</code>. JSON utilizado
 * na tela de perfil administrador.
 *
 * @author Marcelo
 * @version 1.0 01/03/2017
 */
public class StudentsIndicatorByInstitutionSerializer extends JsonSerializer<StudentsIndicatorByInstitutionWrapper> {

	@Override
	public void serialize(final StudentsIndicatorByInstitutionWrapper wrapper, final JsonGenerator gen,
			final SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		dataGenerate(wrapper.getIndicatorResults(), gen);
		institutionGenerate(wrapper.getIndicatorResults(), gen);
		gen.writeEndObject();
	}

	
	private void dataGenerate(final List<StudentsIndicatorByInstitution> indicators, final JsonGenerator gen) throws IOException {		
		gen.writeArrayFieldStart("data");
		gen.writeStartArray();
		for(final StudentsIndicatorByInstitution indicator : indicators) {
			final Integer totalStudents = indicator.getTotal();
			final Integer totalFinalized = indicator.getFinalized();
			gen.writeNumber(calcPercentage(totalFinalized, totalStudents));
		}
		gen.writeEndArray();
		gen.writeStartArray();
		for(final StudentsIndicatorByInstitution indicator : indicators) {
			final Integer totalStudents = indicator.getTotal();
			final Integer totalNotFinalized = indicator.getNotFinalized();
			gen.writeNumber(calcPercentage(totalNotFinalized, totalStudents));
		}
		gen.writeEndArray();
		gen.writeEndArray();
	}
	
	private void institutionGenerate(final List<StudentsIndicatorByInstitution> indicators, final JsonGenerator gen) throws IOException {
		gen.writeArrayFieldStart("institutions");
		for(final StudentsIndicatorByInstitution indicator : indicators) {
			gen.writeStartObject();
			gen.writeStringField("code", indicator.getCode());
			gen.writeStringField("company", indicator.getInstitutionName());
			gen.writeEndObject();
		}
		gen.writeEndArray();
	}
	
	/**
	 * Calcula a porcentagem.
	 * 
	 * @param quantity Quantidade de alunos.
	 * @param total Total de alunos.
	 * @return Porcentagem de alunos.
	 */
	private static BigDecimal calcPercentage(final Integer quantity, final Integer total) {
		final BigDecimal quantityDecimal = BigDecimal.valueOf(quantity);
		final BigDecimal totalDecimal = BigDecimal.valueOf(total);
		return quantityDecimal.divide(totalDecimal, 2, BigDecimal.ROUND_HALF_EVEN).multiply(BigDecimal.valueOf(100));
	}
}