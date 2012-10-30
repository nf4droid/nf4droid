package at.tugraz.iaik.nf4droid.server.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;


@Configurable
@Entity
public class DroidPcapPacket {

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime arrivalTime;
	
	@Type(type = "org.joda.time.contrib.hibernate.PersistentInstantAsBigInt")
	private Instant arrivalTimeInstant;

	private Long includedLength;

	private Long originalLength;

	public Date getArrivalTimeJava() {
		return arrivalTimeInstant.toDate();
	}
	
	public void setArrivalTimeJava(Date date) {
		arrivalTime = new DateTime(date);
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
        EntityManager em = new DroidPcapPacket().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countDroidPcapPackets() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DroidPcapPacket o", Long.class).getSingleResult();
    }

	public static List<DroidPcapPacket> findAllDroidPcapPackets() {
        return entityManager().createQuery("SELECT o FROM DroidPcapPacket o", DroidPcapPacket.class).getResultList();
    }

	public static DroidPcapPacket findDroidPcapPacket(Long id) {
        if (id == null) return null;
        return entityManager().find(DroidPcapPacket.class, id);
    }

	public static List<DroidPcapPacket> findDroidPcapPacketEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DroidPcapPacket o", DroidPcapPacket.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            DroidPcapPacket attached = DroidPcapPacket.findDroidPcapPacket(this.id);
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
    public DroidPcapPacket merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DroidPcapPacket merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public DateTime getArrivalTime() {
        return this.arrivalTime;
    }

	public void setArrivalTime(DateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

	public Instant getArrivalTimeInstant() {
        return this.arrivalTimeInstant;
    }

	public void setArrivalTimeInstant(Instant arrivalTimeInstant) {
        this.arrivalTimeInstant = arrivalTimeInstant;
    }

	public Long getIncludedLength() {
        return this.includedLength;
    }

	public void setIncludedLength(Long includedLength) {
        this.includedLength = includedLength;
    }

	public Long getOriginalLength() {
        return this.originalLength;
    }

	public void setOriginalLength(Long originalLength) {
        this.originalLength = originalLength;
    }
}
