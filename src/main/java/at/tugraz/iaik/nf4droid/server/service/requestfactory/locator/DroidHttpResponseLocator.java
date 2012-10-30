package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpResponse;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class DroidHttpResponseLocator extends Locator<DroidHttpResponse, Long> {

    public DroidHttpResponse create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpResponse> clazz) {
        return new DroidHttpResponse();
    }

    public DroidHttpResponse find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpResponse> clazz, Long id) {
        return DroidHttpResponse.findDroidHttpResponse(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpResponse> getDomainType() {
        return DroidHttpResponse.class;
    }

    public Long getId(DroidHttpResponse droidHttpResponse) {
        return droidHttpResponse.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(DroidHttpResponse droidHttpResponse) {
        return droidHttpResponse.getVersion();
    }
}
