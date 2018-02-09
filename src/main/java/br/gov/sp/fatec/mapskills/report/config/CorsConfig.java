/*
 * @(#)CorsConfig.java 1.0 1 09/02/2018
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * A classe {@link CorsConfig} tem a função de adicionar as origens ao
 * qual são permitidas realizar requisições.
 * 
 * <p>
 * De acordo com a documentação do Spring
 * (https://spring.io/blog/2015/06/08/cors-support-in-spring-framework)
 * </p>
 *
 * @author Marcelo
 * @version 1.0 09/02/2018
 */
@Configuration
@PropertySource("classpath:/br/gov/sp/fatec/mapskills/report/security/security.properties")
public class CorsConfig {
	
	private final String[] allowedOrigins;
	
	public CorsConfig(@Value("${allowed.origins}") final String allowedOrigins) {
		this.allowedOrigins = allowedOrigins.split(",");
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(final CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(allowedOrigins);
            }
        };
    }
}