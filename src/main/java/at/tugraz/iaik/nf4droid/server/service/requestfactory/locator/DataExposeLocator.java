package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.DataExpose;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class DataExposeLocator extends Locator<DataExpose, Long> {

    public DataExpose create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DataExpose> clazz) {
        return new DataExpose();
    }

    public DataExpose find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DataExpose> clazz, Long id) {
        return DataExpose.findDataExpose(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.DataExpose> getDomainType() {
        return DataExpose.class;
    }

    public Long getId(DataExpose dataExpose) {
        return dataExpose.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(DataExpose dataExpose) {
        return dataExpose.getVersion();
    }
}
