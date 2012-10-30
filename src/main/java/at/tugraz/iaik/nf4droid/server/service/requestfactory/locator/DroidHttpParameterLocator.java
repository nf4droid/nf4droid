package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpParameter;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class DroidHttpParameterLocator extends Locator<DroidHttpParameter, Long> {

    public DroidHttpParameter create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpParameter> clazz) {
        return new DroidHttpParameter();
    }

    public DroidHttpParameter find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpParameter> clazz, Long id) {
        return DroidHttpParameter.findDroidHttpParameter(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpParameter> getDomainType() {
        return DroidHttpParameter.class;
    }

    public Long getId(DroidHttpParameter droidHttpParameter) {
        return droidHttpParameter.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(DroidHttpParameter droidHttpParameter) {
        return droidHttpParameter.getVersion();
    }
}
