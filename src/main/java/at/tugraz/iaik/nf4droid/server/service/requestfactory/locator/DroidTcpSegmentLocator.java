package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpSegment;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class DroidTcpSegmentLocator extends Locator<DroidTcpSegment, Long> {

    public DroidTcpSegment create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpSegment> clazz) {
        return new DroidTcpSegment();
    }

    public DroidTcpSegment find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpSegment> clazz, Long id) {
        return DroidTcpSegment.findDroidTcpSegment(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpSegment> getDomainType() {
        return DroidTcpSegment.class;
    }

    public Long getId(DroidTcpSegment droidTcpSegment) {
        return droidTcpSegment.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(DroidTcpSegment droidTcpSegment) {
        return droidTcpSegment.getVersion();
    }
}
