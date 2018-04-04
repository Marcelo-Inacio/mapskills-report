/*
 * @(#)ReportGenerator.java 1.0 1 05/11/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.infra;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import br.gov.sp.fatec.mapskills.report.studentresult.StudentResult;
import br.gov.sp.fatec.mapskills.report.studentresult.SkillResultIndicator;
import lombok.AllArgsConstructor;

/**
 * A classe {@link ReportGenerator} responsavel
 * por realizar a geracao do relatorio em formato
 * csv para download.
 *
 * @author Marcelo
 * @version 1.0 05/11/2017
 */
@Component
@AllArgsConstructor
public class ReportGenerator {
	
	private static final String SEMICOLON = ";";
	
	public byte[] generate(final List<StudentResult> studentsReport) {
		final StringBuilder reportBuilder = new StringBuilder();
		generateHeader(reportBuilder, studentsReport);
		studentsReport.stream().forEachOrdered(student -> {
			final StringBuilder studentRow = generateStudentInformation(student);
			for(final SkillResultIndicator studentIndicator : student.getStudentIndicators()) {
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
		
		final List<SkillResultIndicator> indicators = studentsReport.get(0).getStudentIndicators(); 		
		indicators.stream().forEachOrdered(indicator -> defaultHeader.append(String.format("%s;", indicator.getSkillName().toUpperCase())));
		stringBuilder.append(defaultHeader).append("\n");
	}
	
	/**
	 * Metodo responsavel por escrever todas informacoes
	 * basicas do aluno que aparecera no relatorio.
	 */
	private StringBuilder generateStudentInformation(final StudentResult studentReport) {
		final StringBuilder studentInformation = new StringBuilder();
		final String yearSemester = String.format("%d/%d", studentReport.getStartYear(), studentReport.getStartSemester());
		return studentInformation.append(studentReport.getRa()).append(SEMICOLON)
			.append(studentReport.getName()).append(SEMICOLON)
			.append(studentReport.getCourseName()).append(SEMICOLON)
			.append(studentReport.getInstitutionCode()).append(SEMICOLON)
			.append(studentReport.getInstitutionCompany()).append(SEMICOLON)
			.append(yearSemester).append(SEMICOLON);
	}
	
	/**
	 * Metodo responsavel por escrever todos os resultados das competencias
	 * de um aluno gerada pela aplicacao.
	 */
	private void generateResultGame(final SkillResultIndicator studentIndicator, final StringBuilder reportBuilder) {
		reportBuilder.append(ObjectUtils.isEmpty(studentIndicator.getTotal()) ? "N/A" : studentIndicator.getTotal()).append(SEMICOLON);
	}
}