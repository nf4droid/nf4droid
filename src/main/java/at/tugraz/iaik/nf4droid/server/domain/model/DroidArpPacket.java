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
public class DroidArpPacket {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, optional=false)
	@JoinColumn
	private DroidEthernetFrame ethernetFrame;
	
	private String senderMac;
	private String senderIp;
	private String targetMac;
	private String targetIp;
	
	private Integer hardwareType; // should be 0x0001
	private Integer protocolType; // should be 0x0800
	private Integer hardwareSize; // should be 6
	private Integer protocolSize; // should be 4
	private Integer opcode;
	
	public DroidArpPacket() {
		super();
		this.ethernetFrame = new DroidEthernetFrame();
	}
	
	

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new DroidArpPacket().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countDroidArpPackets() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DroidArpPacket o", Long.class).getSingleResult();
    }

	public static List<DroidArpPacket> findAllDroidArpPackets() {
        return entityManager().createQuery("SELECT o FROM DroidArpPacket o", DroidArpPacket.class).getResultList();
    }

	public static DroidArpPacket findDroidArpPacket(Long id) {
        if (id == null) return null;
        return entityManager().find(DroidArpPacket.class, id);
    }

	public static List<DroidArpPacket> findDroidArpPacketEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DroidArpPacket o", DroidArpPacket.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            DroidArpPacket attached = DroidArpPacket.findDroidArpPacket(this.id);
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
    public DroidArpPacket merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DroidArpPacket merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
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

	public String getSenderMac() {
        return this.senderMac;
    }

	public void setSenderMac(String senderMac) {
        this.senderMac = senderMac;
    }

	public String getSenderIp() {
        return this.senderIp;
    }

	public void setSenderIp(String senderIp) {
        this.senderIp = senderIp;
    }

	public String getTargetMac() {
        return this.targetMac;
    }

	public void setTargetMac(String targetMac) {
        this.targetMac = targetMac;
    }

	public String getTargetIp() {
        return this.targetIp;
    }

	public void setTargetIp(String targetIp) {
        this.targetIp = targetIp;
    }

	public Integer getHardwareType() {
        return this.hardwareType;
    }

	public void setHardwareType(Integer hardwareType) {
        this.hardwareType = hardwareType;
    }

	public Integer getProtocolType() {
        return this.protocolType;
    }

	public void setProtocolType(Integer protocolType) {
        this.protocolType = protocolType;
    }

	public Integer getHardwareSize() {
        return this.hardwareSize;
    }

	public void setHardwareSize(Integer hardwareSize) {
        this.hardwareSize = hardwareSize;
    }

	public Integer getProtocolSize() {
        return this.protocolSize;
    }

	public void setProtocolSize(Integer protocolSize) {
        this.protocolSize = protocolSize;
    }

	public Integer getOpcode() {
        return this.opcode;
    }

	public void setOpcode(Integer opcode) {
        this.opcode = opcode;
    }
}
