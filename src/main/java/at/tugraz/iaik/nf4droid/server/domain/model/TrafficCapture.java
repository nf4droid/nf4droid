package at.tugraz.iaik.nf4droid.server.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import at.tugraz.iaik.nf4droid.server.common.ParallelIterator;
import at.tugraz.iaik.nf4droid.server.domain.DomainUtil;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QAppVersion;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QTrafficCapture;
import at.tugraz.iaik.nf4droid.shared.dto.ClassVariableMapping;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.OrderSpecifier;

@Configurable
@Entity
public class TrafficCapture {

	private static final Logger LOGGER = Logger.getLogger(TrafficCapture.class);

	private static final QAppVersion appVersionQ = QAppVersion.appVersion;
	private static final QTrafficCapture trafficCaptureQ = QTrafficCapture.trafficCapture;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn
	private DroidProcessingResult processingResult;

	@ManyToOne(optional = false)
	@JoinColumn(name = "APP_VERSION_ID", nullable = false, updatable = false)
	private AppVersion appVersion;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date dateAdded;

	private String description;

	private String phoneNr;
	
	private String androidId;
	
	private Long imsi;
	
	private Long imei;
	
	private String user;
	
	private String password;
	
	private String ssid;
	
	private String bssid;
	
	private String latitude;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] pcapFile;

	public byte[] getPcapFile() {
		return pcapFile;
	}

	public void setPcapFile(byte[] pcapFile) {
		this.pcapFile = pcapFile;
	}

	private static JPAQuery fromAppVersion() {
		return new JPAQuery(entityManager()).from(appVersionQ);
	}

	public static List<TrafficCapture> findTrafficCapturesForAppVersionByDateAddedOrDescriptionLikeLimitOrderBy(
			final Long appVersionId, final String searchString,
			final int firstResult, final int maxResults,
			final List<String> orderByNames,
			final List<Boolean> orderByAscValues) {
		LOGGER.info("Find traffic captures for app version '" + appVersionId
				+ "' with date or description like '" + searchString
				+ "' and limit and order by.");

		return fromAppVersion()
				.join(appVersionQ.trafficCaptures, trafficCaptureQ)
				.where(buildWhereClause(appVersionId, searchString))
				.orderBy(buildOrderByClause(orderByNames, orderByAscValues))
				.offset(firstResult).limit(maxResults).list(trafficCaptureQ);
	}

	public static Long countTrafficCapturesForAppVersionByDateAddedOrDescriptionLike(
			final Long appVersionId, final String searchString) {

		LOGGER.info("Count traffic captures for app version '" + appVersionId
				+ "' with date or description like '" + searchString + "'.");
		return fromAppVersion()
				.join(appVersionQ.trafficCaptures, trafficCaptureQ)
				.where(buildWhereClause(appVersionId, searchString)).count();
	}

	private static BooleanBuilder buildWhereClause(final Long appVersionId,
			String searchString) {
		final BooleanBuilder booleanBuilder = new BooleanBuilder(
				appVersionQ.id.eq(appVersionId));
		if (searchString != null && searchString.length() > 0) {
			searchString = DomainUtil.prepareSearchString(searchString);
			booleanBuilder.andAnyOf(
					DomainUtil.convertDateToDefaultFormatString(trafficCaptureQ.dateAdded).like(searchString),
					trafficCaptureQ.description
							.lower().like(searchString.toLowerCase()));
		}

		return booleanBuilder;
	}

	@SuppressWarnings("unchecked")
	private static OrderSpecifier<?>[] buildOrderByClause(
			final List<String> orderByNames,
			final List<Boolean> orderByAscValues) {
		final List<OrderSpecifier<?>> orderBys = new ArrayList<OrderSpecifier<?>>();
		if (orderByNames != null && orderByAscValues != null) {

			for (ParallelIterator<String, Boolean> iterator = new ParallelIterator<String, Boolean>(
					orderByNames.iterator(), orderByAscValues.iterator()); iterator
					.hasNext();) {
				ParallelIterator<String, Boolean>.Pair<String, Boolean> orderByPair = iterator
						.next();

				if (orderByPair.getV1().equals(
						ClassVariableMapping.CAPTURE_DATE)) {
					if (orderByPair.getV2()) {
						orderBys.add(trafficCaptureQ.dateAdded.asc());
					} else {
						orderBys.add(trafficCaptureQ.dateAdded.desc());
					}
				} else if (orderByPair.getV1().equals(
						ClassVariableMapping.CAPTURE_DESCRIPTION)) {
					if (orderByPair.getV2()) {
						orderBys.add(trafficCaptureQ.description.asc());
					} else {
						orderBys.add(trafficCaptureQ.description.desc());
					}
				}
			}
		}

		// default sorting (if none defined)
		if (orderBys.isEmpty()) {
			orderBys.add(trafficCaptureQ.dateAdded.asc());
		}
		return orderBys.toArray(new OrderSpecifier[orderBys.size()]);
	}

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new TrafficCapture().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countTrafficCaptures() {
        return entityManager().createQuery("SELECT COUNT(o) FROM TrafficCapture o", Long.class).getSingleResult();
    }

	public static List<TrafficCapture> findAllTrafficCaptures() {
        return entityManager().createQuery("SELECT o FROM TrafficCapture o", TrafficCapture.class).getResultList();
    }

	public static TrafficCapture findTrafficCapture(Long id) {
        if (id == null) return null;
        return entityManager().find(TrafficCapture.class, id);
    }

	public static List<TrafficCapture> findTrafficCaptureEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM TrafficCapture o", TrafficCapture.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            TrafficCapture attached = TrafficCapture.findTrafficCapture(this.id);
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
    public TrafficCapture merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        TrafficCapture merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public DroidProcessingResult getProcessingResult() {
        return this.processingResult;
    }

	public void setProcessingResult(DroidProcessingResult processingResult) {
        this.processingResult = processingResult;
    }

	public AppVersion getAppVersion() {
        return this.appVersion;
    }

	public void setAppVersion(AppVersion appVersion) {
        this.appVersion = appVersion;
    }

	public Date getDateAdded() {
        return this.dateAdded;
    }

	public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

	public String getDescription() {
        return this.description;
    }

	public void setDescription(String description) {
        this.description = description;
    }

	public String getPhoneNr() {
        return this.phoneNr;
    }

	public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

	public String getAndroidId() {
        return this.androidId;
    }

	public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

	public Long getImsi() {
        return this.imsi;
    }

	public void setImsi(Long imsi) {
        this.imsi = imsi;
    }

	public Long getImei() {
        return this.imei;
    }

	public void setImei(Long imei) {
        this.imei = imei;
    }

	public String getUser() {
        return this.user;
    }

	public void setUser(String user) {
        this.user = user;
    }

	public String getPassword() {
        return this.password;
    }

	public void setPassword(String password) {
        this.password = password;
    }

	public String getSsid() {
        return this.ssid;
    }

	public void setSsid(String ssid) {
        this.ssid = ssid;
    }

	public String getBssid() {
        return this.bssid;
    }

	public void setBssid(String bssid) {
        this.bssid = bssid;
    }

	public String getLatitude() {
        return this.latitude;
    }

	public void setLatitude(String latitude) {
        this.latitude = latitude;
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
}
