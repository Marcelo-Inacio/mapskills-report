/*
 * @(#)StudentResultPageSerializer.java.java 1.0 1 28/10/2017
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

import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentResultPageWrapper;
import br.gov.sp.fatec.mapskills.report.studentresult.SkillResultIndicator;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResult;

/**
 * A classe {@link StudentResultPageSerializer} responsavel
 * por serializar a estrutura para visualizcao dos resultados
 * dos alunos na interface do usuario.
 *
 * @author Marcelo
 * @version 1.0 28/10/2017
 */
public class StudentResultPageSerializer extends JsonSerializer<StudentResultPageWrapper> {

	@Override
	public void serialize(final StudentResultPageWrapper wrapper, final JsonGenerator gen,
			final SerializerProvider arg2) throws IOException {
	
		gen.writeStartObject();
		gen.writeNumberField("numberOfElements", wrapper.getNumberOfElements());
		gen.writeNumberField("totalPages", wrapper.getTotalPages());
		gen.writeNumberField("currentPage", wrapper.getCurrentPageNumber());
		gen.writeNumberField("remainingPages", wrapper.getRemaningPages());
		serializeStudents(wrapper.getContent(), gen);
		gen.writeEndObject();
	}
	
	private void serializeStudents(final List<StudentResult> studentIndicators,
			final JsonGenerator gen) throws IOException {
		
		gen.writeArrayFieldStart("students");
		for (final StudentResult student : studentIndicators) {
			gen.writeStartObject();
			gen.writeStringField("ra", student.getRa());
			gen.writeStringField("name", student.getName());
			serializeResult(student.getSkillResultIndicators(), gen);
			gen.writeEndObject();
		}
		gen.writeEndArray();
	}

	private void serializeResult(final List<SkillResultIndicator> skillResultIndicators,
			final JsonGenerator gen) throws IOException {
		
		gen.writeArrayFieldStart("skillResult");
		for (final SkillResultIndicator result : skillResultIndicators) {
			gen.writeStartObject();
			gen.writeStringField("skill", result.getSkillName());
			gen.writeNumberField("value", result.getTotal());
			gen.writeEndObject();
		}
		gen.writeEndArray();
	}
}