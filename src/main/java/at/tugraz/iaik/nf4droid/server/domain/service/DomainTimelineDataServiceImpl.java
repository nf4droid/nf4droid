package at.tugraz.iaik.nf4droid.server.domain.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Configurable;

import at.tugraz.iaik.nf4droid.server.common.FixedMySQLTemplates;
import at.tugraz.iaik.nf4droid.server.domain.DomainUtil;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidEthernetFrame;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidIpv4Packet;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidPcapPacket;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidProcessingResult;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QTrafficCapture;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidEthernetFrame;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidHttpRequest;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidIpv4packet;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidPcapPacket;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidProcResHttpRequests;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidProcResTcpSegmnets;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidProcessingResult;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidTcpSegment;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlTrafficCapture;
import at.tugraz.iaik.nf4droid.shared.constant.FilterOperation;
import at.tugraz.iaik.nf4droid.shared.dto.Criteria;
import at.tugraz.iaik.nf4droid.shared.dto.QTimeValuePair;
import at.tugraz.iaik.nf4droid.shared.dto.TimeValuePair;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.sql.JPASQLQuery;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.Path;
import com.mysema.query.types.QTuple;
import com.mysema.query.types.SubQueryExpression;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.expr.NumberExpression;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathBuilder;
import com.mysema.query.types.path.StringPath;
import com.mysema.query.types.query.ListSubQuery;

@Configurable
public class DomainTimelineDataServiceImpl implements DomainTimelineDataService {

	private static final QSqlTrafficCapture trafficCapQSql = QSqlTrafficCapture.trafficCapture;
	private static final QSqlDroidProcessingResult procResQSql = QSqlDroidProcessingResult.droidProcessingResult;
	private static final QSqlDroidTcpSegment tcpSegQSql = QSqlDroidTcpSegment.droidTcpSegment;
	private static final QSqlDroidProcResTcpSegmnets procResTcpSegQSql = QSqlDroidProcResTcpSegmnets.droidProcResTcpSegmnets;
	private static final QSqlDroidIpv4packet ipv4PacketQSql = QSqlDroidIpv4packet.droidIpv4packet;
	private static final QSqlDroidEthernetFrame ethFrameQSql = QSqlDroidEthernetFrame.droidEthernetFrame;
	private static final QSqlDroidPcapPacket pcapPacketQSql = QSqlDroidPcapPacket.droidPcapPacket;
	private static final QSqlDroidHttpRequest httpReqQSql = QSqlDroidHttpRequest.droidHttpRequest;
	private static final QSqlDroidProcResHttpRequests procResHttpReqQSql = QSqlDroidProcResHttpRequests.droidProcResHttpRequests;
	
	private static final QTrafficCapture trafficCapQ = QTrafficCapture.trafficCapture;
	private static final QDroidProcessingResult procResQ = QDroidProcessingResult.droidProcessingResult;
	private static final QDroidIpv4Packet ipv4PacketQ = QDroidIpv4Packet.droidIpv4Packet;
	private static final QDroidEthernetFrame ethFrameQ = QDroidEthernetFrame.droidEthernetFrame;
	private static final QDroidPcapPacket pcapPacketQ = QDroidPcapPacket.droidPcapPacket;
	
	private EntityManager entityManager;

	@PersistenceContext(unitName = "persistenceUnit")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private JPAQuery from(EntityPath<?>... o) {
		return new JPAQuery(entityManager).from(o);
	}

	private JPASQLQuery sqlFrom(SubQueryExpression<?> subQuery, Path<?> alias) {
		return new JPASQLQuery(entityManager, new FixedMySQLTemplates()).from(
				subQuery, alias);
	}

	public Tuple getFirstAndLastTimeValueForTrafficTimeline(
			final Long trafficCaptureId) {

		return from(trafficCapQ)
				.join(trafficCapQ.processingResult, procResQ)
				.join(procResQ.pcapPackets, pcapPacketQ)
				.where(trafficCapQ.id.eq(trafficCaptureId))
				.singleResult(
						new QTuple(pcapPacketQ.arrivalTimeInstant.min().as(
								"startTime"), pcapPacketQ.arrivalTimeInstant
								.max().as("endTime")));
	}

	public List<TimeValuePair> findTrafficTimelineData(Long trafficCaptureId,
			Integer intervalInMs) {

		NumberExpression<Long> intervalExpr = pcapPacketQ.arrivalTimeInstant
				.castToNum(Long.class).divide(intervalInMs).floor().longValue()
				.multiply(intervalInMs);

		return from(trafficCapQ)
				.join(trafficCapQ.processingResult, procResQ)
				.join(procResQ.pcapPackets, pcapPacketQ)
				.where(trafficCapQ.id.eq(trafficCaptureId))
				.groupBy(intervalExpr)
				.orderBy(intervalExpr.asc())
				.list(new QTimeValuePair(intervalExpr,
						pcapPacketQ.originalLength.castToNum(Long.class).sum()));
	}

