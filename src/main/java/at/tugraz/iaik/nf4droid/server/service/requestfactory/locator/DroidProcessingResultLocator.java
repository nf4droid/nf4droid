package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidProcessingResult;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class DroidProcessingResultLocator extends Locator<DroidProcessingResult, Long> {

    public DroidProcessingResult create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidProcessingResult> clazz) {
        return new DroidProcessingResult();
    }

    public DroidProcessingResult find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidProcessingResult> clazz, Long id) {
        return DroidProcessingResult.findDroidProcessingResult(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.DroidProcessingResult> getDomainType() {
        return DroidProcessingResult.class;
    }

    public Long getId(DroidProcessingResult droidProcessingResult) {
        return droidProcessingResult.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(DroidProcessingResult droidProcessingResult) {
        return droidProcessingResult.getVersion();
    }
}
