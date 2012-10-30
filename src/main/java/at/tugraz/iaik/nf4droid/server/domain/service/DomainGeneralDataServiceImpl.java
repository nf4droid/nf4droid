	package at.tugraz.iaik.nf4droid.server.domain.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Configurable;

import at.tugraz.iaik.nf4droid.server.common.FixedMySQLTemplates;
import at.tugraz.iaik.nf4droid.server.domain.DomainUtil;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QApp;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QAppVersion;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidEthernetFrame;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidIpv4Packet;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidPcapPacket;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidProcessingResult;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QTrafficCapture;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidEthernetFrame;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidHttpHeaderField;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidHttpParameter;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidHttpReqHeaders;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidHttpReqParams;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidHttpRequest;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidIpv4packet;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidPcapPacket;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidProcResHttpRequests;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidProcResTcpSegmnets;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidProcessingResult;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidTcpSegment;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlTrafficCapture;
import at.tugraz.iaik.nf4droid.shared.dto.AppSuggestion;
import at.tugraz.iaik.nf4droid.shared.dto.AppVersionSuggestion;
import at.tugraz.iaik.nf4droid.shared.dto.QAppSuggestion;
import at.tugraz.iaik.nf4droid.shared.dto.QAppVersionSuggestion;
import at.tugraz.iaik.nf4droid.shared.dto.QTrafficAmountInfo;
import at.tugraz.iaik.nf4droid.shared.dto.TimeValuePair;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficAmountInfo;

import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.sql.JPASQLQuery;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.Expression;
import com.mysema.query.types.QTuple;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathBuilder;
import com.mysema.query.types.path.StringPath;
import com.mysema.query.types.query.ListSubQuery;

@Configurable
public class DomainGeneralDataServiceImpl implements DomainGeneralDataService {
	private static final QSqlTrafficCapture trafficCapQSql = QSqlTrafficCapture.trafficCapture;
	private static final QSqlDroidProcessingResult procResQSql = QSqlDroidProcessingResult.droidProcessingResult;
	private static final QSqlDroidTcpSegment tcpSegQSql = QSqlDroidTcpSegment.droidTcpSegment;
	private static final QSqlDroidProcResTcpSegmnets procResTcpSegQSql = QSqlDroidProcResTcpSegmnets.droidProcResTcpSegmnets;
	private static final QSqlDroidIpv4packet ipv4PacketQSql = QSqlDroidIpv4packet.droidIpv4packet;
	private static final QSqlDroidEthernetFrame ethFrameQSql = QSqlDroidEthernetFrame.droidEthernetFrame;
	private static final QSqlDroidPcapPacket pcapPacketQSql = QSqlDroidPcapPacket.droidPcapPacket;
	private static final QSqlDroidHttpRequest httpReqQSql = QSqlDroidHttpRequest.droidHttpRequest;
	private static final QSqlDroidProcResHttpRequests procResHttpReqQSql = QSqlDroidProcResHttpRequests.droidProcResHttpRequests;
	private static final QSqlDroidHttpHeaderField httpHeaderQSql = QSqlDroidHttpHeaderField.droidHttpHeaderField;
	private static final QSqlDroidHttpReqHeaders httpReqHeadersQSql = QSqlDroidHttpReqHeaders.droidHttpReqHeaders;
	private static final QSqlDroidHttpParameter httpParamQSql = QSqlDroidHttpParameter.droidHttpParameter;
	private static final QSqlDroidHttpReqParams httpReqParamsQSql = QSqlDroidHttpReqParams.droidHttpReqParams;
	
	private static final QTrafficCapture trafficCapQ = QTrafficCapture.trafficCapture;
	private static final QDroidProcessingResult procResQ = QDroidProcessingResult.droidProcessingResult;
	private static final QDroidIpv4Packet ipv4PacketQ = QDroidIpv4Packet.droidIpv4Packet;
	private static final QDroidEthernetFrame ethFrameQ = QDroidEthernetFrame.droidEthernetFrame;
	private static final QDroidPcapPacket pcapPacketQ = QDroidPcapPacket.droidPcapPacket;
	private static final QApp appQ = QApp.app;
	private static final QAppVersion appVersionQ = QAppVersion.appVersion;

