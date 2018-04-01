/*
 * @(#)StudentResultDeserializer.java 1.0 1 28/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi.serializer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentResultWrapper;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResult;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResultIndicator;

/**
 * A classe {@link StudentResultDeserializer} responsavel
 * por deserializar um objeto <code>StudentResult</code>.
 *
 * @author Marcelo
 * @version 1.0 28/10/2017
 */
public class StudentResultDeserializer extends JsonDeserializer<StudentResultWrapper> {

	@Override
	public StudentResultWrapper deserialize(final JsonParser jsonParser,
			final DeserializationContext arg1) throws IOException {
		
		final JsonNode node = jsonParser.getCodec().readTree(jsonParser);

		return new StudentResultWrapper(
				new StudentResult(node.get("id").asLong(),
						node.get("ra").asText(),
						node.get("name").asText(),
						node.get("courseCode").asText(),
						node.get("courseName").asText(),
						node.get("institutionCode").asText(),
						node.get("institutionCompany").asText(),
						node.get("institutionLevel").asText(),
						node.get("startYear").asInt(),
						node.get("startSemester").asInt(),
						deserializeIndicators(node.get("studentIndicators"))));
	}
	
	private List<StudentResultIndicator> deserializeIndicators(final JsonNode node) {
		final List<StudentResultIndicator> indicators = new LinkedList<>();
		node.forEach(currentNode -> indicators.add(new StudentResultIndicator(currentNode.get("skillName").asText(),
					currentNode.get("skillDescription").asText(),
					currentNode.get("total").asInt())));
		
		return indicators;
	}
}