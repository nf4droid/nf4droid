package at.tugraz.iaik.nf4droid.server.domain.model;

import java.util.List;

import javax.persistence.Entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
public class DroidIpv4Packet extends DroidAbstractIpPacket {
	
	private Integer ipVersion;

    private Integer headerLength;

    private Integer typeOfService;

    private Integer totalLength;

    private Integer identification;

    private Integer flags;

    private Integer fragmentOffset;

    private Integer timeToLive;

    private Integer protocol;

    private Integer headerChecksum;

	public DroidIpv4Packet() {
		super();
	}
      

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public Integer getIpVersion() {
        return this.ipVersion;
    }

	public void setIpVersion(Integer ipVersion) {
        this.ipVersion = ipVersion;
    }

	public Integer getHeaderLength() {
        return this.headerLength;
    }

	public void setHeaderLength(Integer headerLength) {
        this.headerLength = headerLength;
    }

	public Integer getTypeOfService() {
        return this.typeOfService;
    }

	public void setTypeOfService(Integer typeOfService) {
        this.typeOfService = typeOfService;
    }

	public Integer getTotalLength() {
        return this.totalLength;
    }

	public void setTotalLength(Integer totalLength) {
        this.totalLength = totalLength;
    }

	public Integer getIdentification() {
        return this.identification;
    }

	public void setIdentification(Integer identification) {
        this.identification = identification;
    }

	public Integer getFlags() {
        return this.flags;
    }

	public void setFlags(Integer flags) {
        this.flags = flags;
    }

	public Integer getFragmentOffset() {
        return this.fragmentOffset;
    }

	public void setFragmentOffset(Integer fragmentOffset) {
        this.fragmentOffset = fragmentOffset;
    }

	public Integer getTimeToLive() {
        return this.timeToLive;
    }

	public void setTimeToLive(Integer timeToLive) {
        this.timeToLive = timeToLive;
    }

	public Integer getProtocol() {
        return this.protocol;
    }

	public void setProtocol(Integer protocol) {
        this.protocol = protocol;
    }

	public Integer getHeaderChecksum() {
        return this.headerChecksum;
    }

	public void setHeaderChecksum(Integer headerChecksum) {
        this.headerChecksum = headerChecksum;
    }

	public static long countDroidIpv4Packets() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DroidIpv4Packet o", Long.class).getSingleResult();
    }

	public static List<DroidIpv4Packet> findAllDroidIpv4Packets() {
        return entityManager().createQuery("SELECT o FROM DroidIpv4Packet o", DroidIpv4Packet.class).getResultList();
    }

	public static DroidIpv4Packet findDroidIpv4Packet(Long id) {
        if (id == null) return null;
        return entityManager().find(DroidIpv4Packet.class, id);
    }

	public static List<DroidIpv4Packet> findDroidIpv4PacketEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DroidIpv4Packet o", DroidIpv4Packet.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public DroidIpv4Packet merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DroidIpv4Packet merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
