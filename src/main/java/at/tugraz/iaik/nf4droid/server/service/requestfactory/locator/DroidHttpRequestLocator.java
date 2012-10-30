package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpRequest;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class DroidHttpRequestLocator extends Locator<DroidHttpRequest, Long> {

    public DroidHttpRequest create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpRequest> clazz) {
        return new DroidHttpRequest();
    }

    public DroidHttpRequest find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpRequest> clazz, Long id) {
        return DroidHttpRequest.findDroidHttpRequest(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpRequest> getDomainType() {
        return DroidHttpRequest.class;
    }

    public Long getId(DroidHttpRequest droidHttpRequest) {
        return droidHttpRequest.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(DroidHttpRequest droidHttpRequest) {
        return droidHttpRequest.getVersion();
    }
}
