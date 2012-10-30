package at.tugraz.iaik.nf4droid.client.service.rpc;

import java.util.List;

import at.tugraz.iaik.nf4droid.shared.dto.BarChartExposeEntry;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficAmountInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RpcDashboardDataServiceAsync {

	void getTrafficRatioForNetworkLayer(Long captureId,
			AsyncCallback<List<TrafficAmountInfo>> callback);

	void getTrafficRatioForTransportLayer(Long captureId,
			AsyncCallback<List<TrafficAmountInfo>> callback);

	void getTrafficRatioForPorts(Long captureId,
			AsyncCallback<List<TrafficAmountInfo>> callback);

	void getTotalTrafficLength(Long trafficCaptureId,
			AsyncCallback<Long> callback);
	
	void findDataExposureAmount(Long trafficCaptureId,
			AsyncCallback<List<BarChartExposeEntry>> callback);

	void coundDataExposures(Long trafficCaptureId, AsyncCallback<Long> callback);
}
