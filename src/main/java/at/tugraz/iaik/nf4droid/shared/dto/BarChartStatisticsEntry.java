package at.tugraz.iaik.nf4droid.shared.dto;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.mysema.query.annotations.QueryProjection;

public class BarChartStatisticsEntry implements IsSerializable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String value;
	private Long count;

	public BarChartStatisticsEntry() {
		super();
	}

	@QueryProjection
	public BarChartStatisticsEntry(String value, Long count) {
		super();
		this.value = value;
		this.count = count;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}
	
	

}
