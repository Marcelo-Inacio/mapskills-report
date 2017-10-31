/*
 * @(#)ReportController.java 1.0 1 13/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import br.gov.sp.fatec.mapskills.report.application.ReportApplicationServices;
import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentReportPageWrapper;
import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentResultWrapper;
import br.gov.sp.fatec.mapskills.report.studentreport.StudentResult;
import lombok.AllArgsConstructor;

/**
 * A classe {@link ReportController}
 *
 * @author Marcelo
 * @version 1.0 13/10/2017
 */
@RestController
@AllArgsConstructor
public class ReportController {

	private final ReportApplicationServices services;
	
	/**
	 * End-point para realizar download do relatorio, chamado nas interfaces de administrador e mentor.
	 * 
	 * @param filter Filtro para busca do relatorio.
	 * @param response Onde sera escrito o tipo do conteudo.
	 * @return Atraves de stream o relatorio.
	 */
	@GetMapping("/report/download")
	public HttpEntity<byte[]> getReportDownload(final Predicate reportPredicate, final HttpServletResponse response) {
		final byte[] report = services.download(reportPredicate);
	    response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
	    response.setCharacterEncoding("UTF-8");
		return new HttpEntity<>(report, getHttpHeaders());
	}
	
	/**
	 * End-point que recupera um relatorio dos alunos para
	 * visualizacao na interface de relatorio do usuario.
	 */
	@GetMapping("/report")
	public StudentReportPageWrapper getReportView(final Pageable pageable, final Predicate reportPredicate) {
		final Page<StudentResult> students = services.getStudentsReport(pageable, reportPredicate);
		return new StudentReportPageWrapper(students);
	}
	
	
	/** 
	 * Endpoint que expoe servico para determinar a conclusao do jogo feito pelo aluno
	 * e retornar os resultados obtidos pelo mesmo, a ser exibido na interface de resultados.
	 * 
	 * @param studentID ID do aluno a ser recuperado.
	 * @return StudentResultWrapper Resultado a ser serializado. 
	 */
	@GetMapping(value = "/report/{studentId}")
	public StudentResultWrapper getResult(@PathVariable("studentId") final Long studentId) {	
		final StudentResult result = services.getStudentResultById(studentId);
		return new StudentResultWrapper(result);
	}
	
	private HttpHeaders getHttpHeaders() {
		final HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.add("Content-Disposition", "attachment; filename=report.csv" );
	    return httpHeaders;
	}
}