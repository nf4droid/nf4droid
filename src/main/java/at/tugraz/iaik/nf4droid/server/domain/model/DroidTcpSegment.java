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

import at.tugraz.iaik.nf4droid.shared.constant.DroidTcpDirection;

@Configurable
@Entity
public class DroidTcpSegment {
	
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
	
	private Integer destPort;

    private Integer sourcePort;
	
	private Integer seqNr;

	private Integer ackNr;

	// not used, since only set in later processing step
	private Integer relativeSeqNr;

	// not used, since only set in later processing step
	private Integer relativeAckNr;

	private Integer tcpLength;

	private boolean syn;

	private boolean ack;

	private boolean psh;

	private boolean fin;

	private boolean rst;

	private boolean urg;

	@Enumerated
	private DroidTcpDirection direction;

	/**
	 *XXX still used?
	 * @param select ip packet using DroidTcpSegment.IpVersion.IPV4 or DroidTcpSegment.IpVersion.IPV6
	 */
	public DroidTcpSegment(IpVersion ipVersion) {
		super();
		if (ipVersion == IpVersion.IPV6) {
			this.ipv6Packet = new DroidIpv6Packet();
		} else {
			this.ipv4Packet = new DroidIpv4Packet();
		}
	}
	
	

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new DroidTcpSegment().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countDroidTcpSegments() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DroidTcpSegment o", Long.class).getSingleResult();
    }

	public static List<DroidTcpSegment> findAllDroidTcpSegments() {
        return entityManager().createQuery("SELECT o FROM DroidTcpSegment o", DroidTcpSegment.class).getResultList();
    }

	public static DroidTcpSegment findDroidTcpSegment(Long id) {
        if (id == null) return null;
        return entityManager().find(DroidTcpSegment.class, id);
    }

	public static List<DroidTcpSegment> findDroidTcpSegmentEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DroidTcpSegment o", DroidTcpSegment.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            DroidTcpSegment attached = DroidTcpSegment.findDroidTcpSegment(this.id);
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
    public DroidTcpSegment merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DroidTcpSegment merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@Version
    @Column(name = "version")
    private Integer version;

	public DroidTcpSegment() {
        super();
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
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

	public Integer getSeqNr() {
        return this.seqNr;
    }

	public void setSeqNr(Integer seqNr) {
        this.seqNr = seqNr;
    }

	public Integer getAckNr() {
        return this.ackNr;
    }

	public void setAckNr(Integer ackNr) {
        this.ackNr = ackNr;
    }

	public Integer getRelativeSeqNr() {
        return this.relativeSeqNr;
    }

	public void setRelativeSeqNr(Integer relativeSeqNr) {
        this.relativeSeqNr = relativeSeqNr;
    }

	public Integer getRelativeAckNr() {
        return this.relativeAckNr;
    }

	public void setRelativeAckNr(Integer relativeAckNr) {
        this.relativeAckNr = relativeAckNr;
    }

	public Integer getTcpLength() {
        return this.tcpLength;
    }

	public void setTcpLength(Integer tcpLength) {
        this.tcpLength = tcpLength;
    }

	public boolean isSyn() {
        return this.syn;
    }

	public void setSyn(boolean syn) {
        this.syn = syn;
    }

	public boolean isAck() {
        return this.ack;
    }

	public void setAck(boolean ack) {
        this.ack = ack;
    }

	public boolean isPsh() {
        return this.psh;
    }

	public void setPsh(boolean psh) {
        this.psh = psh;
    }

	public boolean isFin() {
        return this.fin;
    }

	public void setFin(boolean fin) {
        this.fin = fin;
    }

	public boolean isRst() {
        return this.rst;
    }

	public void setRst(boolean rst) {
        this.rst = rst;
    }

	public boolean isUrg() {
        return this.urg;
    }

	public void setUrg(boolean urg) {
        this.urg = urg;
    }

	public DroidTcpDirection getDirection() {
        return this.direction;
    }

	public void setDirection(DroidTcpDirection direction) {
        this.direction = direction;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
