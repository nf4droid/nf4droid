package at.tugraz.iaik.nf4droid.shared.dto;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TrafficTimeLineData implements Serializable, IsSerializable {

	private static final long serialVersionUID = 1L;

	private List<TimeValuePair> timeValueList;
	private Integer intervalInMs;

	public TrafficTimeLineData() {
		super();
	}

	public TrafficTimeLineData(List<TimeValuePair> timeValueList,
			Integer intervalInMs) {
		super();
		this.timeValueList = timeValueList;
		this.intervalInMs = intervalInMs;
	}

	/**
	 * @return the valueList
	 */
	public List<TimeValuePair> getValueList() {
		return timeValueList;
	}

	/**
	 * @return the startTime
	 */
	public Long getStartTime() {
		if (timeValueList != null && !timeValueList.isEmpty()) {
			return timeValueList.get(0).getTime().getTime();
		}
		return null;
	}

	/**
	 * @return the endTime
	 */
	public Long getEndTime() {
		if (timeValueList != null && !timeValueList.isEmpty()) {
			return timeValueList.get(timeValueList.size() - 1).getTime()
					.getTime();
		}
		return null;
	}

	/**
	 * @return the intervalInMs
	 */
	public Integer getIntervalInMs() {
		return intervalInMs;
	}

}
