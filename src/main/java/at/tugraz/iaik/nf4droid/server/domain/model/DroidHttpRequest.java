package at.tugraz.iaik.nf4droid.server.domain.model;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import at.tugraz.iaik.nf4droid.server.common.ParallelIterator;
import at.tugraz.iaik.nf4droid.server.domain.DomainUtil;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDataExpose;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidHttpRequest;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidProcessingResult;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QTrafficCapture;
import at.tugraz.iaik.nf4droid.shared.constant.DroidHttpMethod;
import at.tugraz.iaik.nf4droid.shared.constant.DroidHttpVersion;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;
import at.tugraz.iaik.nf4droid.shared.dto.BarChartStatisticsEntry;
import at.tugraz.iaik.nf4droid.shared.dto.ClassVariableMapping;
import at.tugraz.iaik.nf4droid.shared.dto.QBarChartStatisticsEntry;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.OrderSpecifier;

@Configurable
@Entity
public class DroidHttpRequest {

	private static final Logger LOGGER = Logger
			.getLogger(DroidHttpRequest.class);

	private static final QTrafficCapture trafficCaptureQ = QTrafficCapture.trafficCapture;
	private static final QDroidHttpRequest httpReqQ = QDroidHttpRequest.droidHttpRequest;
	private static final QDroidProcessingResult procResQ = QDroidProcessingResult.droidProcessingResult;
	private static final QDataExpose dataExposeQ = QDataExpose.dataExpose;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(max = 2048)
	@Column(length = 2048)
	private String url;

	@Size(max = 2048)
	@Column(length = 2048)
	private String host;

	@Size(max = 2048)
	@Column(length = 2048)
	private String queryString;

	private String remoteIp;

	private String localIp;

	private Integer remotePort;

	private Integer localPort;

	private String remoteCountry;

	private String remoteCity;

	private Float remoteLat;

	private Float remoteLon;

	@ManyToOne
	private TrafficCapture capture;

	@Enumerated
	private DroidHttpVersion httpVersion;