	public List<TimeValuePair> findTrafficTimelineDataForHttpRequests(
			Long trafficCaptureId, Integer intervalInMs) {

		String subQueryAlias = "sub";
		PathBuilder<Tuple> sub = new PathBuilder<Tuple>(Tuple.class,
				subQueryAlias);

		NumberPath<Integer> sourcePort = new NumberPath<Integer>(Integer.class,
				"source_port");
		StringPath sourceIp = new StringPath("source_ip");
		NumberPath<Integer> destPort = new NumberPath<Integer>(Integer.class,
				"dest_port");
		StringPath destIp = new StringPath("dest_ip");
		NumberPath<Long> httpReqId = new NumberPath<Long>(Long.class,
				"http_req_id");

		PathBuilder<Tuple> joinedDataSub = new PathBuilder<Tuple>(Tuple.class,
				"joinedDataSub");
		NumberPath<Long> time = new NumberPath<Long>(Long.class, "arrival_time");
		NumberPath<Long> length = new NumberPath<Long>(Long.class,
				"orig_length");
		NumberExpression<BigInteger> intervalExpr = joinedDataSub.get(time)
				.castToNum(Long.class).divide(intervalInMs).floor()
				.multiply(intervalInMs).castToNum(BigInteger.class);

		ListSubQuery<Tuple> subQuery = new SQLSubQuery()
				.from(trafficCapQSql)
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(trafficCapQSql.processingResult))
				.innerJoin(procResHttpReqQSql)
				.on(procResQSql.id.eq(procResHttpReqQSql.droidProcResId))
				.innerJoin(httpReqQSql)
				.on(procResHttpReqQSql.httpRequestId.eq(httpReqQSql.id))
				.where(trafficCapQSql.id.eq(trafficCaptureId))
				.groupBy(httpReqQSql.id)
				.list(new QTuple(httpReqQSql.id.as(httpReqId),
						httpReqQSql.localIp.as(sourceIp), httpReqQSql.localPort
								.as(sourcePort), httpReqQSql.remoteIp
								.as(destIp), httpReqQSql.remotePort
								.as(destPort)));

