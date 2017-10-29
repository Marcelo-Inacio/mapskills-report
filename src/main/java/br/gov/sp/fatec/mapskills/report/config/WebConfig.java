/*
 * @(#)WebConfig.java 1.0 1 28/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.gov.sp.fatec.mapskills.report.restapi.ReportPredicateArgumentResolver;

/**
 * A classe {@link WebConfig}
 *
 * @author Marcelo
 * @version 1.0 28/10/2017
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Bean
    public PageableHandlerMethodArgumentResolver pageableResolver() {
        final PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setFallbackPageable(new PageRequest(0, 20));
        return resolver;
    }
	
	@Bean
	public ReportPredicateArgumentResolver predicateResolver() {
		return new ReportPredicateArgumentResolver();
	}
	
	@Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
    	argumentResolvers.add(pageableResolver());
    	argumentResolvers.add(predicateResolver());
    	super.addArgumentResolvers(argumentResolvers);
    }

}