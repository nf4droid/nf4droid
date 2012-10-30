package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidEthernetFrame;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class DroidEthernetFrameLocator extends Locator<DroidEthernetFrame, Long> {

    public DroidEthernetFrame create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidEthernetFrame> clazz) {
        return new DroidEthernetFrame();
    }

    public DroidEthernetFrame find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidEthernetFrame> clazz, Long id) {
        return DroidEthernetFrame.findDroidEthernetFrame(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.DroidEthernetFrame> getDomainType() {
        return DroidEthernetFrame.class;
    }

    public Long getId(DroidEthernetFrame droidEthernetFrame) {
        return droidEthernetFrame.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(DroidEthernetFrame droidEthernetFrame) {
        return droidEthernetFrame.getVersion();
    }
}
