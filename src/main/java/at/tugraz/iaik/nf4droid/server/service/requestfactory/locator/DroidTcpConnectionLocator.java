package at.tugraz.iaik.nf4droid.server.service.requestfactory.locator;

import org.springframework.stereotype.Component;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpConnection;

import com.google.web.bindery.requestfactory.shared.Locator;

@Component
public class DroidTcpConnectionLocator extends Locator<DroidTcpConnection, Long> {

    public DroidTcpConnection create(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpConnection> clazz) {
        return new DroidTcpConnection();
    }

    public DroidTcpConnection find(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpConnection> clazz, Long id) {
        return DroidTcpConnection.findDroidTcpConnection(id);
    }

    public Class<at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpConnection> getDomainType() {
        return DroidTcpConnection.class;
    }

    public Long getId(DroidTcpConnection droidTcpConnection) {
        return droidTcpConnection.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(DroidTcpConnection droidTcpConnection) {
        return droidTcpConnection.getVersion();
    }
}
