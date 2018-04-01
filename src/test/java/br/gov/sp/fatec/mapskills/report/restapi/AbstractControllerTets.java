/*
 * @(#)AbstractControllerTets.java 1.0 1 30/10/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.report.restapi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.ext.hsqldb.HsqldbConnection;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.xml.sax.InputSource;

import br.gov.sp.fatec.mapskills.report.studentresult.StudentResult;
import br.gov.sp.fatec.mapskills.report.studentresult.StudentResultIndicator;
import net.minidev.json.JSONValue;

/**
 * A classe {@link AbstractControllerTets}
 *
 * @author Marcelo
 * @version 1.0 30/10/2017
 */
public abstract class AbstractControllerTets {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Before
	public void before() {
		mongoTemplate.dropCollection(StudentResult.class);
	}
	
	private static IDatabaseConnection databaseConnection;
	private static Connection connection;
	private static boolean isAfterFirstRun;
	private static Logger logger = LoggerFactory.getLogger(AbstractControllerTets.class);

	@BeforeClass
	public static void createDatabase() throws Exception {
		try {
			final Properties properties = loadProperties();
			final String driver = properties.getProperty("datasource.driver");
			final String url = properties.getProperty("datasource.url");
			final String userName = properties.getProperty("datasource.username");
			final String password = properties.getProperty("datasource.password");
			final String schema = properties.getProperty("datasource.schema");

			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, password);
			databaseConnection = new HsqldbConnection(connection, schema);
		} catch (final SQLException exception) {
			throw new RuntimeException(exception.getMessage(), exception);
		} catch (final ClassNotFoundException exception) {
			throw new RuntimeException(exception.getMessage(), exception);
		}
	}

	@Before
	public void setDatabaseUp() throws Exception {
		if (!isAfterFirstRun) {
			runSQLCommands(getDatabaseFile());
		}
		runSQLCommands(getClearDatabaseFile());
		runSQLCommands(getResetSequencesFile());
		runSQLCommands(getDataFile());
		isAfterFirstRun = true;
	}

	@AfterClass
	public static void closeConnection() throws Exception {
		connection.close();
		databaseConnection.close();
	}

	protected void runSQLCommands(final String file) throws Exception {
		if (file != null) {
			final InputStream stream = getSQLInputStream(file);

			final BufferedReader databaseReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			final Throwable localThrowable3 = null;
			try {
				int i = 1;
				String sqlStatement = null;
				while ((sqlStatement = databaseReader.readLine()) != null) {
					if (sqlStatement.startsWith("--")) {
						++i;
					}

					int index = sqlStatement.lastIndexOf(";");
					if (index > -1) {
						sqlStatement = sqlStatement.substring(0, index + 1);
					}
					if (sqlStatement.trim().length() != 0) {
						try {
							connection.createStatement().execute(sqlStatement);
						} catch (final Exception exception) {
							logger.error("Error running command on line " + i + " of file " + file + ": "
									+ exception.getMessage());
							throw new RuntimeException(exception);
						}
					}
					++i;
				}
			} catch (final Throwable localThrowable1) {
			} finally {
				if (databaseReader != null) {
					if (localThrowable3 != null) {
						try {
							databaseReader.close();
						} catch (final Throwable localThrowable2) {
							localThrowable3.addSuppressed(localThrowable2);
						}
					} else {
						databaseReader.close();
					}
				}
			}
		}
	}

	protected IDatabaseConnection getConnection() {
		return databaseConnection;
	}

	protected static IDataSet getDataSet(final String dataset) {
		try {
			final InputSource source = new InputSource(AbstractControllerTets.class.getResourceAsStream(dataset));
			final FlatXmlProducer producer = new FlatXmlProducer(source, false, true);
			return new FlatXmlDataSet(producer);
		} catch (final Exception exception) {
			throw new RuntimeException("Cannot read the dataset file " + dataset + "!", exception);
		}
	}
	
	protected String getJsonAsString(final String path) throws UnsupportedEncodingException, FileNotFoundException {
        final InputStream inputStream = getClass().getResourceAsStream(String.format("/br/gov/sp/fatec/mapskills/report/json/%s", path));
        if (inputStream == null) {
            throw new FileNotFoundException("File " + path + " not found. A file named " + path + " must be present "
                    + "in the src/test/resources folder of the project whose class matches being tested.");
        }
        return JSONValue.parse(new InputStreamReader(inputStream, "UTF-8")).toString();
    }
	
	protected void insertStudentResults(final int resultNumber, final String institutionCode, final String courseCode) {
		final List<StudentResult> results = new LinkedList<>();
		for (int index = 0; index < resultNumber; index++) {
			final String ra = String.format("%s%s17200%d", institutionCode, courseCode, index);
			final StudentResult studentResult = new StudentResult(new Long(index), ra,
					"AlunoTest" + index, courseCode, "Banco De Dados", institutionCode, "Fatec Profº Jessen Vidal",
					"SUPERIOR", 2017, 2, getResultIndicator());
			results.add(studentResult);
		}
		mongoTemplate.insertAll(results);
	}
	
	private List<StudentResultIndicator> getResultIndicator() {
		final Map<String, String> skills = new HashMap<>(5);
		skills.put("Liderança", "Descrição da Habilidade de Liderança.");
		skills.put("Gestão de Tempo", "Descrição da Habilidade de Gestão de Tempo.");
		skills.put("Resiliência", "Descrição da Habilidade de Resiliência.");
		skills.put("Visão de Futuro", "Descrição da Habilidade de Visão de Futuro.");
		skills.put("Comunicação", "Descrição da Habilidade de Comunicação.");
		
		final List<StudentResultIndicator> indicators = new LinkedList<>();
		skills.forEach((name, description) -> {
			indicators.add(new StudentResultIndicator(name, description, 7));
		});
		return indicators;
	}
	
	private static Properties loadProperties() throws Exception {
		final InputStream stream = ClassLoader.getSystemResourceAsStream("br/gov/sp/fatec/mapskills/report/config/datasource.properties");
		if (stream == null) {
			throw new FileNotFoundException(
					"File datasource.properties not found. A file named datasource.properties must be present in the src/test/resources folder of the project whose class is being tested.");
		}

		final Properties properties = new Properties();
		properties.load(stream);
		return properties;
	}
	
	private static InputStream getSQLInputStream(final String fileName) {
		return AbstractControllerTets.class.getResourceAsStream(fileName);
	}
	
	private String getClearDatabaseFile() {
		return "/br/gov/sp/fatec/mapskills/report/database/clear-database.sql";
	}

	private String getResetSequencesFile() {
		return "/br/gov/sp/fatec/mapskills/report/database/reset-sequences.sql";
	}

	private String getDatabaseFile() {
		return "/br/gov/sp/fatec/mapskills/report/database/database-setup.sql";
	}

	private String getDataFile() {
		return "/br/gov/sp/fatec/mapskills/report/database/data.sql";
	}
}