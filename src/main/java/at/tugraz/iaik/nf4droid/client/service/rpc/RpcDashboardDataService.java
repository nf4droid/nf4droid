package at.tugraz.iaik.nf4droid.client.service.rpc;

import java.util.List;

import at.tugraz.iaik.nf4droid.shared.dto.BarChartExposeEntry;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficAmountInfo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dashboardDataService")
public interface RpcDashboardDataService extends RemoteService {

	public List<TrafficAmountInfo> getTrafficRatioForNetworkLayer(
			final Long captureId);

	public List<TrafficAmountInfo> getTrafficRatioForTransportLayer(
			final Long captureId);

	public List<TrafficAmountInfo> getTrafficRatioForPorts(final Long captureId);

	public Long getTotalTrafficLength(final Long trafficCaptureId);

	public List<BarChartExposeEntry> findDataExposureAmount(final Long trafficCaptureId);
	
	public Long coundDataExposures(final Long trafficCaptureId);
}
