package at.tugraz.iaik.nf4droid.server.service.rpc;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import at.tugraz.iaik.nf4droid.client.service.rpc.RpcGeneralDataService;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpRequest;
import at.tugraz.iaik.nf4droid.server.domain.service.DomainGeneralDataService;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;
import at.tugraz.iaik.nf4droid.shared.dto.AppSuggestion;
import at.tugraz.iaik.nf4droid.shared.dto.AppVersionSuggestion;
import at.tugraz.iaik.nf4droid.shared.dto.BarChartStatisticsEntry;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficAmountInfo;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Configurable
public class RpcGeneralDataServiceImpl extends RemoteServiceServlet implements
		RpcGeneralDataService {

	private static final Logger LOGGER = Logger
			.getLogger(RpcGeneralDataServiceImpl.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	private DomainGeneralDataService domainDataService;

	public List<BarChartStatisticsEntry> getHttpRequestsHitStatistics(
			final Long trafficCaptureId, final String searchString,
			final int firstResult, final int maxResults,
			final ExposeType exposeFilter, final String groupBy) {
		LOGGER.info("Get http requests hit statistic for capture: "+trafficCaptureId);
		
		return DroidHttpRequest.findHttpRequestsHitStatistics(trafficCaptureId,
				searchString, firstResult, maxResults, exposeFilter, groupBy);
	}

	public List<TrafficAmountInfo> getIpv4CountryTrafficAmount(
			final Long trafficCaptureId) {
		LOGGER.info("Get ipv4 traffic amount per country for capture: "+trafficCaptureId);
		return domainDataService.findIpv4CountryTrafficAmount(trafficCaptureId);
	}

	public TrafficAmountInfo getIpv4CountryUnknownTrafficAmount(
			final Long trafficCaptureId) {
		LOGGER.info("Get ipv4 traffic amount where country is unknown for capture: "+trafficCaptureId);
		return domainDataService
				.findIpv4CountryUnknownTrafficAmount(trafficCaptureId);
	}

	public TrafficAmountInfo getIpv4TotalTrafficAmount(
			final Long trafficCaptureId) {
		LOGGER.info("Get total ipv4 traffic amount for capture: "+trafficCaptureId);
		return domainDataService.findIpv4TotalTrafficAmount(trafficCaptureId);
	}

	public List<AppSuggestion> getAppPackageSuggestions(
			final String appPackage, final Long limit) {
		LOGGER.info("Get app package suggestions for " + appPackage + " limit "
				+ limit + ".");
		return domainDataService.findAppSuggestionWherePackageNameLike(
				appPackage, limit);
	}

	public List<AppVersionSuggestion> findAppVersionsWhereAppPackageEqAndVersionCodeLike(
			final String appPackage, final String appVersionCodeLike,
			final Long limit) {
		LOGGER.info("Get app version suggestions for app package " + appPackage
				+ " where version code like " + appVersionCodeLike + " limit "
				+ limit + ".");
		return domainDataService
				.findAppVersionSuggestionsWhereAppPackageEqAndVersionCodeLike(
						appPackage, appVersionCodeLike, limit);
	}

}
