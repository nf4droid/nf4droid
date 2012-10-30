package at.tugraz.iaik.nf4droid.shared.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.mysema.query.annotations.QueryProjection;

public class TimeValuePair implements Serializable, IsSerializable {

	private static final long serialVersionUID = 1L;
	
	private Date time;
	private Long value;
	
	public TimeValuePair() {
		super();
	}
	
	@QueryProjection
	public TimeValuePair(Date time, Long value) {
		super();
		this.time = time;
		this.value = value;
	}
	
	public TimeValuePair(BigInteger time, Long value) {
		super();
		this.time = new Date(time.longValue());
		this.value = value;
	}
	
	@QueryProjection
	public TimeValuePair(Long time, Long value) {
		super();
		this.time = new Date(time);
		this.value = value;
	}
	
	public Date getTime() {
		return time;
	}
	public Long getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "TimeValuePair [" + (time != null ? "time=" + time + ", " : "")
				+ "value=" + value + "]";
	}
}

