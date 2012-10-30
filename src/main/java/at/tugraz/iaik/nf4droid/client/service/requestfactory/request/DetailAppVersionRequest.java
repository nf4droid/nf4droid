package at.tugraz.iaik.nf4droid.client.service.requestfactory.request;

import java.util.List;

import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.AppVersionProxy;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServiceName;

@ServiceName("at.tugraz.iaik.nf4droid.server.domain.model.AppVersion")
public interface DetailAppVersionRequest extends AppVersionRequest {

	abstract Request<List<AppVersionProxy>> findAppVersionsForAppByVersionCodeOrVerisonNameLikeLimitOrderBy(
			final Long appId, final String searchString, final int firstResult,
			final int maxResults, final List<String> orderByNames,
			final List<Boolean> orderByAscValues);

	abstract Request<Long> countAppVersionsForAppByVersionCodeOrVersionNameLike(
			final Long appId, final String searchString);

}
