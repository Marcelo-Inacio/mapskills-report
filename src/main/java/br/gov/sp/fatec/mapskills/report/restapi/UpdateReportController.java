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

import br.gov.sp.fatec.mapskills.report.application.ReportApplicationServices;
import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentResultListWrapper;
import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentResultWrapper;
import lombok.AllArgsConstructor;

/**
 * A classe {@link UpdateReportController} contem metodos
 * para operacoes de insercao de registros, relacionados aos
 * resultados de alunos.
 *
 * @author Marcelo
 * @version 1.0 21/10/2017
 */
@RestController
@AllArgsConstructor
public class UpdateReportController {
	
	private final ReportApplicationServices services;
	
	/**
	 * Endpoint responsavel por cadastrar os resultados de um
	 * aluno na aplicacao.
	 * 
	 * @param wrapper
	 * 		resultado do aluno encapsulado.
	 */
	@PostMapping("/report")
	public void registerResult(@RequestBody final StudentResultWrapper wrapper) {
		services.registerResult(wrapper.getStudentResult());
	}
	
	/**
	 * Endpoint responsavel por realizar a reindexacao da base de dados
	 * com resgistros de resultados dos alunos.
	 * 
	 * @param wrapper
	 * 		lista encapsulada dos resultados a ser inseridos.
	 */
	@PostMapping("/reindex")
	public void reindexDatabase(@RequestBody final StudentResultListWrapper wrapper) {
		services.reindexDatabase(wrapper.getResults());
	}
}