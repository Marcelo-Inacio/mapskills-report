/*
 * @(#)ReportControllerTest.java 1.0 1 12/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.gov.sp.fatec.mapskills.report.ReportApplication;

/**
 * A classe {@link ReportControllerTest} contem
 * metodos de testes para API de <code>ReportController</code>.
 *
 * @author Marcelo
 * @version 1.0 12/10/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ReportApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ReportControllerTest extends AbstractControllerTets {
	
	@Autowired
    private MockMvc mvc;
	
	@Test
	public void reportStudentResultTest() throws Exception {
		insertStudentResults(10, "146", "028");
		final MvcResult result = this.mvc.perform(get("/report"))
				.andExpect(status().isOk()).andReturn();
		
		final String content = result.getResponse().getContentAsString();
		final String expected = getJsonAsString("students-report.json");
		JSONAssert.assertEquals(expected, content, true);
	}
	
	@Test
	public void reportStudentResultWithParamTest() throws Exception {
		insertStudentResults(10, "146", "028");
		insertStudentResults(3, "146", "112");
		final MvcResult result = this.mvc.perform(get("/report")
				.param("institutionLevel", "SUPERIOR")
				.param("institutionCode", "146")
				.param("courseCode", "112")
				.param("startDate", "2017/2")
				.param("endDate", "2017/2")				)
				.andExpect(status().isOk()).andReturn();
		
		final String content = result.getResponse().getContentAsString();
		final String expected = getJsonAsString("students-report-with-param.json");
		JSONAssert.assertEquals(expected, content, true);
	}
	
	@Test
	public void downloadTest() throws Exception {
		insertStudentResults(10, "146", "028");
		final int expectedLength = 1002;
		final MvcResult result = this.mvc.perform(get("/report/download"))
				.andExpect(status().isOk()).andReturn();
		
		final int content = result.getResponse().getContentLength();
		assertEquals(expectedLength, content);
	}
}