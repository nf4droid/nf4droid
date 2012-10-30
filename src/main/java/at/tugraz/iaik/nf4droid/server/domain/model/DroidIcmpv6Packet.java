package at.tugraz.iaik.nf4droid.server.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
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

@Configurable
@Entity
public class DroidIcmpv6Packet {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, optional=false)
	@JoinColumn
	private DroidIpv6Packet ipv6Packet;
	
	private Integer icmpType;
	private Integer code;
	private Integer checksum;
	
	public DroidIcmpv6Packet() {
		super();
		this.ipv6Packet = new DroidIpv6Packet();
	}
	
	

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public DroidIpv6Packet getIpv6Packet() {
        return this.ipv6Packet;
    }

	public void setIpv6Packet(DroidIpv6Packet ipv6Packet) {
        this.ipv6Packet = ipv6Packet;
    }

	public Integer getIcmpType() {
        return this.icmpType;
    }

	public void setIcmpType(Integer icmpType) {
        this.icmpType = icmpType;
    }

	public Integer getCode() {
        return this.code;
    }

	public void setCode(Integer code) {
        this.code = code;
    }

	public Integer getChecksum() {
        return this.checksum;
    }

	public void setChecksum(Integer checksum) {
        this.checksum = checksum;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new DroidIcmpv6Packet().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countDroidIcmpv6Packets() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DroidIcmpv6Packet o", Long.class).getSingleResult();
    }

	public static List<DroidIcmpv6Packet> findAllDroidIcmpv6Packets() {
        return entityManager().createQuery("SELECT o FROM DroidIcmpv6Packet o", DroidIcmpv6Packet.class).getResultList();
    }

	public static DroidIcmpv6Packet findDroidIcmpv6Packet(Long id) {
        if (id == null) return null;
        return entityManager().find(DroidIcmpv6Packet.class, id);
    }

	public static List<DroidIcmpv6Packet> findDroidIcmpv6PacketEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DroidIcmpv6Packet o", DroidIcmpv6Packet.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            DroidIcmpv6Packet attached = DroidIcmpv6Packet.findDroidIcmpv6Packet(this.id);
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
    public DroidIcmpv6Packet merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DroidIcmpv6Packet merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
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
