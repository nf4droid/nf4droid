

package at.tugraz.iaik.nf4droid.client.service.requestfactory.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;

@ServiceName("at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv6Packet")
public interface DroidIpv6PacketRequest extends RequestContext {

    abstract Request<java.lang.Long> countDroidIpv6Packets();

    abstract Request<java.util.List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidIpv6PacketProxy>> findAllDroidIpv6Packets();

    abstract Request<java.util.List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidIpv6PacketProxy>> findDroidIpv6PacketEntries(int firstResult, int maxResults);

    abstract Request<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidIpv6PacketProxy> findDroidIpv6Packet(Long id);

    abstract InstanceRequest<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidIpv6PacketProxy, java.lang.Void> persist();

    abstract InstanceRequest<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidIpv6PacketProxy, java.lang.Void> remove();
}
