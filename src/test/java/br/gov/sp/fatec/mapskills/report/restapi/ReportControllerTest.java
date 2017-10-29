/*
 * @(#)ReportControllerTest.java 1.0 1 12/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.gov.sp.fatec.mapskills.report.ReportApplication;

/**
 * A classe {@link ReportControllerTest}
 *
 * @author Marcelo
 * @version 1.0 12/10/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ReportApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ReportControllerTest {
	
	@Autowired
    private MockMvc mvc;
	
	@Test
	public void firstTest() throws Exception {
		final MvcResult result = this.mvc.perform(get("/report").param("institutionCode", "146"))
				.andExpect(status().isOk()).andReturn();
		
		final String content = result.getResponse().getContentAsString();
		System.out.println(content);
	}
}