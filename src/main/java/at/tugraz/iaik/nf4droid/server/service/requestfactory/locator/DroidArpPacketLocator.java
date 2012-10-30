package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidArpPacket;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class DroidArpPacketLocator extends Locator<DroidArpPacket, Long> {

    public DroidArpPacket create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidArpPacket> clazz) {
        return new DroidArpPacket();
    }

    public DroidArpPacket find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidArpPacket> clazz, Long id) {
        return DroidArpPacket.findDroidArpPacket(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.DroidArpPacket> getDomainType() {
        return DroidArpPacket.class;
    }

    public Long getId(DroidArpPacket droidArpPacket) {
        return droidArpPacket.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(DroidArpPacket droidArpPacket) {
        return droidArpPacket.getVersion();
    }
}
