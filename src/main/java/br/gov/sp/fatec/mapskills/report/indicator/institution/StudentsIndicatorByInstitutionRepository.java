/*
 * @(#)StudentsIndicatorByInstitutionRepository.java 1.0 1 04/11/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.indicator.institution;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

/**
 * A classe {@link StudentsIndicatorByInstitutionRepository}
 *
 * @author Marcelo
 * @version 1.0 04/11/2017
 */
public interface StudentsIndicatorByInstitutionRepository
		extends Repository<StudentsIndicatorByInstitution, Long>,
		JpaSpecificationExecutor<StudentsIndicatorByInstitution>{
}