package at.tugraz.iaik.nf4droid.client.service.rpc;

import at.tugraz.iaik.nf4droid.shared.dto.Criteria;
import at.tugraz.iaik.nf4droid.shared.dto.TimeValuePair;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficTimeLineData;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficTimelineDataSet;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RpcTimelineDataServiceAsync {

	void getTrafficTimelineStartTimeAndInterval(Long trafficCaptureId,
			AsyncCallback<TimeValuePair> callback);

	void getTrafficTimelineHttpRequestDataset(Long trafficCaptureId,
			int intervalInMs, AsyncCallback<TrafficTimelineDataSet> callback);

	void getTotalTrafficTimelineData(Long trafficCaptureId, int intervalInMs,
			AsyncCallback<TrafficTimeLineData> callback);

	void getTrafficTimelineDataForIpv4ByCriteria(Long trafficCaptureId,
			Integer intervalInMs, Criteria criterias,
			AsyncCallback<TrafficTimeLineData> callback);

}
