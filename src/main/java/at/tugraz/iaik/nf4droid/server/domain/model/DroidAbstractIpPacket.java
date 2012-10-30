package at.tugraz.iaik.nf4droid.server.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@MappedSuperclass
public abstract class DroidAbstractIpPacket {

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@JoinColumn
	private DroidEthernetFrame ethernetFrame;

	private String sourceIp;
	
	private Boolean sourceIsLocal;

	private Boolean destIsLocal;
	
	private String destIp;
	
	private String remoteCountry;
	
	private String remoteCity;
	
	private Float remoteLat;
	
	private Float remoteLon;

	public DroidAbstractIpPacket() {
		super();
		this.ethernetFrame = new DroidEthernetFrame();
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

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public DroidEthernetFrame getEthernetFrame() {
        return this.ethernetFrame;
    }

	public void setEthernetFrame(DroidEthernetFrame ethernetFrame) {
        this.ethernetFrame = ethernetFrame;
    }

	public String getSourceIp() {
        return this.sourceIp;
    }

	public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

	public Boolean getSourceIsLocal() {
        return this.sourceIsLocal;
    }

	public void setSourceIsLocal(Boolean sourceIsLocal) {
        this.sourceIsLocal = sourceIsLocal;
    }

	public Boolean getDestIsLocal() {
        return this.destIsLocal;
    }

	public void setDestIsLocal(Boolean destIsLocal) {
        this.destIsLocal = destIsLocal;
    }

	public String getDestIp() {
        return this.destIp;
    }

	public void setDestIp(String destIp) {
        this.destIp = destIp;
    }

	public String getRemoteCountry() {
        return this.remoteCountry;
    }

	public void setRemoteCountry(String remoteCountry) {
        this.remoteCountry = remoteCountry;
    }

	public String getRemoteCity() {
        return this.remoteCity;
    }

	public void setRemoteCity(String remoteCity) {
        this.remoteCity = remoteCity;
    }

	public Float getRemoteLat() {
        return this.remoteLat;
    }

	public void setRemoteLat(Float remoteLat) {
        this.remoteLat = remoteLat;
    }

	public Float getRemoteLon() {
        return this.remoteLon;
    }

	public void setRemoteLon(Float remoteLon) {
        this.remoteLon = remoteLon;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new DroidAbstractIpPacket() {
        }.entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countDroidAbstractIpPackets() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DroidAbstractIpPacket o", Long.class).getSingleResult();
    }

	public static List<DroidAbstractIpPacket> findAllDroidAbstractIpPackets() {
        return entityManager().createQuery("SELECT o FROM DroidAbstractIpPacket o", DroidAbstractIpPacket.class).getResultList();
    }

	public static DroidAbstractIpPacket findDroidAbstractIpPacket(Long id) {
        if (id == null) return null;
        return entityManager().find(DroidAbstractIpPacket.class, id);
    }

	public static List<DroidAbstractIpPacket> findDroidAbstractIpPacketEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DroidAbstractIpPacket o", DroidAbstractIpPacket.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            DroidAbstractIpPacket attached = DroidAbstractIpPacket.findDroidAbstractIpPacket(this.id);
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
    public DroidAbstractIpPacket merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DroidAbstractIpPacket merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
