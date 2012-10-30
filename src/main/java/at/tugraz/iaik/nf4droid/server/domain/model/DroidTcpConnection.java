package at.tugraz.iaik.nf4droid.server.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import at.tugraz.iaik.nf4droid.shared.constant.DroidTcpState;

@Configurable
@Entity
public class DroidTcpConnection {

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    private String clientIp;

    private String serverIp;

    private Integer clientPort;

    private Integer serverPort;

    private String serverDomain;

    private String serverCountry;
    
    private String serverCity;
    
    private Float serverLatitude;
    
    private Float serverLongitude;
    
    @Enumerated
    private DroidTcpState state;


	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new DroidTcpConnection().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countDroidTcpConnections() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DroidTcpConnection o", Long.class).getSingleResult();
    }

	public static List<DroidTcpConnection> findAllDroidTcpConnections() {
        return entityManager().createQuery("SELECT o FROM DroidTcpConnection o", DroidTcpConnection.class).getResultList();
    }

	public static DroidTcpConnection findDroidTcpConnection(Long id) {
        if (id == null) return null;
        return entityManager().find(DroidTcpConnection.class, id);
    }

	public static List<DroidTcpConnection> findDroidTcpConnectionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DroidTcpConnection o", DroidTcpConnection.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            DroidTcpConnection attached = DroidTcpConnection.findDroidTcpConnection(this.id);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public DroidTcpConnection merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DroidTcpConnection merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public String getClientIp() {
        return this.clientIp;
    }

	public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

	public String getServerIp() {
        return this.serverIp;
    }

	public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

	public Integer getClientPort() {
        return this.clientPort;
    }

	public void setClientPort(Integer clientPort) {
        this.clientPort = clientPort;
    }

	public Integer getServerPort() {
        return this.serverPort;
    }

	public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

	public String getServerDomain() {
        return this.serverDomain;
    }

	public void setServerDomain(String serverDomain) {
        this.serverDomain = serverDomain;
    }

	public String getServerCountry() {
        return this.serverCountry;
    }

	public void setServerCountry(String serverCountry) {
        this.serverCountry = serverCountry;
    }

	public String getServerCity() {
        return this.serverCity;
    }

	public void setServerCity(String serverCity) {
        this.serverCity = serverCity;
    }

	public Float getServerLatitude() {
        return this.serverLatitude;
    }

	public void setServerLatitude(Float serverLatitude) {
        this.serverLatitude = serverLatitude;
    }

	public Float getServerLongitude() {
        return this.serverLongitude;
    }

	public void setServerLongitude(Float serverLongitude) {
        this.serverLongitude = serverLongitude;
    }

	public DroidTcpState getState() {
        return this.state;
    }

	public void setState(DroidTcpState state) {
        this.state = state;
    }

	@Version
    @Column(name = "version")
    private Integer version;

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }
}
