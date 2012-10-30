package at.tugraz.iaik.nf4droid.client.service.rpc;

import java.util.List;

import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;
import at.tugraz.iaik.nf4droid.shared.dto.AppSuggestion;
import at.tugraz.iaik.nf4droid.shared.dto.AppVersionSuggestion;
import at.tugraz.iaik.nf4droid.shared.dto.BarChartStatisticsEntry;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficAmountInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RpcGeneralDataServiceAsync {

	void getIpv4CountryTrafficAmount(Long trafficCaptureId,
			AsyncCallback<List<TrafficAmountInfo>> callback);

	void getIpv4CountryUnknownTrafficAmount(Long trafficCaptureId,
			AsyncCallback<TrafficAmountInfo> callback);

	void getAppPackageSuggestions(String appPackage, Long limit,
			AsyncCallback<List<AppSuggestion>> callback);

	void findAppVersionsWhereAppPackageEqAndVersionCodeLike(String appPackage,
			String appVersionCodeLike, Long limit,
			AsyncCallback<List<AppVersionSuggestion>> callback);

	void getIpv4TotalTrafficAmount(Long trafficCaptureId,
			AsyncCallback<TrafficAmountInfo> callback);

	void getHttpRequestsHitStatistics(Long trafficCaptureId,
			String searchString, int firstResult, int maxResults,
			ExposeType exposeFilter, String groupBy,
			AsyncCallback<List<BarChartStatisticsEntry>> callback);

	
	
}
