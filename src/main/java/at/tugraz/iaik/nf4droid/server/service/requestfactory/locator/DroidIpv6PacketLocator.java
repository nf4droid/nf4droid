package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv6Packet;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class DroidIpv6PacketLocator extends Locator<DroidIpv6Packet, Long> {

    public DroidIpv6Packet create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv6Packet> clazz) {
        return new DroidIpv6Packet();
    }

    public DroidIpv6Packet find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv6Packet> clazz, Long id) {
        return DroidIpv6Packet.findDroidIpv6Packet(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv6Packet> getDomainType() {
        return DroidIpv6Packet.class;
    }

    public Long getId(DroidIpv6Packet droidIpv6Packet) {
        return droidIpv6Packet.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(DroidIpv6Packet droidIpv6Packet) {
        return droidIpv6Packet.getVersion();
    }
}
