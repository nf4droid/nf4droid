package at.tugraz.iaik.nf4droid.server.common;

import java.math.BigInteger;

import org.apache.log4j.Logger;

import com.mysema.query.sql.MySQLTemplates;

public class FixedMySQLTemplates extends MySQLTemplates {

	private static final Logger LOGGER = Logger
			.getLogger(FixedMySQLTemplates.class);

	public FixedMySQLTemplates() {
		super();
		LOGGER.info("Using fixed MySQL Template in QueryDSL (zero args constructor).");
		addClass2TypeMappings("unsigned", BigInteger.class);
		LOGGER.info("Adding BitIntger as unsigned.");
	}

}
