package at.tugraz.iaik.nf4droid.shared.dto;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TrafficTimelineDataSet implements Serializable, IsSerializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TrafficTimeLineData timelineData;
	private List<Expose> epxose;

	public TrafficTimelineDataSet() {
		super();
	}

	public TrafficTimelineDataSet(TrafficTimeLineData timelineData,
			List<Expose> epxose) {
		super();
		this.timelineData = timelineData;
		this.epxose = epxose;
	}

	/**
	 * @return the timelineData
	 */
	public TrafficTimeLineData getTimelineData() {
		return timelineData;
	}

	/**
	 * @param timelineData
	 *            the timelineData to set
	 */
	public void setTimelineData(TrafficTimeLineData timelineData) {
		this.timelineData = timelineData;
	}

	/**
	 * @return the epxose
	 */
	public List<Expose> getEpxose() {
		return epxose;
	}

	/**
	 * @param epxose
	 *            the epxose to set
	 */
	public void setEpxose(List<Expose> epxose) {
		this.epxose = epxose;
	}

}