	private EntityManager entityManager;

	@PersistenceContext(unitName = "persistenceUnit")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private JPAQuery from(EntityPath<?>... o) {
		return new JPAQuery(entityManager).from(o);
	}
	
	private JPASQLQuery sqlFrom(Expression<?>... e) {
		return new JPASQLQuery(entityManager, new FixedMySQLTemplates())
				.from(e);
	}
	
	public List<TrafficAmountInfo> findIpv4CountryTrafficAmount(
			final Long trafficCaptureId) {
		return from(trafficCapQ)
				.join(trafficCapQ.processingResult, procResQ)
				.join(procResQ.ipv4Packets, ipv4PacketQ)
				.join(ipv4PacketQ.ethernetFrame, ethFrameQ)
				.join(ethFrameQ.pcapPacket, pcapPacketQ)
				.where(trafficCapQ.id.eq(trafficCaptureId)
						.and(ipv4PacketQ.remoteCountry.isNotNull())
						.and(ipv4PacketQ.remoteCountry.isNotEmpty()))
				.groupBy(ipv4PacketQ.remoteCountry)
				.list(new QTrafficAmountInfo(pcapPacketQ.originalLength.sum(),
						ipv4PacketQ.count(), ipv4PacketQ.remoteCountry));

	}
	
	public TrafficAmountInfo findIpv4TotalTrafficAmount(
			final Long trafficCaptureId) {
		return from(trafficCapQ)
				.join(trafficCapQ.processingResult, procResQ)
				.join(procResQ.ipv4Packets, ipv4PacketQ)
				.join(ipv4PacketQ.ethernetFrame, ethFrameQ)
				.join(ethFrameQ.pcapPacket, pcapPacketQ)
				.where(trafficCapQ.id.eq(trafficCaptureId))
				.singleResult(
						new QTrafficAmountInfo(
								pcapPacketQ.originalLength.sum(), ipv4PacketQ
										.count()));
	}

	public TrafficAmountInfo findIpv4CountryUnknownTrafficAmount(
			final Long trafficCaptureId) {
		return from(trafficCapQ)
				.join(trafficCapQ.processingResult, procResQ)
				.join(procResQ.ipv4Packets, ipv4PacketQ)
				.join(ipv4PacketQ.ethernetFrame, ethFrameQ)
				.join(ethFrameQ.pcapPacket, pcapPacketQ)
				.where(trafficCapQ.id.eq(trafficCaptureId)
						.and(ipv4PacketQ.remoteCountry.isNull())
						.or(ipv4PacketQ.remoteCountry.isEmpty()))
				.singleResult(
						new QTrafficAmountInfo(
								pcapPacketQ.originalLength.sum(), ipv4PacketQ
										.count()));
	}

	public List<AppSuggestion> findAppSuggestionWherePackageNameLike(final String appPackageLike,
			final Long limit) {
		List<AppSuggestion> suggestions = from(appQ)
				.where(appQ.appPackage.like(DomainUtil
						.prepareSearchString(appPackageLike))).limit(limit)
				.list(new QAppSuggestion(appQ.appPackage, appQ.appName));
		if (suggestions != null) {
			for (AppSuggestion appSuggestion : suggestions) {
				appSuggestion.setHighlight(appPackageLike);
			}
		}
		return suggestions;
	}

