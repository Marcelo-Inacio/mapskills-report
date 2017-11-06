/*
 * @(#)ReportController.java 1.0 1 13/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.mapskills.report.application.ReportApplicationServices;
import br.gov.sp.fatec.mapskills.report.indicator.course.StudentsIndicatorByCourse;
import br.gov.sp.fatec.mapskills.report.indicator.course.StudentsIndicatorByCourseSpecification;
import br.gov.sp.fatec.mapskills.report.indicator.institution.StudentsIndicatorByInstitution;
import br.gov.sp.fatec.mapskills.report.indicator.institution.StudentsIndicatorByInstitutionSpecification;
import br.gov.sp.fatec.mapskills.report.indicator.institutionlevel.StudentsIndicatorByInstitutionLevel;
import br.gov.sp.fatec.mapskills.report.indicator.institutionlevel.StudentsIndicatorByInstitutionLevelSpecification;
import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentResultPageWrapper;
import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentResultWrapper;
import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentsIndicatorByCourseWrapper;
import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentsIndicatorByInstitutionLevelWrapper;
import br.gov.sp.fatec.mapskills.report.restapi.wrapper.StudentsIndicatorByInstitutionWrapper;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResult;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResultPredicate;
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
	 * End-point para realizar download do relatorio, chamado nas
	 * interfaces de administrador e mentor.
	 * 
	 * @param filter Filtro para busca do relatorio.
	 * @param response Onde sera escrito o tipo do conteudo.
	 * @return Atraves de stream o relatorio.
	 */
	@GetMapping("/report/download")
	public HttpEntity<byte[]> getReportDownload(final StudentResultPredicate studentResultPredicate, final HttpServletResponse response) {
		final byte[] report = services.download(studentResultPredicate.getPredicate());
	    response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
	    response.setCharacterEncoding("UTF-8");
		return new HttpEntity<>(report, getHttpHeaders());
	}
	
	/**
	 * End-point que recupera um relatorio dos alunos para
	 * visualizacao na interface de relatorio do usuario.
	 */
	@GetMapping("/report/student")
	public StudentResultPageWrapper getStudentReport(final Pageable pageable, final StudentResultPredicate studentResultPredicate) {
		final Page<StudentResult> students = services.getStudentsReport(pageable, studentResultPredicate.getPredicate());
		return new StudentResultPageWrapper(students);
	}	
	
	/** 
	 * Endpoint que expoe servico para recuperar os resultados obtidos de
	 * um aluno, a ser exibido no grafico de radar.
	 * 
	 * @param studentID ID do aluno a ser recuperado.
	 * @return StudentResultWrapper Resultado a ser serializado. 
	 */
	@GetMapping("/report/student/{studentId}")
	public StudentResultWrapper getResult(@PathVariable("studentId") final Long studentId) {	
		final StudentResult result = services.getStudentResultById(studentId);
		return new StudentResultWrapper(result);
	}
	
	/**
	 * 
	 * Expoe endpoint para recuperar os indicadores da quantidade de alunos que
	 * finalizaram e nao finalizaram por grau de instituicao.
	 * Ex.: Superior (Fatec) e Tecnico (Etec)
	 */
	@GetMapping("/report/institution-level")
	public StudentsIndicatorByInstitutionLevelWrapper getInstitutionLevelIndicator(
			final StudentsIndicatorByInstitutionLevelSpecification specification) {
		final List<StudentsIndicatorByInstitutionLevel> indicatorResult = services.getStudentsIndicatorByInstitutionLevel(specification);
		return new StudentsIndicatorByInstitutionLevelWrapper(indicatorResult);
	}
	
	/**
	 * 
	 * Expoe endpoint para
	 */
	//TODO MUDAR CHAMADA DA URI NO FRONT
	@GetMapping("/report/institution")
	public StudentsIndicatorByInstitutionWrapper getInstitutionIndicator(
			final StudentsIndicatorByInstitutionSpecification specification) {
		final List<StudentsIndicatorByInstitution> indicatorResult = services.getStudentsIndicatorByInstitution(specification);
		return new StudentsIndicatorByInstitutionWrapper(indicatorResult);		
	}
	
	/**
	 * Endpoint que recupera os indicadores de alunos que finalizaram
	 * e nao finalizaram o jogo.
	 * indicadores >>> numero de alunos.
	 */
	@GetMapping("/report/institution-courses")
	public StudentsIndicatorByCourseWrapper getInstitutionCoursesIndicator(
			final StudentsIndicatorByCourseSpecification specification) {
		final List<StudentsIndicatorByCourse> courseIndicators = services.getStudentsIndicatorByCourse(specification);
		return new StudentsIndicatorByCourseWrapper(courseIndicators);
	}
	
	private HttpHeaders getHttpHeaders() {
		final HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.add("Content-Disposition", "attachment; filename=report.csv" );
	    return httpHeaders;
	}
}