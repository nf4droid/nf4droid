package at.tugraz.iaik.nf4droid.server.domain.model;

import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import at.tugraz.iaik.nf4droid.server.common.ParallelIterator;
import at.tugraz.iaik.nf4droid.server.domain.DomainUtil;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QApp;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QAppVersion;
import at.tugraz.iaik.nf4droid.shared.dto.ClassVariableMapping;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.OrderSpecifier;

@Configurable
@Entity
public class AppVersion {

	private static final Logger LOGGER = Logger.getLogger(AppVersion.class);
	private static final QApp appQ = QApp.app;
	private static final QAppVersion appVersionQ = QAppVersion.appVersion;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(referencedColumnName = "id", name = "APP_ID", nullable = false, updatable = false)
	private App app;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "appVersion", orphanRemoval = true, fetch = FetchType.LAZY)
	private List<TrafficCapture> trafficCaptures;

	private String versionName;

	private Integer versionCode;

	private static JPAQuery fromApp() {
		return new JPAQuery(entityManager()).from(appQ);
	}

	public static AppVersion findAppVersionForAppWhereVersionCodeEq(
			final Long appId, final int versionCode) {
		LOGGER.info("Find app versions for app '" + appId
				+ "' where version code equals '" + versionCode + "'.");
		return fromApp()
				.join(appQ.appVersions, appVersionQ)
				.where(appQ.id.eq(appId).and(
						appVersionQ.versionCode.eq(versionCode)))
				.singleResult(appVersionQ);
	}

	public static List<AppVersion> findAppVersionsForAppByVersionCodeOrVerisonNameLikeLimitOrderBy(
			final Long appId, final String searchString, final int firstResult,
			final int maxResults, final List<String> orderByNames,
			final List<Boolean> orderByAscValues) {

		LOGGER.info("Find app versions for app '" + appId
				+ "' with version code or version name like '" + searchString
				+ "' and limit and order by.");

		return fromApp().join(appQ.appVersions, appVersionQ)
				.where(buildWhereClause(appId, searchString))
				.orderBy(buildOrderByClause(orderByNames, orderByAscValues))
				.offset(firstResult).limit(maxResults).list(appVersionQ);
	}

	public static Long countAppVersionsForAppByVersionCodeOrVersionNameLike(
			final Long appId, final String searchString) {
		LOGGER.info("Count app versions for app '" + appId
				+ "' with version code or version name like '" + searchString
				+ "'.");

		return fromApp().join(appQ.appVersions, appVersionQ)
				.where(buildWhereClause(appId, searchString)).count();
	}

	private static BooleanBuilder buildWhereClause(final Long appId,
			String searchString) {
		final BooleanBuilder booleanBuilder = new BooleanBuilder(
				appQ.id.eq(appId));
		if (searchString != null && searchString.length() > 0) {
			searchString = DomainUtil.prepareSearchString(searchString);
			booleanBuilder.andAnyOf(
					appVersionQ.versionName.lower().like(
							searchString.toLowerCase()),
					appVersionQ.versionCode.stringValue().lower()
							.like(searchString.toLowerCase()));
		}

		return booleanBuilder;
	}

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
						ClassVariableMapping.APP_VERSION_NAME)) {
					if (orderByPair.getV2()) {
						orderBys.add(appVersionQ.versionName.asc());
					} else {
						orderBys.add(appVersionQ.versionName.desc());
					}
				} else if (orderByPair.getV1().equals(
						ClassVariableMapping.APP_VERSION_CODE)) {
					if (orderByPair.getV2()) {
						orderBys.add(appVersionQ.versionCode.asc());
					} else {
						orderBys.add(appVersionQ.versionCode.desc());
					}
				}
			}
		}

		// default sorting (if none defined)
		if (orderBys.isEmpty()) {
			orderBys.add(appVersionQ.versionCode.asc());
		}
		return orderBys.toArray(new OrderSpecifier[orderBys.size()]);
	}

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new AppVersion().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countAppVersions() {
        return entityManager().createQuery("SELECT COUNT(o) FROM AppVersion o", Long.class).getSingleResult();
    }

	public static List<AppVersion> findAllAppVersions() {
        return entityManager().createQuery("SELECT o FROM AppVersion o", AppVersion.class).getResultList();
    }

	public static AppVersion findAppVersion(Long id) {
        if (id == null) return null;
        return entityManager().find(AppVersion.class, id);
    }

	public static List<AppVersion> findAppVersionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM AppVersion o", AppVersion.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            AppVersion attached = AppVersion.findAppVersion(this.id);
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
    public AppVersion merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        AppVersion merged = this.entityManager.merge(this);
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

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public App getApp() {
        return this.app;
    }

	public void setApp(App app) {
        this.app = app;
    }

	public List<TrafficCapture> getTrafficCaptures() {
        return this.trafficCaptures;
    }

	public void setTrafficCaptures(List<TrafficCapture> trafficCaptures) {
        this.trafficCaptures = trafficCaptures;
    }

	public String getVersionName() {
        return this.versionName;
    }

	public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

	public Integer getVersionCode() {
        return this.versionCode;
    }

	public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
