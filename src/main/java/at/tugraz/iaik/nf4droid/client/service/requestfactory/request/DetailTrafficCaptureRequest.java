package at.tugraz.iaik.nf4droid.client.service.requestfactory.request;

import java.util.List;

import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.TrafficCaptureProxy;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServiceName;

@ServiceName("at.tugraz.iaik.nf4droid.server.domain.model.TrafficCapture")
public interface DetailTrafficCaptureRequest extends TrafficCaptureRequest {

	abstract Request<List<TrafficCaptureProxy>> findTrafficCapturesForAppVersionByDateAddedOrDescriptionLikeLimitOrderBy(
			final Long appVersionId, final String searchString,
			final int firstResult, final int maxResults,
			final List<String> orderByNames,
			final List<Boolean> orderByAscValues);

	abstract Request<Long> countTrafficCapturesForAppVersionByDateAddedOrDescriptionLike(
			final Long appVersionId, final String searchString);

}
