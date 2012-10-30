package at.tugraz.iaik.nf4droid.server.domain.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Configurable;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidProcessingResult;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDataExpose;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidArpPacket;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidEthernetFrame;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidIpv4Packet;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidIpv6Packet;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidPcapPacket;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidProcessingResult;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidTcpSegment;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QDroidUdpPacket;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa.QTrafficCapture;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidEthernetFrame;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidIpv4packet;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidIpv6packet;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidPcapPacket;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidProcResTcpSegmnets;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidProcResUdpPackets;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidProcessingResult;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidTcpSegment;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlDroidUdpPacket;
import at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql.QSqlTrafficCapture;
import at.tugraz.iaik.nf4droid.shared.dto.BarChartExposeEntry;
import at.tugraz.iaik.nf4droid.shared.dto.QBarChartExposeEntry;
import at.tugraz.iaik.nf4droid.shared.dto.QTrafficAmountInfo;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficAmountInfo;

import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.sql.JPASQLQuery;
import com.mysema.query.sql.MySQLTemplates;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.QTuple;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathBuilder;
import com.mysema.query.types.query.ListSubQuery;

@Configurable
public class DomainDashboardDataServiceImpl implements
		DomainDashboardDataService {

	private static final QSqlTrafficCapture trafficCapQSql = QSqlTrafficCapture.trafficCapture;
	private static final QSqlDroidProcessingResult procResQSql = QSqlDroidProcessingResult.droidProcessingResult;
	private static final QSqlDroidTcpSegment tcpSegQSql = QSqlDroidTcpSegment.droidTcpSegment;
	private static final QSqlDroidProcResTcpSegmnets procResTcpSegQSql = QSqlDroidProcResTcpSegmnets.droidProcResTcpSegmnets;
	private static final QSqlDroidUdpPacket udpPacketQSql = QSqlDroidUdpPacket.droidUdpPacket;
	private static final QSqlDroidProcResUdpPackets procResUdpPktQSql = QSqlDroidProcResUdpPackets.droidProcResUdpPackets;
	private static final QSqlDroidIpv4packet ipv4PacketQSql = QSqlDroidIpv4packet.droidIpv4packet;
	private static final QSqlDroidIpv6packet ipv6PacketQSql = QSqlDroidIpv6packet.droidIpv6packet;
	private static final QSqlDroidEthernetFrame ethFrameQSql = QSqlDroidEthernetFrame.droidEthernetFrame;
	private static final QSqlDroidPcapPacket pcapPacketQSql = QSqlDroidPcapPacket.droidPcapPacket;

	private static final QTrafficCapture trafficCapQ = QTrafficCapture.trafficCapture;
	private static final QDroidProcessingResult procResQ = QDroidProcessingResult.droidProcessingResult;
	private static final QDroidIpv4Packet ipv4PacketQ = QDroidIpv4Packet.droidIpv4Packet;
	private static final QDroidIpv6Packet ipv6PacketQ = QDroidIpv6Packet.droidIpv6Packet;
	private static final QDroidEthernetFrame ethFrameQ = QDroidEthernetFrame.droidEthernetFrame;
	private static final QDroidPcapPacket pcapPacketQ = QDroidPcapPacket.droidPcapPacket;
	private static final QDroidArpPacket arpPacketQ = QDroidArpPacket.droidArpPacket;
	private static final QDroidTcpSegment tcpSegmentQ = QDroidTcpSegment.droidTcpSegment;
	private static final QDroidUdpPacket udpPacketQ = QDroidUdpPacket.droidUdpPacket;
	private static final QDataExpose dataExposeQ = QDataExpose.dataExpose;

	private static final int WELL_KNOWN_PORTS_MAX = 1023;
	private static final int REGISTERED_PORTS_MAX = 49151;

	private EntityManager entityManager;

	@PersistenceContext(unitName = "persistenceUnit")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private JPAQuery from(EntityPath<?>... o) {
		return new JPAQuery(entityManager).from(o);
	}
	
	public Long coundDataExposures(final Long trafficCaptureId) {
		return from(trafficCapQ)
				.join(trafficCapQ.processingResult, procResQ)
				.join(procResQ.dataExposes, dataExposeQ)
				.where(trafficCapQ.id.eq(trafficCaptureId))
				.count();
	}

	public List<BarChartExposeEntry> findDataExposureAmount(final Long trafficCaptureId) {
		return from(trafficCapQ)
				.join(trafficCapQ.processingResult, procResQ)
				.join(procResQ.dataExposes, dataExposeQ)
				.where(trafficCapQ.id.eq(trafficCaptureId))
				.groupBy(dataExposeQ.exposeType)
				.list(new QBarChartExposeEntry(dataExposeQ.id.count(), dataExposeQ.exposeType));
	}

	public TrafficAmountInfo getTcpIpv4TrafficAmountInfo(
			final Long trafficCaptureId) {
		return from(trafficCapQ)
				.join(trafficCapQ.processingResult, procResQ)
				.join(procResQ.tcpSegments, tcpSegmentQ)
				.join(tcpSegmentQ.ipv4Packet, ipv4PacketQ)
				.join(ipv4PacketQ.ethernetFrame, ethFrameQ)
				.join(ethFrameQ.pcapPacket, pcapPacketQ)
				.where(trafficCapQ.id.eq(trafficCaptureId))
				.singleResult(
						new QTrafficAmountInfo(
								pcapPacketQ.originalLength.sum(),
								tcpSegmentQ.id.count()));
	}

	public TrafficAmountInfo getTcpIpv6TrafficAmountInfo(
			final Long trafficCaptureId) {
		return from(trafficCapQ)
				.join(trafficCapQ.processingResult, procResQ)
				.join(procResQ.tcpSegments, tcpSegmentQ)
				.join(tcpSegmentQ.ipv6Packet, ipv6PacketQ)
				.join(ipv6PacketQ.ethernetFrame, ethFrameQ)
				.join(ethFrameQ.pcapPacket, pcapPacketQ)
				.where(trafficCapQ.id.eq(trafficCaptureId))
				.singleResult(
						new QTrafficAmountInfo(
								pcapPacketQ.originalLength.sum(),
								tcpSegmentQ.id.count()));
	}

	public TrafficAmountInfo getUdpIpv4TrafficAmountInfo(
			final Long trafficCaptureId) {
		return from(trafficCapQ)
				.join(trafficCapQ.processingResult, procResQ)
				.join(procResQ.udpPackets, udpPacketQ)
				.join(udpPacketQ.ipv4Packet, ipv4PacketQ)
				.join(ipv4PacketQ.ethernetFrame, ethFrameQ)
				.join(ethFrameQ.pcapPacket, pcapPacketQ)
				.where(trafficCapQ.id.eq(trafficCaptureId))
				.singleResult(
						new QTrafficAmountInfo(
								pcapPacketQ.originalLength.sum(), udpPacketQ.id
										.count()));
	}

	public TrafficAmountInfo getUdpIpv6TrafficAmountInfo(
			final Long trafficCaptureId) {
		return from(trafficCapQ)
				.join(trafficCapQ.processingResult, procResQ)
				.join(procResQ.udpPackets, udpPacketQ)
				.join(udpPacketQ.ipv6Packet, ipv6PacketQ)
				.join(ipv6PacketQ.ethernetFrame, ethFrameQ)
				.join(ethFrameQ.pcapPacket, pcapPacketQ)
				.where(trafficCapQ.id.eq(trafficCaptureId))
				.singleResult(
						new QTrafficAmountInfo(
								pcapPacketQ.originalLength.sum(), udpPacketQ.id
										.count()));
	}

	public TrafficAmountInfo getIpv4TrafficAmountInfo(
			final Long trafficCaptureId) {
		return from(trafficCapQ)
				.join(trafficCapQ.processingResult, procResQ)
				.join(procResQ.ipv4Packets, ipv4PacketQ)
				.join(ipv4PacketQ.ethernetFrame, ethFrameQ)
				.join(ethFrameQ.pcapPacket, pcapPacketQ)
				.where(trafficCapQ.id.eq(trafficCaptureId))
				.singleResult(
						new QTrafficAmountInfo(
								pcapPacketQ.originalLength.sum(),
								ipv4PacketQ.id.count()));
	}

	public TrafficAmountInfo getIpv6TrafficAmountInfo(
			final Long trafficCaptureId) {
		return from(trafficCapQ)
				.join(trafficCapQ.processingResult, procResQ)
				.join(procResQ.ipv6Packets, ipv6PacketQ)
				.join(ipv6PacketQ.ethernetFrame, ethFrameQ)
				.join(ethFrameQ.pcapPacket, pcapPacketQ)
				.where(trafficCapQ.id.eq(trafficCaptureId))
				.singleResult(
						new QTrafficAmountInfo(
								pcapPacketQ.originalLength.sum(),
								ipv6PacketQ.id.count()));
	}

	public TrafficAmountInfo getArpTrafficAmountInfo(final Long trafficCaptureId) {
		return from(trafficCapQ)
				.join(trafficCapQ.processingResult, procResQ)
				.join(procResQ.arpPackets, arpPacketQ)
				.join(arpPacketQ.ethernetFrame, ethFrameQ)
				.join(ethFrameQ.pcapPacket, pcapPacketQ)
				.where(trafficCapQ.id.eq(trafficCaptureId))
				.singleResult(
						new QTrafficAmountInfo(
								pcapPacketQ.originalLength.sum(), arpPacketQ.id
										.count()));
	}

	/**
	 * Get list of traffic amount and number of packets for TCP and UDP ports.
	 * 
	 * @param processingResultId
	 *            {@link DroidProcessingResult} Id
	 * @param maxPort
	 *            Upper limit up to which ports should be considered.
	 * @return List of @see TrafficPortCountTriple.
	 */
	private List<TrafficAmountInfo> getTrafficAmountForPorts(
			final Long trafficCaptureId, final int maxPort) {
		JPASQLQuery sqlQuery = new JPASQLQuery(this.entityManager,
				new MySQLTemplates());

		NumberPath<Integer> port = new NumberPath<Integer>(Integer.class,
				"port");
		NumberPath<Long> length = new NumberPath<Long>(Long.class, "length");

		ListSubQuery<Tuple> subTcpSegIn = new SQLSubQuery()
				.from(pcapPacketQSql)
				.innerJoin(ethFrameQSql)
				.on(ethFrameQSql.pcapPacket.eq(pcapPacketQSql.id))
				.innerJoin(ipv4PacketQSql)
				.on(ipv4PacketQSql.ethernetFrame.eq(ethFrameQSql.id))
				.innerJoin(tcpSegQSql)
				.on(tcpSegQSql.ipv4packet.eq(ipv4PacketQSql.id))
				.innerJoin(procResTcpSegQSql)
				.on(procResTcpSegQSql.tcpSegmId.eq(tcpSegQSql.id))
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(procResTcpSegQSql.droidProcResId))
				.innerJoin(trafficCapQSql)
				.on(trafficCapQSql.processingResult.eq(procResQSql.id))
				.where(trafficCapQSql.id.eq(trafficCaptureId)
						.and(tcpSegQSql.sourcePort.loe(maxPort))
						.and(tcpSegQSql.sourcePort.lt(tcpSegQSql.destPort)))
				.list(new QTuple(tcpSegQSql.sourcePort.as(port),
						pcapPacketQSql.originalLength.as(length)));

		ListSubQuery<Tuple> subTcpSegOut = new SQLSubQuery()
				.from(pcapPacketQSql)
				.innerJoin(ethFrameQSql)
				.on(ethFrameQSql.pcapPacket.eq(pcapPacketQSql.id))
				.innerJoin(ipv4PacketQSql)
				.on(ipv4PacketQSql.ethernetFrame.eq(ethFrameQSql.id))
				.innerJoin(tcpSegQSql)
				.on(tcpSegQSql.ipv4packet.eq(ipv4PacketQSql.id))
				.innerJoin(procResTcpSegQSql)
				.on(procResTcpSegQSql.tcpSegmId.eq(tcpSegQSql.id))
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(procResTcpSegQSql.droidProcResId))
				.innerJoin(trafficCapQSql)
				.on(trafficCapQSql.processingResult.eq(procResQSql.id))
				.where(trafficCapQSql.id.eq(trafficCaptureId)
						.and(tcpSegQSql.destPort.loe(maxPort))
						.and(tcpSegQSql.destPort.loe(tcpSegQSql.sourcePort)))
				.list(new QTuple(tcpSegQSql.destPort.as(port),
						pcapPacketQSql.originalLength.as(length)));

		ListSubQuery<Tuple> subUdpPktIn = new SQLSubQuery()
				.from(pcapPacketQSql)
				.innerJoin(ethFrameQSql)
				.on(ethFrameQSql.pcapPacket.eq(pcapPacketQSql.id))
				.innerJoin(ipv4PacketQSql)
				.on(ipv4PacketQSql.ethernetFrame.eq(ethFrameQSql.id))
				.innerJoin(udpPacketQSql)
				.on(udpPacketQSql.ipv4packet.eq(ipv4PacketQSql.id))
				.innerJoin(procResUdpPktQSql)
				.on(procResUdpPktQSql.udpPacketId.eq(udpPacketQSql.id))
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(procResUdpPktQSql.droidProcResId))
				.innerJoin(trafficCapQSql)
				.on(trafficCapQSql.processingResult.eq(procResQSql.id))
				.where(trafficCapQSql.id
						.eq(trafficCaptureId)
						.and(udpPacketQSql.sourcePort.loe(maxPort))
						.and(udpPacketQSql.sourcePort
								.lt(udpPacketQSql.destPort)))
				.list(new QTuple(udpPacketQSql.sourcePort.as(port),
						pcapPacketQSql.originalLength.as(length)));

		ListSubQuery<Tuple> subUdpPktOut = new SQLSubQuery()
				.from(pcapPacketQSql)
				.innerJoin(ethFrameQSql)
				.on(ethFrameQSql.pcapPacket.eq(pcapPacketQSql.id))
				.innerJoin(ipv4PacketQSql)
				.on(ipv4PacketQSql.ethernetFrame.eq(ethFrameQSql.id))
				.innerJoin(udpPacketQSql)
				.on(udpPacketQSql.ipv4packet.eq(ipv4PacketQSql.id))
				.innerJoin(procResUdpPktQSql)
				.on(procResUdpPktQSql.udpPacketId.eq(udpPacketQSql.id))
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(procResUdpPktQSql.droidProcResId))
				.innerJoin(trafficCapQSql)
				.on(trafficCapQSql.processingResult.eq(procResQSql.id))
				.where(trafficCapQSql.id
						.eq(trafficCaptureId)
						.and(udpPacketQSql.destPort.loe(maxPort))
						.and(udpPacketQSql.destPort
								.loe(udpPacketQSql.sourcePort)))
				.list(new QTuple(udpPacketQSql.destPort.as(port),
						pcapPacketQSql.originalLength.as(length)));

		ListSubQuery<Tuple> subTcpSegIpv6In = new SQLSubQuery()
				.from(pcapPacketQSql)
				.innerJoin(ethFrameQSql)
				.on(ethFrameQSql.pcapPacket.eq(pcapPacketQSql.id))
				.innerJoin(ipv6PacketQSql)
				.on(ipv6PacketQSql.ethernetFrame.eq(ethFrameQSql.id))
				.innerJoin(tcpSegQSql)
				.on(tcpSegQSql.ipv6packet.eq(ipv6PacketQSql.id))
				.innerJoin(procResTcpSegQSql)
				.on(procResTcpSegQSql.tcpSegmId.eq(tcpSegQSql.id))
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(procResTcpSegQSql.droidProcResId))
				.innerJoin(trafficCapQSql)
				.on(trafficCapQSql.id.eq(procResQSql.id))
				.where(trafficCapQSql.processingResult.eq(trafficCaptureId)
						.and(tcpSegQSql.sourcePort.loe(maxPort))
						.and(tcpSegQSql.sourcePort.lt(tcpSegQSql.destPort)))
				.list(new QTuple(tcpSegQSql.sourcePort.as(port),
						pcapPacketQSql.originalLength.as(length)));

		ListSubQuery<Tuple> subTcpSegIpv6Out = new SQLSubQuery()
				.from(pcapPacketQSql)
				.innerJoin(ethFrameQSql)
				.on(ethFrameQSql.pcapPacket.eq(pcapPacketQSql.id))
				.innerJoin(ipv6PacketQSql)
				.on(ipv6PacketQSql.ethernetFrame.eq(ethFrameQSql.id))
				.innerJoin(tcpSegQSql)
				.on(tcpSegQSql.ipv6packet.eq(ipv6PacketQSql.id))
				.innerJoin(procResTcpSegQSql)
				.on(procResTcpSegQSql.tcpSegmId.eq(tcpSegQSql.id))
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(procResTcpSegQSql.droidProcResId))
				.innerJoin(trafficCapQSql)
				.on(trafficCapQSql.processingResult.eq(procResQSql.id))
				.where(trafficCapQSql.id.eq(trafficCaptureId)
						.and(tcpSegQSql.destPort.loe(maxPort))
						.and(tcpSegQSql.destPort.loe(tcpSegQSql.sourcePort)))
				.list(new QTuple(tcpSegQSql.destPort.as(port),
						pcapPacketQSql.originalLength.as(length)));

		ListSubQuery<Tuple> subUdpPktIpv6In = new SQLSubQuery()
				.from(pcapPacketQSql)
				.innerJoin(ethFrameQSql)
				.on(ethFrameQSql.pcapPacket.eq(pcapPacketQSql.id))
				.innerJoin(ipv6PacketQSql)
				.on(ipv6PacketQSql.ethernetFrame.eq(ethFrameQSql.id))
				.innerJoin(udpPacketQSql)
				.on(udpPacketQSql.ipv6packet.eq(ipv6PacketQSql.id))
				.innerJoin(procResUdpPktQSql)
				.on(procResUdpPktQSql.udpPacketId.eq(udpPacketQSql.id))
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(procResUdpPktQSql.droidProcResId))
				.innerJoin(trafficCapQSql)
				.on(trafficCapQSql.processingResult.eq(procResQSql.id))
				.where(trafficCapQSql.id
						.eq(trafficCaptureId)
						.and(udpPacketQSql.sourcePort.loe(maxPort))
						.and(udpPacketQSql.sourcePort
								.lt(udpPacketQSql.destPort)))
				.list(new QTuple(udpPacketQSql.sourcePort.as(port),
						pcapPacketQSql.originalLength.as(length)));

		ListSubQuery<Tuple> subUdpPktIpv6Out = new SQLSubQuery()
				.from(pcapPacketQSql)
				.innerJoin(ethFrameQSql)
				.on(ethFrameQSql.pcapPacket.eq(pcapPacketQSql.id))
				.innerJoin(ipv6PacketQSql)
				.on(ipv6PacketQSql.ethernetFrame.eq(ethFrameQSql.id))
				.innerJoin(udpPacketQSql)
				.on(udpPacketQSql.ipv6packet.eq(ipv6PacketQSql.id))
				.innerJoin(procResUdpPktQSql)
				.on(procResUdpPktQSql.udpPacketId.eq(udpPacketQSql.id))
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(procResUdpPktQSql.droidProcResId))
				.innerJoin(trafficCapQSql)
				.on(trafficCapQSql.processingResult.eq(procResQSql.id))
				.where(trafficCapQSql.id
						.eq(trafficCaptureId)
						.and(udpPacketQSql.destPort.loe(maxPort))
						.and(udpPacketQSql.destPort
								.loe(udpPacketQSql.sourcePort)))
				.list(new QTuple(udpPacketQSql.destPort.as(port),
						pcapPacketQSql.originalLength.as(length)));

		PathBuilder<Tuple> sub = new PathBuilder<Tuple>(Tuple.class, "subQuery");

		@SuppressWarnings("unchecked")
		List<Object[]> trafficPortCounts = sqlQuery
				.unionAll(sub, subTcpSegIn, subTcpSegOut, subUdpPktIn,
						subUdpPktOut, subTcpSegIpv6In, subTcpSegIpv6Out,
						subUdpPktIpv6In, subUdpPktIpv6Out)
				.groupBy(sub.get(port))
				.list(sub.get(length).sum(), sub.get(length).count(),
						sub.get(port));

		if (trafficPortCounts == null) {
			return null;
		} else {
			List<TrafficAmountInfo> trafficPortCountsList = new ArrayList<TrafficAmountInfo>();
			for (Object[] trafficPortCount : trafficPortCounts) {
				trafficPortCountsList.add(new TrafficAmountInfo(
						((BigDecimal) trafficPortCount[0]).longValue(),
						((BigInteger) trafficPortCount[1]).longValue(),
						((Integer) trafficPortCount[2]).toString()));

			}
			return trafficPortCountsList;
		}
	}

	private List<TrafficAmountInfo> getTrafficAmountForTcpPorts(
			final Long trafficCaptureId, final int maxPort) {
		JPASQLQuery sqlQuery = new JPASQLQuery(this.entityManager,
				new MySQLTemplates());

		NumberPath<Integer> port = new NumberPath<Integer>(Integer.class,
				"port");
		NumberPath<Long> length = new NumberPath<Long>(Long.class, "length");

		ListSubQuery<Tuple> subTcpSegIn = new SQLSubQuery()
				.from(pcapPacketQSql)
				.innerJoin(ethFrameQSql)
				.on(ethFrameQSql.pcapPacket.eq(pcapPacketQSql.id))
				.innerJoin(ipv4PacketQSql)
				.on(ipv4PacketQSql.ethernetFrame.eq(ethFrameQSql.id))
				.innerJoin(tcpSegQSql)
				.on(tcpSegQSql.ipv4packet.eq(ipv4PacketQSql.id))
				.innerJoin(procResTcpSegQSql)
				.on(procResTcpSegQSql.tcpSegmId.eq(tcpSegQSql.id))
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(procResTcpSegQSql.droidProcResId))
				.innerJoin(trafficCapQSql)
				.on(trafficCapQSql.processingResult.eq(procResQSql.id))
				.where(trafficCapQSql.id.eq(trafficCaptureId)
						.and(tcpSegQSql.sourcePort.loe(maxPort))
						.and(tcpSegQSql.sourcePort.lt(tcpSegQSql.destPort)))
				.list(new QTuple(tcpSegQSql.sourcePort.as(port),
						pcapPacketQSql.originalLength.as(length)));

		ListSubQuery<Tuple> subTcpSegOut = new SQLSubQuery()
				.from(pcapPacketQSql)
				.innerJoin(ethFrameQSql)
				.on(ethFrameQSql.pcapPacket.eq(pcapPacketQSql.id))
				.innerJoin(ipv4PacketQSql)
				.on(ipv4PacketQSql.ethernetFrame.eq(ethFrameQSql.id))
				.innerJoin(tcpSegQSql)
				.on(tcpSegQSql.ipv4packet.eq(ipv4PacketQSql.id))
				.innerJoin(procResTcpSegQSql)
				.on(procResTcpSegQSql.tcpSegmId.eq(tcpSegQSql.id))
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(procResTcpSegQSql.droidProcResId))
				.innerJoin(trafficCapQSql)
				.on(trafficCapQSql.processingResult.eq(procResQSql.id))
				.where(trafficCapQSql.id.eq(trafficCaptureId)
						.and(tcpSegQSql.destPort.loe(maxPort))
						.and(tcpSegQSql.destPort.loe(tcpSegQSql.sourcePort)))
				.list(new QTuple(tcpSegQSql.destPort.as(port),
						pcapPacketQSql.originalLength.as(length)));

		ListSubQuery<Tuple> subTcpSegIpv6In = new SQLSubQuery()
				.from(pcapPacketQSql)
				.innerJoin(ethFrameQSql)
				.on(ethFrameQSql.pcapPacket.eq(pcapPacketQSql.id))
				.innerJoin(ipv6PacketQSql)
				.on(ipv6PacketQSql.ethernetFrame.eq(ethFrameQSql.id))
				.innerJoin(tcpSegQSql)
				.on(tcpSegQSql.ipv6packet.eq(ipv6PacketQSql.id))
				.innerJoin(procResTcpSegQSql)
				.on(procResTcpSegQSql.tcpSegmId.eq(tcpSegQSql.id))
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(procResTcpSegQSql.droidProcResId))
				.innerJoin(trafficCapQSql)
				.on(trafficCapQSql.processingResult.eq(procResQSql.id))
				.where(trafficCapQSql.id.eq(trafficCaptureId)
						.and(tcpSegQSql.sourcePort.loe(maxPort))
						.and(tcpSegQSql.sourcePort.lt(tcpSegQSql.destPort)))
				.list(new QTuple(tcpSegQSql.sourcePort.as(port),
						pcapPacketQSql.originalLength.as(length)));

		ListSubQuery<Tuple> subTcpSegIpv6Out = new SQLSubQuery()
				.from(pcapPacketQSql)
				.innerJoin(ethFrameQSql)
				.on(ethFrameQSql.pcapPacket.eq(pcapPacketQSql.id))
				.innerJoin(ipv6PacketQSql)
				.on(ipv6PacketQSql.ethernetFrame.eq(ethFrameQSql.id))
				.innerJoin(tcpSegQSql)
				.on(tcpSegQSql.ipv6packet.eq(ipv6PacketQSql.id))
				.innerJoin(procResTcpSegQSql)
				.on(procResTcpSegQSql.tcpSegmId.eq(tcpSegQSql.id))
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(procResTcpSegQSql.droidProcResId))
				.innerJoin(trafficCapQSql)
				.on(trafficCapQSql.processingResult.eq(procResQSql.id))
				.where(trafficCapQSql.id.eq(trafficCaptureId)
						.and(tcpSegQSql.destPort.loe(maxPort))
						.and(tcpSegQSql.destPort.loe(tcpSegQSql.sourcePort)))
				.list(new QTuple(tcpSegQSql.destPort.as(port),
						pcapPacketQSql.originalLength.as(length)));

		PathBuilder<Tuple> sub = new PathBuilder<Tuple>(Tuple.class, "subQuery");

		@SuppressWarnings("unchecked")
		List<Object[]> trafficPortCounts = sqlQuery
				.unionAll(sub, subTcpSegIn, subTcpSegOut, subTcpSegIpv6In,
						subTcpSegIpv6Out)
				.groupBy(sub.get(port))
				.list(sub.get(length).sum(), sub.get(length).count(),
						sub.get(port));

		if (trafficPortCounts == null) {
			return null;
		} else {
			List<TrafficAmountInfo> trafficPortCountsList = new ArrayList<TrafficAmountInfo>();
			for (Object[] trafficPortCount : trafficPortCounts) {
				trafficPortCountsList.add(new TrafficAmountInfo(
						((BigDecimal) trafficPortCount[0]).longValue(),
						((BigInteger) trafficPortCount[1]).longValue(),
						((Integer) trafficPortCount[2]).toString()));

			}
			return trafficPortCountsList;
		}
	}

	private List<TrafficAmountInfo> getTrafficAmountForUdpPorts(
			final Long trafficCaptureId, final int maxPort) {
		JPASQLQuery sqlQuery = new JPASQLQuery(this.entityManager,
				new MySQLTemplates());

		NumberPath<Integer> port = new NumberPath<Integer>(Integer.class,
				"port");
		NumberPath<Long> length = new NumberPath<Long>(Long.class, "length");

		ListSubQuery<Tuple> subUdpPktIn = new SQLSubQuery()
				.from(pcapPacketQSql)
				.innerJoin(ethFrameQSql)
				.on(ethFrameQSql.pcapPacket.eq(pcapPacketQSql.id))
				.innerJoin(ipv4PacketQSql)
				.on(ipv4PacketQSql.ethernetFrame.eq(ethFrameQSql.id))
				.innerJoin(udpPacketQSql)
				.on(udpPacketQSql.ipv4packet.eq(ipv4PacketQSql.id))
				.innerJoin(procResUdpPktQSql)
				.on(procResUdpPktQSql.udpPacketId.eq(udpPacketQSql.id))
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(procResUdpPktQSql.droidProcResId))
				.innerJoin(trafficCapQSql)
				.on(trafficCapQSql.processingResult.eq(procResQSql.id))
				.where(trafficCapQSql.id
						.eq(trafficCaptureId)
						.and(udpPacketQSql.sourcePort.loe(maxPort))
						.and(udpPacketQSql.sourcePort
								.lt(udpPacketQSql.destPort)))
				.list(new QTuple(udpPacketQSql.sourcePort.as(port),
						pcapPacketQSql.originalLength.as(length)));

		ListSubQuery<Tuple> subUdpPktOut = new SQLSubQuery()
				.from(pcapPacketQSql)
				.innerJoin(ethFrameQSql)
				.on(ethFrameQSql.pcapPacket.eq(pcapPacketQSql.id))
				.innerJoin(ipv4PacketQSql)
				.on(ipv4PacketQSql.ethernetFrame.eq(ethFrameQSql.id))
				.innerJoin(udpPacketQSql)
				.on(udpPacketQSql.ipv4packet.eq(ipv4PacketQSql.id))
				.innerJoin(procResUdpPktQSql)
				.on(procResUdpPktQSql.udpPacketId.eq(udpPacketQSql.id))
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(procResUdpPktQSql.droidProcResId))
				.innerJoin(trafficCapQSql)
				.on(trafficCapQSql.processingResult.eq(procResQSql.id))
				.where(trafficCapQSql.id
						.eq(trafficCaptureId)
						.and(udpPacketQSql.destPort.loe(maxPort))
						.and(udpPacketQSql.destPort
								.loe(udpPacketQSql.sourcePort)))
				.list(new QTuple(udpPacketQSql.destPort.as(port),
						pcapPacketQSql.originalLength.as(length)));

		ListSubQuery<Tuple> subUdpPktIpv6In = new SQLSubQuery()
				.from(pcapPacketQSql)
				.innerJoin(ethFrameQSql)
				.on(ethFrameQSql.pcapPacket.eq(pcapPacketQSql.id))
				.innerJoin(ipv6PacketQSql)
				.on(ipv6PacketQSql.ethernetFrame.eq(ethFrameQSql.id))
				.innerJoin(udpPacketQSql)
				.on(udpPacketQSql.ipv6packet.eq(ipv6PacketQSql.id))
				.innerJoin(procResUdpPktQSql)
				.on(procResUdpPktQSql.udpPacketId.eq(udpPacketQSql.id))
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(procResUdpPktQSql.droidProcResId))
				.innerJoin(trafficCapQSql)
				.on(trafficCapQSql.processingResult.eq(procResQSql.id))
				.where(trafficCapQSql.id
						.eq(trafficCaptureId)
						.and(udpPacketQSql.sourcePort.loe(maxPort))
						.and(udpPacketQSql.sourcePort
								.lt(udpPacketQSql.destPort)))
				.list(new QTuple(udpPacketQSql.sourcePort.as(port),
						pcapPacketQSql.originalLength.as(length)));

		ListSubQuery<Tuple> subUdpPktIpv6Out = new SQLSubQuery()
				.from(pcapPacketQSql)
				.innerJoin(ethFrameQSql)
				.on(ethFrameQSql.pcapPacket.eq(pcapPacketQSql.id))
				.innerJoin(ipv6PacketQSql)
				.on(ipv6PacketQSql.ethernetFrame.eq(ethFrameQSql.id))
				.innerJoin(udpPacketQSql)
				.on(udpPacketQSql.ipv6packet.eq(ipv6PacketQSql.id))
				.innerJoin(procResUdpPktQSql)
				.on(procResUdpPktQSql.udpPacketId.eq(udpPacketQSql.id))
				.innerJoin(procResQSql)
				.on(procResQSql.id.eq(procResUdpPktQSql.droidProcResId))
				.innerJoin(trafficCapQSql)
				.on(trafficCapQSql.processingResult.eq(procResQSql.id))
				.where(trafficCapQSql.id
						.eq(trafficCaptureId)
						.and(udpPacketQSql.destPort.loe(maxPort))
						.and(udpPacketQSql.destPort
								.loe(udpPacketQSql.sourcePort)))
				.list(new QTuple(udpPacketQSql.destPort.as(port),
						pcapPacketQSql.originalLength.as(length)));

		PathBuilder<Tuple> sub = new PathBuilder<Tuple>(Tuple.class, "subQuery");

		@SuppressWarnings("unchecked")
		List<Object[]> trafficPortCounts = sqlQuery
				.unionAll(sub, subUdpPktIn, subUdpPktOut, subUdpPktIpv6In,
						subUdpPktIpv6Out)
				.groupBy(sub.get(port))
				.list(sub.get(length).sum(), sub.get(length).count(),
						sub.get(port));

		if (trafficPortCounts == null) {
			return null;
		} else {
			List<TrafficAmountInfo> trafficPortCountsList = new ArrayList<TrafficAmountInfo>();
			for (Object[] trafficPortCount : trafficPortCounts) {
				trafficPortCountsList.add(new TrafficAmountInfo(
						((BigDecimal) trafficPortCount[0]).longValue(),
						((BigInteger) trafficPortCount[1]).longValue(),
						((Integer) trafficPortCount[2]).toString()));

			}
			return trafficPortCountsList;
		}
	}

	@Override
	public List<TrafficAmountInfo> getTrafficAmountForWellKnownTcpPorts(
			final Long trafficCaptureId) {
		return getTrafficAmountForTcpPorts(trafficCaptureId,
				WELL_KNOWN_PORTS_MAX);
	}

	@Override
	public List<TrafficAmountInfo> getTrafficAmountForWellKnownUdpPorts(
			final Long trafficCaptureId) {
		return getTrafficAmountForUdpPorts(trafficCaptureId,
				WELL_KNOWN_PORTS_MAX);
	}

	public Long findTotalTrafficLength(final Long trafficCaptureId) {

		return from(trafficCapQ).join(trafficCapQ.processingResult, procResQ)
				.join(procResQ.pcapPackets, pcapPacketQ)
				.where(trafficCapQ.id.eq(trafficCaptureId))
				.singleResult(pcapPacketQ.originalLength.sum());
	}

	@Override
	public List<TrafficAmountInfo> getTrafficAmountForWellKnownPorts(
			final Long trafficCaptureId) {
		return getTrafficAmountForPorts(trafficCaptureId, WELL_KNOWN_PORTS_MAX);
	}

	@Override
	public List<TrafficAmountInfo> getTrafficAmountForRegisteredPorts(
			final Long trafficCaptureId) {
		return getTrafficAmountForPorts(trafficCaptureId, REGISTERED_PORTS_MAX);
	}

}
