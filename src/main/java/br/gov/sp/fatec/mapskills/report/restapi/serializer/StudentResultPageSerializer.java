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
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResult;
import br.gov.sp.fatec.mapskills.report.studentresult.SkillResultIndicator;

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
		gen.writeNumberField("remaningPages", wrapper.getRemaningPages());
		serializeHeader(wrapper.getFirstStudentIndicators(), gen);
		serializeResults(wrapper.getContent(), gen);
		gen.writeEndObject();
	}
	
	private void serializeHeader(final List<SkillResultIndicator> studentIndicators,
			final JsonGenerator gen) throws IOException {
		
		gen.writeArrayFieldStart("head");
		gen.writeString("RA");
		gen.writeString("NOME");
		for(final SkillResultIndicator indicator : studentIndicators) {
			gen.writeString(indicator.getSkillName());
		}
		gen.writeEndArray();
	}
	
	private void serializeResults(final List<StudentResult> studentReport,
			final JsonGenerator gen) throws IOException {
		
		gen.writeArrayFieldStart("data");
		for(final StudentResult student : studentReport) {
			gen.writeStartArray();
			gen.writeString(student.getRa());
			gen.writeString(student.getName());
			for(final SkillResultIndicator score : student.getStudentIndicators()) {
				gen.writeNumber(score.getTotal());
			}
			gen.writeEndArray();
		}
		gen.writeEndArray();
	}
}