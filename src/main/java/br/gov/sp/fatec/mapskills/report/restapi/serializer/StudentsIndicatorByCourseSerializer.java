/*
 * @(#)StudentsIndicatorByCourseSerializer.java 1.0 01/03/2017
 *
 * Copyright (c) 2017, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.report.restapi.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.gov.sp.fatec.mapskills.report.indicator.course.StudentsIndicatorByCourse;
import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentsIndicatorByCourseWrapper;
/**
 * 
 * A classe {@link StudentsIndicatorByCourseSerializer} e responsavel
 * por serializar os resultados da quantidade de alunos que finalizaram e
 * nao finalizaram de todos os cursos de uma instituicao.
 *
 * @author Marcelo
 * @version 1.0 01/03/2017
 */
public class StudentsIndicatorByCourseSerializer extends JsonSerializer<StudentsIndicatorByCourseWrapper> {

	@Override
	public void serialize(final StudentsIndicatorByCourseWrapper wrapper, final JsonGenerator gen,
			final SerializerProvider serializers) throws IOException {
		gen.writeStartArray();
		for(final StudentsIndicatorByCourse indicator : wrapper.getIndicators()) {
			gen.writeStartObject();
			gen.writeStringField("code", indicator.getCourseCode());
			gen.writeStringField("name", indicator.getCourseName());		
			gen.writeArrayFieldStart("values");
			gen.writeNumber(indicator.getNotFinalized());
			gen.writeNumber(indicator.getFinalized());
			gen.writeEndArray();			
			gen.writeEndObject();
		}		
		gen.writeEndArray();		
	}
}