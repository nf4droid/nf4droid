package at.tugraz.iaik.nf4droid.server.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import at.tugraz.iaik.nf4droid.shared.constant.DroidEthernetType;

@Configurable
@Entity
public class DroidEthernetFrame {

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, optional=false)
	@JoinColumn
	private DroidPcapPacket pcapPacket;
	
	private String sourceMac;

    private String destMac;

    @Enumerated
    private DroidEthernetType type;

	public DroidEthernetFrame() {
		super();
		pcapPacket = new DroidPcapPacket();
	}
	

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new DroidEthernetFrame().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countDroidEthernetFrames() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DroidEthernetFrame o", Long.class).getSingleResult();
    }

	public static List<DroidEthernetFrame> findAllDroidEthernetFrames() {
        return entityManager().createQuery("SELECT o FROM DroidEthernetFrame o", DroidEthernetFrame.class).getResultList();
    }

	public static DroidEthernetFrame findDroidEthernetFrame(Long id) {
        if (id == null) return null;
        return entityManager().find(DroidEthernetFrame.class, id);
    }

	public static List<DroidEthernetFrame> findDroidEthernetFrameEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DroidEthernetFrame o", DroidEthernetFrame.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            DroidEthernetFrame attached = DroidEthernetFrame.findDroidEthernetFrame(this.id);
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
    public DroidEthernetFrame merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DroidEthernetFrame merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public DroidPcapPacket getPcapPacket() {
        return this.pcapPacket;
    }

	public void setPcapPacket(DroidPcapPacket pcapPacket) {
        this.pcapPacket = pcapPacket;
    }

	public String getSourceMac() {
        return this.sourceMac;
    }

	public void setSourceMac(String sourceMac) {
        this.sourceMac = sourceMac;
    }

	public String getDestMac() {
        return this.destMac;
    }

	public void setDestMac(String destMac) {
        this.destMac = destMac;
    }

	public DroidEthernetType getType() {
        return this.type;
    }

	public void setType(DroidEthernetType type) {
        this.type = type;
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
}
