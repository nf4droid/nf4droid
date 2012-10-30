package at.tugraz.iaik.nf4droid.server.domain.service;

import java.util.List;

import at.tugraz.iaik.nf4droid.shared.dto.AppSuggestion;
import at.tugraz.iaik.nf4droid.shared.dto.AppVersionSuggestion;
import at.tugraz.iaik.nf4droid.shared.dto.TimeValuePair;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficAmountInfo;

public interface DomainGeneralDataService {

	public List<TrafficAmountInfo> findIpv4CountryTrafficAmount(
			final Long trafficCaptureId);

	public TrafficAmountInfo findIpv4CountryUnknownTrafficAmount(
			final Long trafficCaptureId);

	public TrafficAmountInfo findIpv4TotalTrafficAmount(
			final Long trafficCaptureId);

	public List<AppVersionSuggestion> findAppVersionSuggestionsWhereAppPackageEqAndVersionCodeLike(
			final String appPackage, final String appVersionCodeLike,
			final Long limit);

	public List<AppSuggestion> findAppSuggestionWherePackageNameLike(
			final String appPackageLike, final Long limit);
	
	public List<TimeValuePair> findHttpReqIdTimeWhereHeaderFieldLike(
			final String value, final Long trafficCaptureId);
	
	public List<TimeValuePair> findHttpReqIdTimeWhereParameterLike(
			final String value, final Long trafficCaptureId);
}
