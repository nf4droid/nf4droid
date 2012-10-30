package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv4Packet;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class DroidIpv4PacketLocator extends Locator<DroidIpv4Packet, Long> {

    public DroidIpv4Packet create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv4Packet> clazz) {
        return new DroidIpv4Packet();
    }

    public DroidIpv4Packet find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv4Packet> clazz, Long id) {
        return DroidIpv4Packet.findDroidIpv4Packet(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv4Packet> getDomainType() {
        return DroidIpv4Packet.class;
    }

    public Long getId(DroidIpv4Packet droidIpv4Packet) {
        return droidIpv4Packet.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(DroidIpv4Packet droidIpv4Packet) {
        return droidIpv4Packet.getVersion();
    }
}
