/*
 * @(#)ReportFacade.java 1.0 1 17/09/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.indicator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.querydsl.core.types.Predicate;

import br.gov.sp.fatec.mapskills.report.indicator.course.StudentsIndicatorByCourse;
import br.gov.sp.fatec.mapskills.report.indicator.course.StudentsIndicatorByCourseRepository;
import br.gov.sp.fatec.mapskills.report.indicator.course.StudentsIndicatorByCourseSpecification;
import br.gov.sp.fatec.mapskills.report.indicator.institution.StudentsIndicatorByInstitution;
import br.gov.sp.fatec.mapskills.report.indicator.institution.StudentsIndicatorByInstitutionRepository;
import br.gov.sp.fatec.mapskills.report.indicator.institution.StudentsIndicatorByInstitutionSpecification;
import br.gov.sp.fatec.mapskills.report.indicator.institutionlevel.StudentsIndicatorByInstitutionLevel;
import br.gov.sp.fatec.mapskills.report.indicator.institutionlevel.StudentsIndicatorByInstitutionLevelRepository;
import br.gov.sp.fatec.mapskills.report.indicator.institutionlevel.StudentsIndicatorByInstitutionLevelSpecification;
import br.gov.sp.fatec.mapskills.report.infra.ReportGenerator;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResult;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResultRepository;
import lombok.AllArgsConstructor;

/**
 * A classe {@link ReportFacade}
 *
 * @author Marcelo
 * @version 1.0 17/09/2017
 */
@Component
@AllArgsConstructor
public class ReportFacade {
		
	private final ReportGenerator reportGenerator;
	private final StudentResultRepository studentResultRepository;
	private final StudentsIndicatorByCourseRepository byCourseRepository;
	private final StudentsIndicatorByInstitutionRepository byInstitutionRepository;
	private final StudentsIndicatorByInstitutionLevelRepository byInstitutionLevelRepository;
	
	public List<StudentsIndicatorByInstitutionLevel> getStudentsIndicatorByInstitutionLevel(
			final StudentsIndicatorByInstitutionLevelSpecification specification) {
		return byInstitutionLevelRepository.findAll(specification);
	}
	
	public List<StudentsIndicatorByInstitution> getInstitutionStudentIndicators(
			final StudentsIndicatorByInstitutionSpecification specification) {
		return byInstitutionRepository.findAll(specification);
	}
	
	/**
	 * Metodo que recupera os indicadores do numero de alunos que finalizaram
	 * e nao finalizaram o jogo por curso de uma instituicao em um semestre.
	 */
	public List<StudentsIndicatorByCourse> getCourseStudentsIndicators(
			final StudentsIndicatorByCourseSpecification specification) {
		return byCourseRepository.findAll(specification);
	}
	
	public byte[] download(final Predicate reportPredicate) {
		final List<StudentResult> studentsReport = studentResultRepository.findAll(reportPredicate);
		return reportGenerator.generate(studentsReport);		
	}
}