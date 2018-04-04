/*
 * @(#)MongoConfig.java 1.0 1 19/09/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import br.gov.sp.fatec.mapskills.report.studentresult.StudentResultRepository;

/**
 * A classe {@link MongoConfig} representa a configuracao do mongodb da aplicacao.
 *
 * @author Marcelo
 * @version 1.0 19/09/2017
 */
@Configuration
@Profile({"local", "azure-qas", "azure-prd"})
@EnableMongoRepositories(basePackageClasses = StudentResultRepository.class)
public class MongoConfig extends AbstractMongoConfiguration{
	
	@Value("${spring.data.mongodb.host}")
	private String host;
	
	@Value("${spring.data.mongodb.port}")
	private Integer port;
	
	@Value("${spring.data.mongodb.database}")
	private String database;
	
	@Override
    protected String getDatabaseName() {
        return database;
    }
  
    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(host, port);
    }
  
    @Override
    protected List<String> getMappingBasePackages() {
        return Arrays.asList("br.gov.sp.fatec.mapskills.report.studentresult");
    }
}