	@Enumerated
	private DroidHttpMethod httpMethod;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// orphanRemoval = true
	@JoinTable(name = "DroidHttpReqHeaders", joinColumns = @JoinColumn(name = "HTTP_REQUEST_ID", nullable = false, insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "HTTP_HEADER_ID", nullable = false, insertable = false, updatable = false))
	private List<DroidHttpHeaderField> headerFields;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// orphanRemoval = true
	@JoinTable(name = "DroidHttpReqParams", joinColumns = @JoinColumn(name = "HTTP_REQUEST_ID", nullable = false, insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "HTTP_PARAMETER_ID", nullable = false, insertable = false, updatable = false))
	private List<DroidHttpParameter> parameters;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "httpRequest")
	private List<DataExpose> dataExposeList;

	public void addDataExpose(final DataExpose dataExpose) {
		if (dataExposeList == null) {
			dataExposeList = new ArrayList<DataExpose>();
		}

		dataExposeList.add(dataExpose);
	}

	private static JPAQuery fromTrafficCapture() {
		return new JPAQuery(entityManager()).from(trafficCaptureQ);
	}

	public static List<DroidHttpRequest> findHttpRequestsForCaptureByFieldsLikeLimitOrderBy(
			final Long trafficCaptureId, final String searchString,
			final int firstResult, final int maxResults,
			final List<String> orderByNames,
			final List<Boolean> orderByAscValues) {

		LOGGER.info("Find http requests for traffic capture '"
				+ trafficCaptureId + "' with fields like '" + searchString
				+ "' and limit and order by.");

		return fromTrafficCapture()
				.join(trafficCaptureQ.processingResult, procResQ)
				.join(procResQ.httpRequests, httpReqQ)
				.where(buildWhereClause(trafficCaptureId, searchString, null))
				.orderBy(buildOrderByClause(orderByNames, orderByAscValues))
				.offset(firstResult).limit(maxResults).list(httpReqQ);
	}

	public static List<DroidHttpRequest> findHttpRequestsForCaptureByFieldsLikeFilterLimitOrderBy(
			final Long trafficCaptureId, final String searchString,
			final int firstResult, final int maxResults,
			final List<String> orderByNames,
			final List<Boolean> orderByAscValues, final ExposeType exposeFilter) {

		LOGGER.info("Find http requests for traffic capture '"
				+ trafficCaptureId + "' with fields like '" + searchString
				+ "' and expose type is '" + exposeFilter.getDescription()
				+ "' and limit and order by.");

		return fromTrafficCapture()
				.join(trafficCaptureQ.processingResult, procResQ)
				.join(procResQ.httpRequests, httpReqQ)
				.join(httpReqQ.dataExposeList, dataExposeQ)
				.where(buildWhereClause(trafficCaptureId, searchString,
						exposeFilter))
				.orderBy(buildOrderByClause(orderByNames, orderByAscValues))
				.offset(firstResult).limit(maxResults).groupBy(httpReqQ.id)
				.list(httpReqQ);
	}

	public static Long countHttpRequestsForCaptureByFieldsLike(
			final Long trafficCaptureId, final String searchString) {
		LOGGER.info("Count http requests for traffic capture '"
				+ trafficCaptureId + "' with fields like '" + searchString
				+ "'.");

		return fromTrafficCapture()
				.join(trafficCaptureQ.processingResult, procResQ)
				.join(procResQ.httpRequests, httpReqQ)
				.where(buildWhereClause(trafficCaptureId, searchString, null))
				.count();
	}

	public static Long countHttpRequestsForCaptureByFieldsLikeFilter(
			final Long trafficCaptureId, final String searchString,
			final ExposeType exposeFilter) {
		LOGGER.info("Count http requests for traffic capture '"
				+ trafficCaptureId + "' with fields like '" + searchString
				+ "' and expose type is '" + exposeFilter.getDescription()
				+ "'.");

		return fromTrafficCapture()
				.join(trafficCaptureQ.processingResult, procResQ)
				.join(procResQ.httpRequests, httpReqQ)
				.join(httpReqQ.dataExposeList, dataExposeQ)
				.where(buildWhereClause(trafficCaptureId, searchString,
						exposeFilter))
				.singleResult(httpReqQ.id.countDistinct());
	}

	public static List<BarChartStatisticsEntry> findHttpRequestsHitStatistics(
			final Long trafficCaptureId, final String searchString,
			final int firstResult, final int maxResults,
			final ExposeType exposeFilter, final String groupBy) {

		final JPAQuery queryPart = fromTrafficCapture()
				.join(trafficCaptureQ.processingResult, procResQ)
				.join(procResQ.httpRequests, httpReqQ)
				.leftJoin(httpReqQ.dataExposeList, dataExposeQ)
				.where(buildWhereClause(trafficCaptureId, searchString,
						exposeFilter)).offset(firstResult).limit(maxResults);

		if (groupBy != null
				&& groupBy.equals(ClassVariableMapping.HTTP_REQ_URL)) {
			return queryPart
					.orderBy(httpReqQ.url.count().desc())
					.groupBy(httpReqQ.url)
					.list(new QBarChartStatisticsEntry(httpReqQ.url,
							httpReqQ.url.count()));
		} else if (groupBy != null && groupBy.equals(ClassVariableMapping.HTTP_REQ_REMOTE_IP)) {
			return queryPart
					.orderBy(httpReqQ.remoteIp.count().desc())
					.groupBy(httpReqQ.remoteIp)
					.list(new QBarChartStatisticsEntry(httpReqQ.remoteIp,
							httpReqQ.remoteIp.count()));
		} else if (groupBy != null && groupBy.equals(ClassVariableMapping.HTTP_REQ_COUNTRY)) {
			return queryPart
					.orderBy(httpReqQ.remoteCountry.count().desc())
					.groupBy(httpReqQ.remoteCountry)
					.list(new QBarChartStatisticsEntry(httpReqQ.remoteCountry,
							httpReqQ.remoteCountry.count()));
		} else if (groupBy != null && groupBy.equals(ClassVariableMapping.HTTP_REQ_HOST)) {
			return queryPart
					.orderBy(httpReqQ.host.count().desc())
					.groupBy(httpReqQ.host)
					.list(new QBarChartStatisticsEntry(httpReqQ.host,
							httpReqQ.host.count()));
		}  
		else {
			
			//TODO: throw unsupported exception
			return null;
		}
	}

	private static BooleanBuilder buildWhereClause(final Long trafficCaptureId,
			String searchString, final ExposeType exposeFilter) {
		final BooleanBuilder booleanBuilder = new BooleanBuilder(
				trafficCaptureQ.id.eq(trafficCaptureId));
		if (searchString != null && searchString.length() > 0) {
			searchString = DomainUtil.prepareSearchString(searchString);
			booleanBuilder.andAnyOf(
					httpReqQ.url.lower().like(searchString),
					httpReqQ.remoteIp.lower().like(searchString.toLowerCase()),
					httpReqQ.remotePort.stringValue().lower()
							.like(searchString.toLowerCase()),
					httpReqQ.localPort.stringValue().lower()
							.like(searchString.toLowerCase()),
					httpReqQ.remoteCountry.lower().like(
							searchString.toLowerCase()));
		}
		if (exposeFilter != null) {
			booleanBuilder.and(dataExposeQ.exposeType.eq(exposeFilter));
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
						ClassVariableMapping.HTTP_REQ_URL)) {
					if (orderByPair.getV2()) {
						orderBys.add(httpReqQ.url.asc());
					} else {
						orderBys.add(httpReqQ.url.desc());
					}
				} else if (orderByPair.getV1().equals(
						ClassVariableMapping.HTTP_REQ_LOCAL_PORT)) {
					if (orderByPair.getV2()) {
						orderBys.add(httpReqQ.localPort.asc());
					} else {
						orderBys.add(httpReqQ.localPort.desc());
					}
				} else if (orderByPair.getV1().equals(
						ClassVariableMapping.HTTP_REQ_REMOTE_PORT)) {
					if (orderByPair.getV2()) {
						orderBys.add(httpReqQ.remotePort.asc());
					} else {
						orderBys.add(httpReqQ.remotePort.desc());
					}
				} else if (orderByPair.getV1().equals(
						ClassVariableMapping.HTTP_REQ_REMOTE_IP)) {
					if (orderByPair.getV2()) {
						orderBys.add(httpReqQ.remoteIp.asc());
					} else {
						orderBys.add(httpReqQ.remoteIp.desc());
					}
				} else if (orderByPair.getV1().equals(
						ClassVariableMapping.HTTP_REQ_COUNTRY)) {
					if (orderByPair.getV2()) {
						orderBys.add(httpReqQ.remoteCountry.asc());
					} else {
						orderBys.add(httpReqQ.remoteCountry.desc());
					}
				} else if (orderByPair.getV1().equals(
						ClassVariableMapping.HTTP_REQ_METHOD)) {
					if (orderByPair.getV2()) {
						orderBys.add(httpReqQ.httpMethod.asc());
					} else {
						orderBys.add(httpReqQ.httpMethod.desc());
					}
				}
			}
		}

		// default sorting (if none defined)
		if (orderBys.isEmpty()) {
			orderBys.add(httpReqQ.url.asc());
		}
		return orderBys.toArray(new OrderSpecifier[orderBys.size()]);
	}

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public String getUrl() {
        return this.url;
    }

	public void setUrl(String url) {
        this.url = url;
    }

	public String getHost() {
        return this.host;
    }

	public void setHost(String host) {
        this.host = host;
    }

	public String getQueryString() {
        return this.queryString;
    }

	public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

	public String getRemoteIp() {
        return this.remoteIp;
    }

	public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

	public String getLocalIp() {
        return this.localIp;
    }

	public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

	public Integer getRemotePort() {
        return this.remotePort;
    }

	public void setRemotePort(Integer remotePort) {
        this.remotePort = remotePort;
    }

	public Integer getLocalPort() {
        return this.localPort;
    }

	public void setLocalPort(Integer localPort) {
        this.localPort = localPort;
    }

	public String getRemoteCountry() {
        return this.remoteCountry;
    }

	public void setRemoteCountry(String remoteCountry) {
        this.remoteCountry = remoteCountry;
    }

	public String getRemoteCity() {
        return this.remoteCity;
    }

	public void setRemoteCity(String remoteCity) {
        this.remoteCity = remoteCity;
    }

	public Float getRemoteLat() {
        return this.remoteLat;
    }

	public void setRemoteLat(Float remoteLat) {
        this.remoteLat = remoteLat;
    }

	public Float getRemoteLon() {
        return this.remoteLon;
    }

	public void setRemoteLon(Float remoteLon) {
        this.remoteLon = remoteLon;
    }

	public TrafficCapture getCapture() {
        return this.capture;
    }

	public void setCapture(TrafficCapture capture) {
        this.capture = capture;
    }

	public DroidHttpVersion getHttpVersion() {
        return this.httpVersion;
    }

	public void setHttpVersion(DroidHttpVersion httpVersion) {
        this.httpVersion = httpVersion;
    }

	public DroidHttpMethod getHttpMethod() {
        return this.httpMethod;
    }

	public void setHttpMethod(DroidHttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

	public List<DroidHttpHeaderField> getHeaderFields() {
        return this.headerFields;
    }

	public void setHeaderFields(List<DroidHttpHeaderField> headerFields) {
        this.headerFields = headerFields;
    }

	public List<DroidHttpParameter> getParameters() {
        return this.parameters;
    }

	public void setParameters(List<DroidHttpParameter> parameters) {
        this.parameters = parameters;
    }

	public List<DataExpose> getDataExposeList() {
        return this.dataExposeList;
    }

	public void setDataExposeList(List<DataExpose> dataExposeList) {
        this.dataExposeList = dataExposeList;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new DroidHttpRequest().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countDroidHttpRequests() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DroidHttpRequest o", Long.class).getSingleResult();
    }

	public static List<DroidHttpRequest> findAllDroidHttpRequests() {
        return entityManager().createQuery("SELECT o FROM DroidHttpRequest o", DroidHttpRequest.class).getResultList();
    }

	public static DroidHttpRequest findDroidHttpRequest(Long id) {
        if (id == null) return null;
        return entityManager().find(DroidHttpRequest.class, id);
    }

	public static List<DroidHttpRequest> findDroidHttpRequestEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DroidHttpRequest o", DroidHttpRequest.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            DroidHttpRequest attached = DroidHttpRequest.findDroidHttpRequest(this.id);
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
    public DroidHttpRequest merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DroidHttpRequest merged = this.entityManager.merge(this);
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
