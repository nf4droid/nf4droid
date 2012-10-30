package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpPacket;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class DroidIcmpPacketLocator extends Locator<DroidIcmpPacket, Long> {

    public DroidIcmpPacket create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpPacket> clazz) {
        return new DroidIcmpPacket();
    }

    public DroidIcmpPacket find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpPacket> clazz, Long id) {
        return DroidIcmpPacket.findDroidIcmpPacket(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpPacket> getDomainType() {
        return DroidIcmpPacket.class;
    }

    public Long getId(DroidIcmpPacket droidIcmpPacket) {
        return droidIcmpPacket.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(DroidIcmpPacket droidIcmpPacket) {
        return droidIcmpPacket.getVersion();
    }
}
