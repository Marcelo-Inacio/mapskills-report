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
import br.gov.sp.fatec.mapskills.report.indicator.course.StudentsIndicatorByInstitutionCodeRepository;
import br.gov.sp.fatec.mapskills.report.indicator.course.StudentsIndicatorByInstitutionCodeSpecification;
import br.gov.sp.fatec.mapskills.report.indicator.institution.StudentsIndicatorByInstitution;
import br.gov.sp.fatec.mapskills.report.indicator.institution.StudentsIndicatorByInstitutionLevelRepository;
import br.gov.sp.fatec.mapskills.report.indicator.institution.StudentsIndicatorByInstitutionLevelSpecification;
import br.gov.sp.fatec.mapskills.report.indicator.institutionlevel.StudentsIndicatorByInstitutionLevel;
import br.gov.sp.fatec.mapskills.report.indicator.institutionlevel.StudentsIndicatorByAllInstitutionLevelRepository;
import br.gov.sp.fatec.mapskills.report.indicator.institutionlevel.StudentsIndicatorByAllInstitutionLevelSpecification;
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
	
	private final StudentsIndicatorByInstitutionCodeRepository byInstitutionCodeRepository;
	private final StudentsIndicatorByInstitutionLevelRepository byInstitutionLevelRepository;
	private final StudentsIndicatorByAllInstitutionLevelRepository byAllInstitutionLevelRepository;
	
	public List<StudentsIndicatorByInstitutionLevel> getStudentsIndicatorByInstitutionLevel(
			final StudentsIndicatorByAllInstitutionLevelSpecification specification) {
		return byAllInstitutionLevelRepository.findAll(specification);
	}
	
	public List<StudentsIndicatorByInstitution> getInstitutionStudentIndicators(
			final StudentsIndicatorByInstitutionLevelSpecification specification) {
		return byInstitutionLevelRepository.findAll(specification);
	}
	
	/**
	 * Metodo que recupera os indicadores do numero de alunos que finalizaram
	 * e nao finalizaram o jogo por curso de uma instituicao em um semestre.
	 */
	public List<StudentsIndicatorByCourse> getCourseStudentsIndicators(
			final StudentsIndicatorByInstitutionCodeSpecification specification) {
		return byInstitutionCodeRepository.findAll(specification);
	}
	
	public byte[] download(final Predicate reportPredicate) {
		final List<StudentResult> studentsReport = studentResultRepository.findAll(reportPredicate);
		return reportGenerator.generate(studentsReport);		
	}
}