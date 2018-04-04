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

import com.querydsl.core.types.Predicate;

import br.gov.sp.fatec.mapskills.report.indicator.ReportFacade;
import br.gov.sp.fatec.mapskills.report.indicator.course.StudentsIndicatorByCourse;
import br.gov.sp.fatec.mapskills.report.indicator.course.StudentsIndicatorByInstitutionCodeSpecification;
import br.gov.sp.fatec.mapskills.report.indicator.institution.StudentsIndicatorByInstitution;
import br.gov.sp.fatec.mapskills.report.indicator.institution.StudentsIndicatorByInstitutionLevelSpecification;
import br.gov.sp.fatec.mapskills.report.indicator.institutionlevel.StudentsIndicatorByInstitutionLevel;
import br.gov.sp.fatec.mapskills.report.indicator.institutionlevel.StudentsIndicatorByAllInstitutionLevelSpecification;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResult;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResultRepository;
import br.gov.sp.fatec.mapskills.report.studentresult.data.StudentResultDataRetriever;
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

	private final ReportFacade facade;
	private final StudentResultRepository studentResultRepository;
	private final StudentResultDataRetriever studentResultDataRetriever;
	
	//@PreAuthorize("isFullyAuthenticated()")
	public List<StudentsIndicatorByInstitutionLevel> getStudentsIndicatorByInstitutionLevel(
			final StudentsIndicatorByAllInstitutionLevelSpecification specification) {
		return facade.getStudentsIndicatorByInstitutionLevel(specification);
	}
	
	//@PreAuthorize("isFullyAuthenticated()")
	public List<StudentsIndicatorByInstitution> getStudentsIndicatorByInstitution(
			final StudentsIndicatorByInstitutionLevelSpecification specification) {
		return facade.getInstitutionStudentIndicators(specification);
	}

	//@PreAuthorize("isFullyAuthenticated()")
	public List<StudentsIndicatorByCourse> getStudentsIndicatorByCourse(
			final StudentsIndicatorByInstitutionCodeSpecification specification) {
		return facade.getCourseStudentsIndicators(specification);
	}
	
	/**
	 * Metodo que escreve todas as informacoes sobre os
	 * resultados dos alunos dentro das condicoes do filtro
	 * de pesquisa.
	 * 
	 * @param reportPredicate filtro para pesquisa dos alunos.
	 * @return Os bytes do <b>StringBuilder</b> gerado para download.
	 */
	//@PreAuthorize("isFullyAuthenticated()")
	public byte[] download(final Predicate reportPredicate) {
		return facade.download(reportPredicate);
	}

	//@PreAuthorize("isFullyAuthenticated()")
	public Page<StudentResult> getStudentsReport(final Pageable pageable, final Predicate reportPredicate) {
		return studentResultRepository.findAll(reportPredicate, pageable);
	}

	//@PreAuthorize("isFullyAuthenticated()")
	public StudentResult getStudentResultById(final Long studentId) {
		return studentResultRepository.findById(studentId);
	}
	
	//@PreAuthorize("isFullyAuthenticated()")
	public void registerResult(final StudentResult result) {
		studentResultRepository.save(result);
	}

	/**
	 * Responsavel por realizar a reindexacao do conteudo armazenado
	 * no banco de dados mongo a partir do banco relacional mysql.
	 * 
	 * @param results
	 * 		lista dos resultados a ser inseridos.
	 */
	//@PreAuthorize("isFullyAuthenticated()")
	public void reindexDatabase() {
		final List<StudentResult> results = studentResultDataRetriever.retrieve();
		studentResultRepository.deleteAll();
		studentResultRepository.save(results);
	}
}