	public List<AppVersionSuggestion> findAppVersionSuggestionsWhereAppPackageEqAndVersionCodeLike(
			final String appPackage, final String appVersionCodeLike,
			final Long limit) {
		List<AppVersionSuggestion> suggestions = from(appQ)
				.join(appQ.appVersions, appVersionQ)
				.where(appQ.appPackage
						.eq(appPackage)
						.and(appVersionQ.versionCode
								.stringValue()
								.like(DomainUtil
										.prepareSearchString(appVersionCodeLike))))
				.list(new QAppVersionSuggestion(appVersionQ.versionCode,
						appVersionQ.versionName));
		if (suggestions != null) {
			for (AppVersionSuggestion appVersionSuggestion : suggestions) {
				appVersionSuggestion.setHighlight(appVersionCodeLike);
			}
		}
		return suggestions;
	}

	
	public List<TimeValuePair> findHttpReqIdTimeWhereParameterLike(
			final String value, final Long trafficCaptureId) {
		QSqlDroidHttpParameter httpParam = QSqlDroidHttpParameter.droidHttpParameter;

		final BooleanExpression condition = httpParam.parameterValue.like(DomainUtil
				.prepareSearchString(value));

		return findHttpReqIdTimeWhereCondition(trafficCaptureId, condition);
	}
	
	public List<TimeValuePair> findHttpReqIdTimeWhereHeaderFieldLike(
			final String value, final Long trafficCaptureId) {
		QSqlDroidHttpHeaderField httpHeader = QSqlDroidHttpHeaderField.droidHttpHeaderField;

		final BooleanExpression condition = httpHeader.fieldValue.like(DomainUtil
				.prepareSearchString(value));

		return findHttpReqIdTimeWhereCondition(trafficCaptureId, condition);
	}
	
	private List<TimeValuePair> findHttpReqIdTimeWhereCondition(final Long trafficCaptureId, final BooleanExpression condition) {
		final PathBuilder<Tuple> sub = new PathBuilder<Tuple>(Tuple.class,
				"sub");

		final NumberPath<Integer> sourcePort = new NumberPath<Integer>(Integer.class,
				"source_port");
		final StringPath sourceIp = new StringPath("source_ip");
		final NumberPath<Integer> destPort = new NumberPath<Integer>(Integer.class,
				"dest_port");
		final StringPath destIp = new StringPath("dest_ip");
		final NumberPath<Long> httpReqId = new NumberPath<Long>(Long.class,
				"http_req_id");

		final ListSubQuery<Tuple> subQuery = new SQLSubQuery()
				.from(trafficCapQSql)
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(trafficCapQSql.processingResult))
				.innerJoin(procResHttpReqQSql)
				.on(procResQSql.id.eq(procResHttpReqQSql.droidProcResId))
				.innerJoin(httpReqQSql)
				.on(procResHttpReqQSql.httpRequestId.eq(httpReqQSql.id))
				.innerJoin(httpReqHeadersQSql)
				.on(httpReqQSql.id.eq(httpReqHeadersQSql.httpRequestId))
				.innerJoin(httpHeaderQSql)
				.on(httpReqHeadersQSql.httpHeaderId.eq(httpHeaderQSql.id))
				.innerJoin(httpReqParamsQSql)
				.on(httpReqQSql.id.eq(httpReqParamsQSql.httpRequestId))
				.innerJoin(httpParamQSql)
				.on(httpReqParamsQSql.httpParameterId.eq(httpParamQSql.id))
				.where(trafficCapQSql.id.eq(trafficCaptureId).and(condition))
				.groupBy(httpReqQSql.id)
				.list(new QTuple(httpReqQSql.id.as(httpReqId),
						httpReqQSql.localIp.as(sourceIp), httpReqQSql.localPort
								.as(sourcePort), httpReqQSql.remoteIp
								.as(destIp), httpReqQSql.remotePort
								.as(destPort)));

		final List<Object[]> httpReqIdTimeValues = sqlFrom(trafficCapQSql)
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
				.groupBy(sub.get(httpReqId))
				.list(pcapPacketQSql.arrivalTimeInstant,
						sub.get(httpReqId));

		if (httpReqIdTimeValues == null) {
			return null;
		} else {
			final List<TimeValuePair> httpReqIdTimeValuePairs = new ArrayList<TimeValuePair>();
			for (Object[] httpReqIdTimeValue : httpReqIdTimeValues) {
				httpReqIdTimeValuePairs.add(new TimeValuePair(((BigInteger) httpReqIdTimeValue[0])
						.longValue(), ((BigInteger) httpReqIdTimeValue[1]).longValue()));
			}

			return httpReqIdTimeValuePairs;
		}
	}

}
