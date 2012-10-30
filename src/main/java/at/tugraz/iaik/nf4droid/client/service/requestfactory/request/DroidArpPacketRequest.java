

package at.tugraz.iaik.nf4droid.client.service.requestfactory.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;

@ServiceName("at.tugraz.iaik.nf4droid.server.domain.model.DroidArpPacket")
public interface DroidArpPacketRequest extends RequestContext {

    abstract Request<java.lang.Long> countDroidArpPackets();

    abstract Request<java.util.List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidArpPacketProxy>> findAllDroidArpPackets();

    abstract Request<java.util.List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidArpPacketProxy>> findDroidArpPacketEntries(int firstResult, int maxResults);

    abstract Request<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidArpPacketProxy> findDroidArpPacket(Long id);

    abstract InstanceRequest<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidArpPacketProxy, java.lang.Void> persist();

    abstract InstanceRequest<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidArpPacketProxy, java.lang.Void> remove();
}
