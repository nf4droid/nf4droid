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
public class DroidUdpPacket {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	public enum IpVersion {IPV4,IPV6};
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, optional=true)
	@JoinColumn
	private DroidIpv4Packet ipv4Packet;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, optional=true)
	@JoinColumn
	private DroidIpv6Packet ipv6Packet;
	
	private Integer length;
	private Integer checksum;
	private Integer destPort;
    private Integer sourcePort;
	
	/**
	 * 
	 * @param select ip packet using DroidTcpSegment.IpVersion.IPV4 or DroidTcpSegment.IpVersion.IPV6
	 */
	public DroidUdpPacket(IpVersion ipVersion) {
		super();
		if (ipVersion == IpVersion.IPV6) {
			this.ipv6Packet = new DroidIpv6Packet();
		} else {
			this.ipv4Packet = new DroidIpv4Packet();
		}
	}

	@Version
    @Column(name = "version")
    private Integer version;

	public DroidUdpPacket() {
        super();
    }

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

	public DroidIpv4Packet getIpv4Packet() {
        return this.ipv4Packet;
    }

	public void setIpv4Packet(DroidIpv4Packet ipv4Packet) {
        this.ipv4Packet = ipv4Packet;
    }

	public DroidIpv6Packet getIpv6Packet() {
        return this.ipv6Packet;
    }

	public void setIpv6Packet(DroidIpv6Packet ipv6Packet) {
        this.ipv6Packet = ipv6Packet;
    }

	public Integer getLength() {
        return this.length;
    }

	public void setLength(Integer length) {
        this.length = length;
    }

	public Integer getChecksum() {
        return this.checksum;
    }

	public void setChecksum(Integer checksum) {
        this.checksum = checksum;
    }

	public Integer getDestPort() {
        return this.destPort;
    }

	public void setDestPort(Integer destPort) {
        this.destPort = destPort;
    }

	public Integer getSourcePort() {
        return this.sourcePort;
    }

	public void setSourcePort(Integer sourcePort) {
        this.sourcePort = sourcePort;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new DroidUdpPacket().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countDroidUdpPackets() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DroidUdpPacket o", Long.class).getSingleResult();
    }

	public static List<DroidUdpPacket> findAllDroidUdpPackets() {
        return entityManager().createQuery("SELECT o FROM DroidUdpPacket o", DroidUdpPacket.class).getResultList();
    }

	public static DroidUdpPacket findDroidUdpPacket(Long id) {
        if (id == null) return null;
        return entityManager().find(DroidUdpPacket.class, id);
    }

	public static List<DroidUdpPacket> findDroidUdpPacketEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DroidUdpPacket o", DroidUdpPacket.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            DroidUdpPacket attached = DroidUdpPacket.findDroidUdpPacket(this.id);
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
    public DroidUdpPacket merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DroidUdpPacket merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
