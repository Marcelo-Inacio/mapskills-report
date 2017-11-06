/*
 * @(#)StudentResultSerializer.java 1.0 1 28/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi.serializer;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentResultWrapper;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResultIndicator;

/**
 * A classe {@link StudentResultSerializer} e responsavel
 * por serializar o resultado de um aluno para que seja
 * exibida no grafico de radar. Devolve o objeto preparado
 * para que o componente de grafico do JS receba.
 *
 * @author Marcelo
 * @version 1.0 28/10/2017
 */
public class StudentResultSerializer extends JsonSerializer<StudentResultWrapper> {

	@Override
	public void serialize(final StudentResultWrapper wrapper, final JsonGenerator gen,
			final SerializerProvider serializers) throws IOException {

		gen.writeStartObject();
		this.serializeLabels(wrapper, gen);
		this.serializeResultValues(wrapper, gen);
		this.serializeSkills(wrapper, gen);
		gen.writeEndObject();
	}
	
	/**
	 * Responsavel por serializar os label's do grafico de radar.
	 */
	private void serializeLabels(final StudentResultWrapper wrapper, final JsonGenerator generator) throws IOException {
		final List<StudentResultIndicator> indicators = wrapper.getStudentResultIndicators();
		generator.writeArrayFieldStart("labels");
		for(final StudentResultIndicator indicator : indicators) {
			generator.writeString(indicator.getSkillName());
		}
		generator.writeEndArray();
	}
	
	/**
	 * Responsavel por serializar os valores do grafico de radar.
	 */
	private void serializeResultValues(final StudentResultWrapper wrapper, final JsonGenerator generator) throws IOException {
		final List<StudentResultIndicator> indicators = wrapper.getStudentResultIndicators();
		generator.writeArrayFieldStart("datasets");
		for(final StudentResultIndicator indicator : indicators) {
			generator.writeNumber(indicator.getTotal());
		}
		generator.writeEndArray();
	}
	
	/**
	 * Responsavel por serializar as competencias avaliadas pelo aluno.
	 */
	private void serializeSkills(final StudentResultWrapper wrapper, final JsonGenerator generator) throws IOException {
		final List<StudentResultIndicator> indicators = wrapper.getStudentResultIndicators();
		generator.writeArrayFieldStart("skills");
		for(final StudentResultIndicator indicator : indicators) {
			generator.writeStartObject();
			generator.writeStringField("name", indicator.getSkillName());
			generator.writeStringField("description", indicator.getSkillDescription());
			generator.writeEndObject();
		}
		generator.writeEndArray();
	}
}