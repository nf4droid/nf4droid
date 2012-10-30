package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidPcapPacket;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class DroidPcapPacketLocator extends Locator<DroidPcapPacket, Long> {

    public DroidPcapPacket create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidPcapPacket> clazz) {
        return new DroidPcapPacket();
    }

    public DroidPcapPacket find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidPcapPacket> clazz, Long id) {
        return DroidPcapPacket.findDroidPcapPacket(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.DroidPcapPacket> getDomainType() {
        return DroidPcapPacket.class;
    }

    public Long getId(DroidPcapPacket droidPcapPacket) {
        return droidPcapPacket.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(DroidPcapPacket droidPcapPacket) {
        return droidPcapPacket.getVersion();
    }
}
