/*
 * @(#)UpdateReportControllerTest.java 1.0 1 31/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.gov.sp.fatec.mapskills.report.ReportApplication;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResult;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResultRepository;

/**
 * A classe {@link UpdateReportControllerTest} contem
 * metodos de testes para API de <code>UpdateReportController</code>.
 *
 * @author Marcelo
 * @version 1.0 31/10/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ReportApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UpdateReportControllerTest extends AbstractControllerTets {
	
	@Autowired
    private MockMvc mvc;
	
	@Autowired
	private StudentResultRepository studentResultRepository;
	
	@Test
	public void registerResultTest() throws Exception {
		final String json = getJsonAsString("student-result.json");
		this.mvc.perform(post("/report").content(json).contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk());
		
		final StudentResult student = studentResultRepository.findById(1L);
		assertEquals(new Long(1), student.getId());
		assertEquals("1460681720030", student.getRa());
		assertEquals("Aluno Teste da Silva", student.getName());
		assertTrue(student.getStudentIndicators().size() == 5);
	}
	
	@Test
	public void reindexMongoDatabase() throws Exception {
		runSQLCommands("/br/gov/sp/fatec/mapskills/report/database/controller/insert-students.sql");
		this.mvc.perform(post("/report/reindex").contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk());
		
		final List<StudentResult> students = studentResultRepository.findAll();
		assertTrue(students.size() == 4);
	}
}