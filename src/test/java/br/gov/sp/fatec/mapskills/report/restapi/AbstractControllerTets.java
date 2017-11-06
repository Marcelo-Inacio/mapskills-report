/*
 * @(#)AbstractControllerTets.java 1.0 1 30/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import br.gov.sp.fatec.mapskills.report.studentresult.StudentResult;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResultIndicator;
import net.minidev.json.JSONValue;

/**
 * A classe {@link AbstractControllerTets}
 *
 * @author Marcelo
 * @version 1.0 30/10/2017
 */
public abstract class AbstractControllerTets {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Before
	public void before() {
		mongoTemplate.dropCollection(StudentResult.class);
	}
	
	protected String getJsonAsString(final String path) throws UnsupportedEncodingException, FileNotFoundException {
        final InputStream inputStream = getClass().getResourceAsStream(String.format("/br/gov/sp/fatec/mapskills/report/json/%s", path));
        if (inputStream == null) {
            throw new FileNotFoundException("File " + path + " not found. A file named " + path + " must be present "
                    + "in the src/test/resources folder of the project whose class matches being tested.");
        }
        return JSONValue.parse(new InputStreamReader(inputStream, "UTF-8")).toString();
    }
	
	protected void insertStudentResults(final int resultNumber, final String institutionCode, final String courseCode) {
		final List<StudentResult> results = new LinkedList<>();
		for (int i = 0; i < resultNumber; i++) {
			final String ra = String.format("%s%s17200%d", institutionCode, courseCode, i);
			final StudentResult studentResult = new StudentResult(new Long(i), ra,
					"AlunoTest"+i, courseCode, "Banco De Dados", institutionCode, "Fatec Profº Jessen Vidal",
					"SUPERIOR", 2017, 2, getResultIndicator());
			results.add(studentResult);
		}
		mongoTemplate.insertAll(results);
	}
	
	private List<StudentResultIndicator> getResultIndicator() {
		final Map<String, String> skills = new HashMap<>(5);
		skills.put("Liderança", "Descrição da Habilidade de Liderança.");
		skills.put("Gestão de Tempo", "Descrição da Habilidade de Gestão de Tempo.");
		skills.put("Resiliência", "Descrição da Habilidade de Resiliência.");
		skills.put("Visão de Futuro", "Descrição da Habilidade de Visão de Futuro.");
		skills.put("Comunicação", "Descrição da Habilidade de Comunicação.");
		
		final List<StudentResultIndicator> indicators = new LinkedList<>();
		skills.forEach((name, description) -> {
			indicators.add(new StudentResultIndicator(name, description, 7));
		});
		return indicators;
	}
}