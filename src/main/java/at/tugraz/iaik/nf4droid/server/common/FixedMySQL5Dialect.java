package at.tugraz.iaik.nf4droid.server.common;

import java.sql.Types;

import org.apache.log4j.Logger;
import org.hibernate.dialect.MySQL5Dialect;

public class FixedMySQL5Dialect extends MySQL5Dialect {

	public FixedMySQL5Dialect() {
		super();
		LOGGER.info("Using modified MySQL5Dialect.");
	}

	private static final Logger LOGGER = Logger
			.getLogger(FixedMySQL5Dialect.class);

	@Override
	public String getCastTypeName(int code) {
		if (code == Types.BIGINT) {
			LOGGER.info("Using modified MySQL5Dialect for cast of BIGINT.");
			return "signed";
		} 
		else {
			LOGGER.info("Using defautl cast type name in modified MySQL5Dialect.");
			return super.getCastTypeName(code);
		}
	}

}
