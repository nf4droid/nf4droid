package at.tugraz.iaik.nf4droid.client.service.rpc;

import java.util.List;

import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;
import at.tugraz.iaik.nf4droid.shared.dto.AppSuggestion;
import at.tugraz.iaik.nf4droid.shared.dto.AppVersionSuggestion;
import at.tugraz.iaik.nf4droid.shared.dto.BarChartStatisticsEntry;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficAmountInfo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("generalDataService")
public interface RpcGeneralDataService extends RemoteService {

	public List<TrafficAmountInfo> getIpv4CountryTrafficAmount(
			final Long trafficCaptureId);

	public TrafficAmountInfo getIpv4CountryUnknownTrafficAmount(
			final Long trafficCaptureId);

	public TrafficAmountInfo getIpv4TotalTrafficAmount(
			final Long trafficCaptureId);

	public List<AppSuggestion> getAppPackageSuggestions(
			final String appPackage, final Long limit);

	public List<AppVersionSuggestion> findAppVersionsWhereAppPackageEqAndVersionCodeLike(
			final String appPackage, final String appVersionCodeLike,
			final Long limit);
	
	public List<BarChartStatisticsEntry> getHttpRequestsHitStatistics(
			final Long trafficCaptureId, final String searchString,
			final int firstResult, final int maxResults,
			final ExposeType exposeFilter, final String groupBy);

}
