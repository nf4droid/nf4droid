package at.tugraz.iaik.nf4droid.client.service.requestfactory.request;

import java.util.List;

import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpRequestProxy;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServiceName;

@ServiceName("at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpRequest")
public interface DetailDroidHttpReqRequest extends DroidHttpRequestRequest {
	abstract Request<List<DroidHttpRequestProxy>> findHttpRequestsForCaptureByFieldsLikeLimitOrderBy(
			final Long trafficCaptureId, final String searchString,
			final int firstResult, final int maxResults,
			final List<String> orderByNames,
			final List<Boolean> orderByAscValues);

	abstract Request<Long> countHttpRequestsForCaptureByFieldsLike(
			final Long trafficCaptureId, final String searchString);
	
	abstract Request<List<DroidHttpRequestProxy>> findHttpRequestsForCaptureByFieldsLikeFilterLimitOrderBy(
			final Long trafficCaptureId, final String searchString,
			final int firstResult, final int maxResults,
			final List<String> orderByNames,
			final List<Boolean> orderByAscValues, final ExposeType exposeFilter);
	
	abstract Request<Long> countHttpRequestsForCaptureByFieldsLikeFilter(
			final Long trafficCaptureId, final String searchString, final ExposeType exposeFilter);
}
