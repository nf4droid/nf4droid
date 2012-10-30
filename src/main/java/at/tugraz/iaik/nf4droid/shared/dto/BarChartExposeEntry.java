package at.tugraz.iaik.nf4droid.shared.dto;

import java.io.Serializable;

import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.mysema.query.annotations.QueryProjection;

public class BarChartExposeEntry implements Serializable, IsSerializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long value;
	private ExposeType exposeType;

	public BarChartExposeEntry() {
		super();
	}

	@QueryProjection
	public BarChartExposeEntry(Long value, ExposeType exposeType) {
		super();
		this.value = value;
		this.exposeType = exposeType;
	}
	
	/**
	 * @return the value
	 */
	public Long getValue() {
		return value;
	}

	/**
	 * @return the exposeType
	 */
	public ExposeType getExposeType() {
		return exposeType;
	}
	
	
}
