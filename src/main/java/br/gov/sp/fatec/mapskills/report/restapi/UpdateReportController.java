/*
 * @(#)UpdateReportController.java 1.0 1 21/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentResultWrapper;
import lombok.AllArgsConstructor;

/**
 * A classe {@link UpdateReportController}
 *
 * @author Marcelo
 * @version 1.0 21/10/2017
 */
@RestController
@AllArgsConstructor
public class UpdateReportController {
	
	@PostMapping("/report")
	public void updateReport(@RequestBody final StudentResultWrapper wrapper) {
		
	}

}