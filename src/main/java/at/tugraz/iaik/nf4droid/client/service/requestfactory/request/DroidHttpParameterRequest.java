

package at.tugraz.iaik.nf4droid.client.service.requestfactory.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;

@ServiceName("at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpParameter")
public interface DroidHttpParameterRequest extends RequestContext {

    abstract Request<java.lang.Long> countDroidHttpParameters();

    abstract Request<java.util.List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpParameterProxy>> findAllDroidHttpParameters();

    abstract Request<java.util.List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpParameterProxy>> findDroidHttpParameterEntries(int firstResult, int maxResults);

    abstract Request<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpParameterProxy> findDroidHttpParameter(Long id);

    abstract InstanceRequest<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpParameterProxy, java.lang.Void> persist();

    abstract InstanceRequest<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpParameterProxy, java.lang.Void> remove();
}
