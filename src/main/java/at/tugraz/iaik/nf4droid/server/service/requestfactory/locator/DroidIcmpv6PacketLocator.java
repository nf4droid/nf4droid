package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpv6Packet;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class DroidIcmpv6PacketLocator extends Locator<DroidIcmpv6Packet, Long> {

    public DroidIcmpv6Packet create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpv6Packet> clazz) {
        return new DroidIcmpv6Packet();
    }

    public DroidIcmpv6Packet find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpv6Packet> clazz, Long id) {
        return DroidIcmpv6Packet.findDroidIcmpv6Packet(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpv6Packet> getDomainType() {
        return DroidIcmpv6Packet.class;
    }

    public Long getId(DroidIcmpv6Packet droidIcmpv6Packet) {
        return droidIcmpv6Packet.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(DroidIcmpv6Packet droidIcmpv6Packet) {
        return droidIcmpv6Packet.getVersion();
    }
}
