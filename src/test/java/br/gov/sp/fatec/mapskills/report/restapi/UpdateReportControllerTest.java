/*
 * @(#)UpdateReportControllerTest.java 1.0 1 31/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.gov.sp.fatec.mapskills.report.ReportApplication;

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
	
	@Test
	public void test() {
		assertTrue(true);
	}

}