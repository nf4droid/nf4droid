package at.tugraz.iaik.nf4droid.server.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;
import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import at.tugraz.iaik.nf4droid.shared.constant.ExposeObscuring;
import at.tugraz.iaik.nf4droid.shared.constant.ExposePoint;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;

@Configurable
@Entity
public class DataExpose implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Type(type = "org.joda.time.contrib.hibernate.PersistentInstantAsBigInt")
	private Instant timeInstant;

	private Long startTotalTrafficAmount;

	@Enumerated
	private ExposeType exposeType;

	@Enumerated
	private ExposeObscuring exposeObscuring;

	@Enumerated
	private ExposePoint exposePoint;

	private String exposedData;

	@ManyToOne
	@JoinColumn(name = "httpRequestId")
	private DroidHttpRequest httpRequest;

	public DataExpose(Instant time, DroidHttpRequest httpRequest,
			ExposeType exposeType, ExposeObscuring exposeObscuring,
			ExposePoint exposePoint, String exposedData) {
		super();
		this.timeInstant = time;
		this.httpRequest = httpRequest;
		this.exposeType = exposeType;
		this.exposeObscuring = exposeObscuring;
		this.exposePoint = exposePoint;
		this.exposedData = exposedData;
	}

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new DataExpose().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countDataExposes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DataExpose o", Long.class).getSingleResult();
    }

	public static List<DataExpose> findAllDataExposes() {
        return entityManager().createQuery("SELECT o FROM DataExpose o", DataExpose.class).getResultList();
    }

	public static DataExpose findDataExpose(Long id) {
        if (id == null) return null;
        return entityManager().find(DataExpose.class, id);
    }

	public static List<DataExpose> findDataExposeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DataExpose o", DataExpose.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            DataExpose attached = DataExpose.findDataExpose(this.id);
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
    public DataExpose merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DataExpose merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Version
    @Column(name = "version")
    private Integer version;

	public DataExpose() {
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

	public Instant getTimeInstant() {
        return this.timeInstant;
    }

	public void setTimeInstant(Instant timeInstant) {
        this.timeInstant = timeInstant;
    }

	public Long getStartTotalTrafficAmount() {
        return this.startTotalTrafficAmount;
    }

	public void setStartTotalTrafficAmount(Long startTotalTrafficAmount) {
        this.startTotalTrafficAmount = startTotalTrafficAmount;
    }

	public ExposeType getExposeType() {
        return this.exposeType;
    }

	public void setExposeType(ExposeType exposeType) {
        this.exposeType = exposeType;
    }

	public ExposeObscuring getExposeObscuring() {
        return this.exposeObscuring;
    }

	public void setExposeObscuring(ExposeObscuring exposeObscuring) {
        this.exposeObscuring = exposeObscuring;
    }

	public ExposePoint getExposePoint() {
        return this.exposePoint;
    }

	public void setExposePoint(ExposePoint exposePoint) {
        this.exposePoint = exposePoint;
    }

	public String getExposedData() {
        return this.exposedData;
    }

	public void setExposedData(String exposedData) {
        this.exposedData = exposedData;
    }

	public DroidHttpRequest getHttpRequest() {
        return this.httpRequest;
    }

	public void setHttpRequest(DroidHttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }
}
