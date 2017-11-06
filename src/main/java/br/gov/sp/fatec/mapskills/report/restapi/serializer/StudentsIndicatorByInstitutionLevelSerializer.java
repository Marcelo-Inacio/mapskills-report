/*
 * @(#)StudentsIndicatorByInstitutionLevelSerializer.java 1.0 01/03/2017
 *
 * Copyright (c) 2017, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.report.restapi.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.gov.sp.fatec.mapskills.report.indicator.institutionlevel.StudentsIndicatorByInstitutionLevel;
import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentsIndicatorByInstitutionLevelWrapper;
/**
 * 
 * A classe {@link StudentsIndicatorByInstitutionLevelSerializer} e responavel
 * por serializar os resultados da porcentagem de alunos que finalizaram
 * e nao finalizaram o jogo de todos os cursos dos ensinos superiores
 * e tecnico.
 *
 * @author Marcelo
 * @version 1.0 01/03/2017
 */
public class StudentsIndicatorByInstitutionLevelSerializer extends JsonSerializer<StudentsIndicatorByInstitutionLevelWrapper> {

	@Override
	public void serialize(final StudentsIndicatorByInstitutionLevelWrapper wrapper, final JsonGenerator gen,
			final SerializerProvider serializers) throws IOException {
		gen.writeStartArray();
		for(final StudentsIndicatorByInstitutionLevel indicator : wrapper.getIndicatorResults() ) {
			gen.writeStartObject();
			gen.writeStringField("level", indicator.isInstitutionLevelSuperior() ? "Fatecs" : "Etecs");
			gen.writeStringField("yearSemester", indicator.getYearSemester());
			gen.writeArrayFieldStart("values");
			gen.writeNumber(indicator.getFinalized());
			gen.writeNumber(indicator.getNotFinalized());
			gen.writeEndArray();			
			gen.writeEndObject();
		}
		gen.writeEndArray();
	}
}