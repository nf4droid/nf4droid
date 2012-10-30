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
public class DroidHttpHeaderField {

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    private String fieldName;

    @Lob
    @Basic(fetch=FetchType.EAGER)
    private String fieldValue;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public String getFieldName() {
        return this.fieldName;
    }

	public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

	public String getFieldValue() {
        return this.fieldValue;
    }

	public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new DroidHttpHeaderField().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countDroidHttpHeaderFields() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DroidHttpHeaderField o", Long.class).getSingleResult();
    }

	public static List<DroidHttpHeaderField> findAllDroidHttpHeaderFields() {
        return entityManager().createQuery("SELECT o FROM DroidHttpHeaderField o", DroidHttpHeaderField.class).getResultList();
    }

	public static DroidHttpHeaderField findDroidHttpHeaderField(Long id) {
        if (id == null) return null;
        return entityManager().find(DroidHttpHeaderField.class, id);
    }

	public static List<DroidHttpHeaderField> findDroidHttpHeaderFieldEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DroidHttpHeaderField o", DroidHttpHeaderField.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            DroidHttpHeaderField attached = DroidHttpHeaderField.findDroidHttpHeaderField(this.id);
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
    public DroidHttpHeaderField merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DroidHttpHeaderField merged = this.entityManager.merge(this);
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
