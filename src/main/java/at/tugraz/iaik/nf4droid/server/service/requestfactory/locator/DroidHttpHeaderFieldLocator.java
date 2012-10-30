package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpHeaderField;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class DroidHttpHeaderFieldLocator extends Locator<DroidHttpHeaderField, Long> {

    public DroidHttpHeaderField create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpHeaderField> clazz) {
        return new DroidHttpHeaderField();
    }

    public DroidHttpHeaderField find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpHeaderField> clazz, Long id) {
        return DroidHttpHeaderField.findDroidHttpHeaderField(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpHeaderField> getDomainType() {
        return DroidHttpHeaderField.class;
    }

    public Long getId(DroidHttpHeaderField droidHttpHeaderField) {
        return droidHttpHeaderField.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(DroidHttpHeaderField droidHttpHeaderField) {
        return droidHttpHeaderField.getVersion();
    }
}
