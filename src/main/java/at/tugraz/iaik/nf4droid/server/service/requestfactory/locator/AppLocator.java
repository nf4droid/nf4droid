package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.App;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class AppLocator extends Locator<App, Long> {

    public App create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.App> clazz) {
        return new App();
    }

    public App find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.App> clazz, Long id) {
        return App.findApp(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.App> getDomainType() {
        return App.class;
    }

    public Long getId(App app) {
        return app.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(App app) {
        return app.getVersion();
    }
}
