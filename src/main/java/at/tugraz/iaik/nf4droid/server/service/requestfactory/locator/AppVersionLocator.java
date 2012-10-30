package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.AppVersion;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class AppVersionLocator extends Locator<AppVersion, Long> {

    public AppVersion create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.AppVersion> clazz) {
        return new AppVersion();
    }

    public AppVersion find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.AppVersion> clazz, Long id) {
        return AppVersion.findAppVersion(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.AppVersion> getDomainType() {
        return AppVersion.class;
    }

    public Long getId(AppVersion appVersion) {
        return appVersion.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(AppVersion appVersion) {
        return appVersion.getVersion();
    }
}
