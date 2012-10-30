package at.tugraz.iaik.nf4droid.client.service.requestfactory.request;

import java.util.List;

import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.AppProxy;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServiceName;

@ServiceName("at.tugraz.iaik.nf4droid.server.domain.model.App")
public interface DetailAppRequest extends AppRequest {
	
	abstract Request<List<AppProxy>> findAppsByAppNameOrAppPackageLikeLimitOrderBy(
			String searchString, int firstResult, int maxResults,
			List<String> orderByNames, List<Boolean> orderByAscValues);
	
	abstract Request<Long> countAppsByAppNameOrAppPackageLike(
			String searchString);
}
