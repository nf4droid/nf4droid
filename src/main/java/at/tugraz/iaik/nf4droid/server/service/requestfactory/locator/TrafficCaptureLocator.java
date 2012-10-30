package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.TrafficCapture;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class TrafficCaptureLocator extends Locator<TrafficCapture, Long> {

    public TrafficCapture create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.TrafficCapture> clazz) {
        return new TrafficCapture();
    }

    public TrafficCapture find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.TrafficCapture> clazz, Long id) {
        return TrafficCapture.findTrafficCapture(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.TrafficCapture> getDomainType() {
        return TrafficCapture.class;
    }

    public Long getId(TrafficCapture trafficCapture) {
        return trafficCapture.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(TrafficCapture trafficCapture) {
        return trafficCapture.getVersion();
    }
}
