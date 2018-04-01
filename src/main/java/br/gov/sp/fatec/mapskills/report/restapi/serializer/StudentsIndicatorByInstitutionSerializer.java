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

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.gov.sp.fatec.mapskills.report.indicator.institution.StudentsIndicatorByInstitution;
import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentsIndicatorByInstitutionWrapper;
/**
 * 
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
			final int totalStudents = ObjectUtils.isEmpty(indicator.getTotal()) ? 0 : indicator.getTotal();
			final double totalFinalized = ObjectUtils.isEmpty(indicator.getFinalized()) ? 0 : indicator.getFinalized();
			gen.writeNumber(calcPercentage(totalFinalized, totalStudents));
		}
		gen.writeEndArray();
		gen.writeStartArray();
		for(final StudentsIndicatorByInstitution indicator : indicators) {
			final int totalStudents = ObjectUtils.isEmpty(indicator.getTotal()) ? 0 : indicator.getTotal();
			final double totalNotFinalized = ObjectUtils.isEmpty(indicator.getNotFinalized()) ? 0 : indicator.getNotFinalized();
			gen.writeNumber(calcPercentage(totalNotFinalized, totalStudents));
		}
		gen.writeEndArray();
		gen.writeEndArray();
	}
	
	private void institutionGenerate(final List<StudentsIndicatorByInstitution> indicators, final JsonGenerator gen) throws IOException {
		gen.writeArrayFieldStart("institutions");
		for(final StudentsIndicatorByInstitution indicator : indicators) {
			gen.writeStartObject();
			gen.writeStringField("code", indicator.getInstitutionCode());
			gen.writeStringField("company", indicator.getInstitutionName());
			gen.writeEndObject();
		}
		gen.writeEndArray();
	}
	
	/**
	 * Metodo que calcula uma porcentagem a partir de uma quantidade e um total.
	 */
	private BigDecimal calcPercentage(final double quantity, final double total) {
		final double percentage = (quantity / total) * 100;
		final BigDecimal accuratePercentage = BigDecimal.valueOf(percentage);
		return accuratePercentage.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
}