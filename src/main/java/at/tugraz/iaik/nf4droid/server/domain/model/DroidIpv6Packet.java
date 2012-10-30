package at.tugraz.iaik.nf4droid.server.domain.model;

import java.util.List;

import javax.persistence.Entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
public class DroidIpv6Packet extends DroidAbstractIpPacket {
	
	private Integer flowLabel; // 20 bits
	private Integer payloadLength; // 2 bytes
	private Integer hopLimit; // 1 byte
	private Byte trafficClass;
	private Byte nextHeader; // 1 byte

	public DroidIpv6Packet() {
		super();
	}
    

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public static long countDroidIpv6Packets() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DroidIpv6Packet o", Long.class).getSingleResult();
    }

	public static List<DroidIpv6Packet> findAllDroidIpv6Packets() {
        return entityManager().createQuery("SELECT o FROM DroidIpv6Packet o", DroidIpv6Packet.class).getResultList();
    }

	public static DroidIpv6Packet findDroidIpv6Packet(Long id) {
        if (id == null) return null;
        return entityManager().find(DroidIpv6Packet.class, id);
    }

	public static List<DroidIpv6Packet> findDroidIpv6PacketEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DroidIpv6Packet o", DroidIpv6Packet.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public DroidIpv6Packet merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DroidIpv6Packet merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public Integer getFlowLabel() {
        return this.flowLabel;
    }

	public void setFlowLabel(Integer flowLabel) {
        this.flowLabel = flowLabel;
    }

	public Integer getPayloadLength() {
        return this.payloadLength;
    }

	public void setPayloadLength(Integer payloadLength) {
        this.payloadLength = payloadLength;
    }

	public Integer getHopLimit() {
        return this.hopLimit;
    }

	public void setHopLimit(Integer hopLimit) {
        this.hopLimit = hopLimit;
    }

	public Byte getTrafficClass() {
        return this.trafficClass;
    }

	public void setTrafficClass(Byte trafficClass) {
        this.trafficClass = trafficClass;
    }

	public Byte getNextHeader() {
        return this.nextHeader;
    }

	public void setNextHeader(Byte nextHeader) {
        this.nextHeader = nextHeader;
    }
}
