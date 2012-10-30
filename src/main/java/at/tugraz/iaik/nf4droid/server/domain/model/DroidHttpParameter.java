package at.tugraz.iaik.nf4droid.server.domain.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
public class DroidHttpParameter {

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    private String parameterName;

    @Lob
    @Basic(fetch=FetchType.EAGER)
    private String parameterValue;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public String getParameterName() {
        return this.parameterName;
    }

	public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

	public String getParameterValue() {
        return this.parameterValue;
    }

	public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new DroidHttpParameter().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countDroidHttpParameters() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DroidHttpParameter o", Long.class).getSingleResult();
    }

	public static List<DroidHttpParameter> findAllDroidHttpParameters() {
        return entityManager().createQuery("SELECT o FROM DroidHttpParameter o", DroidHttpParameter.class).getResultList();
    }

	public static DroidHttpParameter findDroidHttpParameter(Long id) {
        if (id == null) return null;
        return entityManager().find(DroidHttpParameter.class, id);
    }

	public static List<DroidHttpParameter> findDroidHttpParameterEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DroidHttpParameter o", DroidHttpParameter.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            DroidHttpParameter attached = DroidHttpParameter.findDroidHttpParameter(this.id);
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
    public DroidHttpParameter merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DroidHttpParameter merged = this.entityManager.merge(this);
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
}
