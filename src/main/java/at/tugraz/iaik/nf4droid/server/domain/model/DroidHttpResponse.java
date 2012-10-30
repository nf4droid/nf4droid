package at.tugraz.iaik.nf4droid.server.domain.model;

import java.util.List;

import javax.persistence.Basic;
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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import at.tugraz.iaik.nf4droid.shared.constant.DroidHttpVersion;

@Configurable
@Entity
public class DroidHttpResponse {

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne
	private TrafficCapture capture;
	
    private String statusLine;

    @Enumerated
    private DroidHttpVersion httpVersion;

    private Integer statusCode;
    
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private String content;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) //orphanRemoval = true
	@JoinTable(name = "DroidHttpRespHeaders", joinColumns = @JoinColumn(name = "HTTP_RESPONSE_ID", nullable = false, insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "HTTP_HEADER_ID", nullable = false, insertable = false, updatable = false))
	private List<DroidHttpHeaderField> headerFields;


	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new DroidHttpResponse().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countDroidHttpResponses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DroidHttpResponse o", Long.class).getSingleResult();
    }

	public static List<DroidHttpResponse> findAllDroidHttpResponses() {
        return entityManager().createQuery("SELECT o FROM DroidHttpResponse o", DroidHttpResponse.class).getResultList();
    }

	public static DroidHttpResponse findDroidHttpResponse(Long id) {
        if (id == null) return null;
        return entityManager().find(DroidHttpResponse.class, id);
    }

	public static List<DroidHttpResponse> findDroidHttpResponseEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DroidHttpResponse o", DroidHttpResponse.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            DroidHttpResponse attached = DroidHttpResponse.findDroidHttpResponse(this.id);
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
    public DroidHttpResponse merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DroidHttpResponse merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
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

	public TrafficCapture getCapture() {
        return this.capture;
    }

	public void setCapture(TrafficCapture capture) {
        this.capture = capture;
    }

	public String getStatusLine() {
        return this.statusLine;
    }

	public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }

	public DroidHttpVersion getHttpVersion() {
        return this.httpVersion;
    }

	public void setHttpVersion(DroidHttpVersion httpVersion) {
        this.httpVersion = httpVersion;
    }

	public Integer getStatusCode() {
        return this.statusCode;
    }

	public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

	public String getContent() {
        return this.content;
    }

	public void setContent(String content) {
        this.content = content;
    }

	public List<DroidHttpHeaderField> getHeaderFields() {
        return this.headerFields;
    }

	public void setHeaderFields(List<DroidHttpHeaderField> headerFields) {
        this.headerFields = headerFields;
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
}
