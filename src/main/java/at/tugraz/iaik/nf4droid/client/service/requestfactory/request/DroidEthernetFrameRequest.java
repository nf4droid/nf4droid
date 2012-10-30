

package at.tugraz.iaik.nf4droid.client.service.requestfactory.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;

@ServiceName("at.tugraz.iaik.nf4droid.server.domain.model.DroidEthernetFrame")
public interface DroidEthernetFrameRequest extends RequestContext {

    abstract Request<java.lang.Long> countDroidEthernetFrames();

    abstract Request<java.util.List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidEthernetFrameProxy>> findAllDroidEthernetFrames();

    abstract Request<java.util.List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidEthernetFrameProxy>> findDroidEthernetFrameEntries(int firstResult, int maxResults);

    abstract Request<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidEthernetFrameProxy> findDroidEthernetFrame(Long id);

    abstract InstanceRequest<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidEthernetFrameProxy, java.lang.Void> persist();

    abstract InstanceRequest<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidEthernetFrameProxy, java.lang.Void> remove();
}
