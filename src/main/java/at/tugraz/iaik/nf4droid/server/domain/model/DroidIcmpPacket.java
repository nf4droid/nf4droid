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
public class DroidIcmpPacket {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, optional=false)
	@JoinColumn
	private DroidIpv4Packet ipv4Packet;
	
	private Integer icmpType;
	private Integer code;
	private Integer checksum;
	private Integer icmpId;
	private Integer seq;
	
	public DroidIcmpPacket() {
		super();
		this.ipv4Packet = new DroidIpv4Packet();
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

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new DroidIcmpPacket().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countDroidIcmpPackets() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DroidIcmpPacket o", Long.class).getSingleResult();
    }

	public static List<DroidIcmpPacket> findAllDroidIcmpPackets() {
        return entityManager().createQuery("SELECT o FROM DroidIcmpPacket o", DroidIcmpPacket.class).getResultList();
    }

	public static DroidIcmpPacket findDroidIcmpPacket(Long id) {
        if (id == null) return null;
        return entityManager().find(DroidIcmpPacket.class, id);
    }

	public static List<DroidIcmpPacket> findDroidIcmpPacketEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DroidIcmpPacket o", DroidIcmpPacket.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            DroidIcmpPacket attached = DroidIcmpPacket.findDroidIcmpPacket(this.id);
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
    public DroidIcmpPacket merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DroidIcmpPacket merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public DroidIpv4Packet getIpv4Packet() {
        return this.ipv4Packet;
    }

	public void setIpv4Packet(DroidIpv4Packet ipv4Packet) {
        this.ipv4Packet = ipv4Packet;
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

	public Integer getIcmpId() {
        return this.icmpId;
    }

	public void setIcmpId(Integer icmpId) {
        this.icmpId = icmpId;
    }

	public Integer getSeq() {
        return this.seq;
    }

	public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