		ListSubQuery<Tuple> joinedQuery = new SQLSubQuery()
				.from(trafficCapQSql)
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(trafficCapQSql.processingResult))
				.innerJoin(procResTcpSegQSql)
				.on(procResTcpSegQSql.droidProcResId.eq(procResQSql.id))
				.innerJoin(tcpSegQSql)
				.on(tcpSegQSql.id.eq(procResTcpSegQSql.tcpSegmId))
				.innerJoin(subQuery, sub)
				.on(sub.get(sourcePort).eq(tcpSegQSql.sourcePort)
						.and(sub.get(destPort).eq(tcpSegQSql.destPort)))
				.innerJoin(ipv4PacketQSql)
				.on(tcpSegQSql.ipv4packet.eq(ipv4PacketQSql.id)
						.and(sub.get(sourceIp).eq(ipv4PacketQSql.sourceIp))
						.and(sub.get(destIp).eq(ipv4PacketQSql.destIp)))
				.innerJoin(ethFrameQSql)
				.on(ipv4PacketQSql.ethernetFrame.eq(ethFrameQSql.id))
				.innerJoin(pcapPacketQSql)
				.on(ethFrameQSql.pcapPacket.eq(pcapPacketQSql.id))
				.where(trafficCapQSql.id.eq(trafficCaptureId))
				.groupBy(pcapPacketQSql.arrivalTimeInstant)
				.list(new QTuple(pcapPacketQSql.arrivalTimeInstant.as(time),
						pcapPacketQSql.originalLength.as(length)));

		List<Object[]> trafficAmountAtHttpReq = sqlFrom(joinedQuery,
				joinedDataSub).groupBy(intervalExpr)
				.orderBy(intervalExpr.asc())
				.list(intervalExpr, joinedDataSub.get(length).sum());

		if (trafficAmountAtHttpReq == null) {
			return null;
		} else {
			List<TimeValuePair> timeValuePairs = new ArrayList<TimeValuePair>();
			for (Object[] object : trafficAmountAtHttpReq) {
				timeValuePairs.add(new TimeValuePair(((BigInteger) object[0])
						.longValue(), ((BigDecimal) object[1]).longValue()));
			}

			return timeValuePairs;
		}
	}
	public List<TimeValuePair> findTrafficTimelineDataForIpv4ByCriteria(
			final Long trafficCaptureId, final Integer intervalInMs, final Criteria criterias) {
		final BooleanExpression condition;
		if (criterias != null) {
			final BooleanBuilder builder = createConditionsFromCriteria(criterias);
			if (builder.hasValue()) {
				condition = trafficCapQ.id.eq(trafficCaptureId).and(builder);
			} else {
				condition = trafficCapQ.id.eq(trafficCaptureId);
			}
		} else {
			condition = trafficCapQ.id.eq(trafficCaptureId);
		}
		return findTrafficTimelineDataForIpv4(intervalInMs, condition);
	}

	private List<TimeValuePair> findTrafficTimelineDataForIpv4(
			Integer intervalInMs,
			com.mysema.query.types.Predicate... conditions) {
		NumberExpression<Long> interval = pcapPacketQ.arrivalTimeInstant
				.castToNum(Long.class).divide(intervalInMs).floor().longValue()
				.multiply(intervalInMs);

		return from(trafficCapQ)
				.join(trafficCapQ.processingResult, procResQ)
				.join(procResQ.ipv4Packets, ipv4PacketQ)
				.join(ipv4PacketQ.ethernetFrame, ethFrameQ)
				.join(ethFrameQ.pcapPacket, pcapPacketQ)
				.where(conditions)
				.groupBy(interval)
				.orderBy(interval.asc())
				.list(new QTimeValuePair(interval, pcapPacketQ.originalLength
						.castToNum(Long.class).sum()));
	}
	
	private BooleanBuilder createConditionsFromCriteria(
			final Criteria criteria) {
		final BooleanBuilder conditions = new BooleanBuilder();
		addCriteriaToCondition(criteria, conditions, FilterOperation.AND);
		return conditions;
	}

	private void addCriteriaToCondition(final Criteria criteria,
			final BooleanBuilder conditions, final FilterOperation conjunction) {

		if ((criteria.getOperation() == FilterOperation.AND)
				&& (criteria.getCriterias() != null)
				&& (!criteria.getCriterias().isEmpty())) {
			for (Criteria subCriteria : criteria.getCriterias()) {
				addCriteriaToCondition(subCriteria, conditions,
						FilterOperation.AND);
			}
		} else if ((criteria.getOperation() == FilterOperation.OR)
				&& (criteria.getCriterias() != null)
				&& (!criteria.getCriterias().isEmpty())) {
			for (Criteria subCriteria : criteria.getCriterias()) {
				addCriteriaToCondition(subCriteria, conditions,
						FilterOperation.OR);
			}
		} else if (criteria.getOperation() == FilterOperation.EQUALS) {
			final BooleanExpression expr = getPathForFieldName(
					criteria.getFieldName()).eq(criteria.getValue());

			if (conjunction == FilterOperation.AND) {
				conditions.and(expr);
			} else {
				conditions.or(expr);
			}

		} else if (criteria.getOperation() == FilterOperation.NOT_EQUALS) {
			final BooleanExpression expr = getPathForFieldName(
					criteria.getFieldName()).ne(criteria.getValue());

			if (conjunction == FilterOperation.AND) {
				conditions.and(expr);
			} else {
				conditions.or(expr);
			}
		} else if (criteria.getOperation() == FilterOperation.LIKE) {
			final BooleanExpression expr = getPathForFieldName(
					criteria.getFieldName()).like(
					DomainUtil.prepareSearchString(criteria.getValue()));

			if (conjunction == FilterOperation.AND) {
				conditions.and(expr);
			} else {
				conditions.or(expr);
			}
		} else if (criteria.getOperation() == FilterOperation.NOT_LIKE) {
			final BooleanExpression expr = getPathForFieldName(
					criteria.getFieldName()).like(
					DomainUtil.prepareSearchString(criteria.getValue())).not();

			if (conjunction == FilterOperation.AND) {
				conditions.and(expr);
			} else {
				conditions.or(expr);
			}
		}
	}

	private StringPath getPathForFieldName(final String fieldName) {
		if (fieldName.equals(Criteria.IpFields.SOURCE_IP.getValue())) {
			return ipv4PacketQ.sourceIp;
		} else if (fieldName.equals(Criteria.IpFields.DEST_IP.getValue())) {
			return ipv4PacketQ.destIp;
		} else if (fieldName.equals(Criteria.IpFields.COUNTRY.getValue())) {
			return ipv4PacketQ.remoteCountry;
		} else if (fieldName.equals(Criteria.IpFields.CITY.getValue())) {
			return ipv4PacketQ.remoteCity;
		}
		return null;
	}
	
}
