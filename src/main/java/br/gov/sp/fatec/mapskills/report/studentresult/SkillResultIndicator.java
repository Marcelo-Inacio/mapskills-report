/*
 * @(#)SkillResultIndicator.java 1.0 1 21/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.studentresult;

import br.gov.sp.fatec.mapskills.report.studentresult.data.SkillResultIndicatorData;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A classe {@link SkillResultIndicator} representa
 * um dado indicador de uma competencia com seu respectivo
 * valor.
 *
 * @author Marcelo
 * @version 1.0 21/10/2017
 */
@Getter
@AllArgsConstructor
public class SkillResultIndicator {
	
	private final String skillName;
	private final String skillDescription;
	private final Integer total;
	
	@SuppressWarnings("unused")
	private SkillResultIndicator() {
		this(null, null, null);
	}
	
	public SkillResultIndicator(final SkillResultIndicatorData data) {
		this(data.getSkillName(), data.getSkillDescription(), data.getTotal());
	}
}