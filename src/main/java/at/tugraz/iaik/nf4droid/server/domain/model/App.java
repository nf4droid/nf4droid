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
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import at.tugraz.iaik.nf4droid.server.common.ParallelIterator;
import at.tugraz.iaik.nf4droid.server.domain.DomainUtil;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QApp;
import at.tugraz.iaik.nf4droid.shared.dto.ClassVariableMapping;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.OrderSpecifier;

@Configurable
@Entity
public class App {

	private static final Logger LOGGER = Logger.getLogger(App.class);
	private static final QApp appQ = QApp.app;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@NotNull
	private String appName;

	@NotNull
	private String appPackage;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "app", orphanRemoval = true, fetch = FetchType.LAZY)
	private List<AppVersion> appVersions;

	private static JPAQuery fromApp() {
		return new JPAQuery(entityManager()).from(appQ);
	}

	public static App findAppWherePackageEq(final String appPackage) {
		LOGGER.info("Find app where app package equals '"
				+ appPackage + "'.");
		return fromApp().where(appQ.appPackage.equalsIgnoreCase(appPackage)).singleResult(appQ);
	}
	
	public static List<App> findAppsByAppNameOrAppPackageLikeLimitOrderBy(
			String searchString, int firstResult, int maxResults,
			List<String> orderByNames, List<Boolean> orderByAscValues) {

		LOGGER.info("Find apps with app name or app package like '"
				+ searchString + "' and limit and order by.");

		return fromApp().where(buildWhereClause(searchString))
				.orderBy(buildOrderByClause(orderByNames, orderByAscValues))
				.offset(firstResult).limit(maxResults).list(appQ);
	}

	public static Long countAppsByAppNameOrAppPackageLike(String searchString) {

		LOGGER.info("Count apps with app name or app package like '"
				+ searchString + "'.");

		return fromApp().where(buildWhereClause(searchString)).count();
	}

	private static BooleanBuilder buildWhereClause(String searchString) {
		final BooleanBuilder booleanBuilder = new BooleanBuilder();
		if (searchString != null && searchString.length() > 0) {
			searchString = DomainUtil.prepareSearchString(searchString);
			booleanBuilder.or(appQ.appName.lower().like(searchString.toLowerCase()));
			booleanBuilder.or(appQ.appPackage.lower().like(searchString.toLowerCase()));
		}

		return booleanBuilder;
	}

	@SuppressWarnings("unchecked")
	private static OrderSpecifier<String>[] buildOrderByClause(
			final List<String> orderByNames,
			final List<Boolean> orderByAscValues) {
		final List<OrderSpecifier<String>> orderBys = new ArrayList<OrderSpecifier<String>>();
		if (orderByNames != null && orderByAscValues != null) {

			for (ParallelIterator<String, Boolean> iterator = new ParallelIterator<String, Boolean>(
					orderByNames.iterator(), orderByAscValues.iterator()); iterator
					.hasNext();) {
				ParallelIterator<String, Boolean>.Pair<String, Boolean> orderByPair = iterator
						.next();

				if (orderByPair.getV1().equals(ClassVariableMapping.APP_NAME)) {
					if (orderByPair.getV2()) {
						orderBys.add(appQ.appName.asc());
					} else {
						orderBys.add(appQ.appName.desc());
					}
				} else if (orderByPair.getV1().equals(
						ClassVariableMapping.APP_PACKAGE)) {
					if (orderByPair.getV2()) {
						orderBys.add(appQ.appPackage.asc());
					} else {
						orderBys.add(appQ.appPackage.desc());
					}
				}
			}
		}

		// default sorting (if none defined)
		if (orderBys.isEmpty()) {
			orderBys.add(appQ.appName.asc());
		}
		return orderBys.toArray(new OrderSpecifier[orderBys.size()]);
	}


	public static TypedQuery<App> findAppsByAppNameLikeOrAppPackageLike(String appName, String appPackage) {
        if (appName == null || appName.length() == 0) throw new IllegalArgumentException("The appName argument is required");
        appName = appName.replace('*', '%');
        if (appName.charAt(0) != '%') {
            appName = "%" + appName;
        }
        if (appName.charAt(appName.length() - 1) != '%') {
            appName = appName + "%";
        }
        if (appPackage == null || appPackage.length() == 0) throw new IllegalArgumentException("The appPackage argument is required");
        appPackage = appPackage.replace('*', '%');
        if (appPackage.charAt(0) != '%') {
            appPackage = "%" + appPackage;
        }
        if (appPackage.charAt(appPackage.length() - 1) != '%') {
            appPackage = appPackage + "%";
        }
        EntityManager em = App.entityManager();
        TypedQuery<App> q = em.createQuery("SELECT o FROM App AS o WHERE LOWER(o.appName) LIKE LOWER(:appName)  OR LOWER(o.appPackage) LIKE LOWER(:appPackage)", App.class);
        q.setParameter("appName", appName);
        q.setParameter("appPackage", appPackage);
        return q;
    }

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public String getAppName() {
        return this.appName;
    }

	public void setAppName(String appName) {
        this.appName = appName;
    }

	public String getAppPackage() {
        return this.appPackage;
    }

	public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

	public List<AppVersion> getAppVersions() {
        return this.appVersions;
    }

	public void setAppVersions(List<AppVersion> appVersions) {
        this.appVersions = appVersions;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new App().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countApps() {
        return entityManager().createQuery("SELECT COUNT(o) FROM App o", Long.class).getSingleResult();
    }

	public static List<App> findAllApps() {
        return entityManager().createQuery("SELECT o FROM App o", App.class).getResultList();
    }

	public static App findApp(Long id) {
        if (id == null) return null;
        return entityManager().find(App.class, id);
    }

	public static List<App> findAppEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM App o", App.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            App attached = App.findApp(this.id);
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
    public App merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        App merged = this.entityManager.merge(this);
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

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
