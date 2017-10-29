/*
 * @(#)ReportApplicationServices.java 1.0 1 28/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.application;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.querydsl.core.types.Predicate;

import br.gov.sp.fatec.mapskills.report.studentreport.StudentResult;
import br.gov.sp.fatec.mapskills.report.studentreport.StudentResultIndicator;
import br.gov.sp.fatec.mapskills.report.studentreport.StudentResultRepository;
import lombok.AllArgsConstructor;

/**
 * A classe {@link ReportApplicationServices}
 *
 * @author Marcelo Inacio
 * @version 1.0 28/10/2017
 */
@Component
@AllArgsConstructor
public class ReportApplicationServices {
	
	private static final String SEMICOLON = ";";
	private final StudentResultRepository repository;
	
	/**
	 * Metodo que escreve todas as informacoes sobre os
	 * resultados dos alunos dentro das condicoes do filtro
	 * de pesquisa.
	 * 
	 * @param reportPredicate filtro para pesquisa dos alunos.
	 * @return Os bytes do <b>StringBuilder</b> gerado para download.
	 */
	public byte[] download(final Predicate reportPredicate) {
		final List<StudentResult> studentsReport = repository.findAll(reportPredicate);
		final StringBuilder reportBuilder = new StringBuilder();
		generateHeader(reportBuilder, studentsReport);
		studentsReport.stream().forEachOrdered(student -> {
			final StringBuilder studentRow = generateStudentInformation(student);
			for(final StudentResultIndicator studentIndicator : student.getStudentIndicators()) {
				generateResultGame(studentIndicator, studentRow);
			}
			reportBuilder.append(studentRow.toString());
			reportBuilder.append("\n");
		});
		return reportBuilder.toString().getBytes();
	}
	
	/**
	 * Metodo responsavel por escrever todo o cabecalho do relatorio.
	 * Gerando as competencias dinamicamente.
	 */
	private void generateHeader(final StringBuilder stringBuilder, final List<StudentResult> studentsReport) {
		final StringBuilder defaultHeader = new StringBuilder("RA;ALUNO;CURSO;CODIGO INSTITUICAO;INSTITUICAO;ANO/SEMESTRE;");
		
		if(ObjectUtils.isEmpty(studentsReport)) {
			return;
		}
		
		final List<StudentResultIndicator> indicators = studentsReport.get(0).getStudentIndicators(); 		
		indicators.stream().forEachOrdered(indicator -> defaultHeader.append(String.format("%s;", indicator.getSkillName().toUpperCase())));
		stringBuilder.append(defaultHeader).append("\n");
	}
	
	/**
	 * Metodo responsavel por escrever todas informacoes
	 * basicas do aluno que aparecera no relatorio.
	 */
	private StringBuilder generateStudentInformation(final StudentResult studentReport) {
		final StringBuilder studentInformation = new StringBuilder();
		return studentInformation.append(studentReport.getRa()).append(SEMICOLON)
			.append(studentReport.getName()).append(SEMICOLON)
			.append(studentReport.getCourseName()).append(SEMICOLON)
			.append(studentReport.getInstitutionCode()).append(SEMICOLON)
			.append(studentReport.getInstitutionCompany()).append(SEMICOLON)
			.append(studentReport.getYearSemester()).append(SEMICOLON);
	}
	
	/**
	 * Metodo responsavel por escrever todos os resultados das competencias
	 * de um aluno gerada pela aplicacao.
	 */
	private void generateResultGame(final StudentResultIndicator studentIndicator, final StringBuilder reportBuilder) {
		reportBuilder.append(ObjectUtils.isEmpty(studentIndicator.getTotal()) ? "N/A" : studentIndicator.getTotal()).append(SEMICOLON);
	}

	/**
	 * @param reportPredicate
	 * @return
	 */
	public Page<StudentResult> getStudentsReport(final Pageable pageable, final Predicate reportPredicate) {
		return repository.findAll(reportPredicate, pageable);
	}

	/**
	 * @param studentId
	 * @return
	 */
	public StudentResult getStudentResultById(final Long studentId) {
		return repository.findById(studentId);
	}
}