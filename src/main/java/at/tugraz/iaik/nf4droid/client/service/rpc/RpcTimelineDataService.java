package at.tugraz.iaik.nf4droid.client.service.rpc;

import at.tugraz.iaik.nf4droid.shared.dto.Criteria;
import at.tugraz.iaik.nf4droid.shared.dto.TimeValuePair;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficTimeLineData;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficTimelineDataSet;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("timelineDataService")
public interface RpcTimelineDataService extends RemoteService {

	
	public TimeValuePair getTrafficTimelineStartTimeAndInterval(Long trafficCaptureId);
	
	public TrafficTimelineDataSet getTrafficTimelineHttpRequestDataset(final Long trafficCaptureId, final int intervalInMs);
	
	public TrafficTimeLineData getTotalTrafficTimelineData(final Long trafficCaptureId, final int intervalInMs);
	
	public TrafficTimeLineData getTrafficTimelineDataForIpv4ByCriteria(
			final Long trafficCaptureId, Integer intervalInMs,
			final Criteria criterias);
}
