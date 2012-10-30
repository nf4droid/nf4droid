package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidUdpPacket;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class DroidUdpPacketLocator extends Locator<DroidUdpPacket, Long> {

    public DroidUdpPacket create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidUdpPacket> clazz) {
        return new DroidUdpPacket();
    }

    public DroidUdpPacket find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidUdpPacket> clazz, Long id) {
        return DroidUdpPacket.findDroidUdpPacket(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.DroidUdpPacket> getDomainType() {
        return DroidUdpPacket.class;
    }

    public Long getId(DroidUdpPacket droidUdpPacket) {
        return droidUdpPacket.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(DroidUdpPacket droidUdpPacket) {
        return droidUdpPacket.getVersion();
    }
}
