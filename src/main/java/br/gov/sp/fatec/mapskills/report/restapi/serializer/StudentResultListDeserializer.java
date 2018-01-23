/*
 * @(#)StudentResultListDeserializer.java 1.0 1 23/01/2018
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentResultListWrapper;

/**
 * A classe {@link StudentResultListDeserializer}
 *
 * @author Marcelo
 * @version 1.0 23/01/2018
 */
public class StudentResultListDeserializer extends JsonDeserializer<StudentResultListWrapper> {

	@Override
	public StudentResultListWrapper deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
}