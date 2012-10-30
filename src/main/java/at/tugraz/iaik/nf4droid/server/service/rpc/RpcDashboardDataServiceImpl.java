package at.tugraz.iaik.nf4droid.server.service.rpc;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import at.tugraz.iaik.nf4droid.client.service.rpc.RpcDashboardDataService;
import at.tugraz.iaik.nf4droid.server.common.utils.jnr.Service;
import at.tugraz.iaik.nf4droid.server.domain.service.DomainDashboardDataService;
import at.tugraz.iaik.nf4droid.shared.dto.BarChartExposeEntry;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficAmountInfo;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Configurable
public class RpcDashboardDataServiceImpl extends RemoteServiceServlet
		implements RpcDashboardDataService {

	private static final Logger LOGGER = Logger
			.getLogger(RpcDashboardDataServiceImpl.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	private DomainDashboardDataService domainDataService;
	
	public List<BarChartExposeEntry> findDataExposureAmount(final Long trafficCaptureId) {
		return domainDataService.findDataExposureAmount(trafficCaptureId);
	}

	public List<TrafficAmountInfo> getTrafficRatioForNetworkLayer(
			final Long captureId) {
		
		LOGGER.info("Get traffic-ratio for network layer for capture: "+captureId);
		
		final List<TrafficAmountInfo> amounts = new ArrayList<TrafficAmountInfo>();

		TrafficAmountInfo tmpAmount = domainDataService
				.getIpv4TrafficAmountInfo(captureId);
		tmpAmount.setDescription("IPv4");
		if ((tmpAmount != null) && (tmpAmount.getTrafficAmount() != null)
				&& (tmpAmount.getPacketCount() > 0)) {
			amounts.add(tmpAmount);
		}

		tmpAmount = domainDataService.getIpv6TrafficAmountInfo(captureId);
		tmpAmount.setDescription("IPv6");
		if ((tmpAmount != null) && (tmpAmount.getTrafficAmount() != null)
				&& (tmpAmount.getPacketCount() > 0)) {
			amounts.add(tmpAmount);
		}

		tmpAmount = domainDataService.getArpTrafficAmountInfo(captureId);
		tmpAmount.setDescription("ARP");
		if ((tmpAmount != null) && (tmpAmount.getTrafficAmount() != null)
				&& (tmpAmount.getPacketCount() > 0)) {
			amounts.add(tmpAmount);
		}

		return amounts;
	}

	public List<TrafficAmountInfo> getTrafficRatioForTransportLayer(
			final Long captureId) {
		LOGGER.info("Get traffic-ratio for transport layer for capture: "+captureId);
		
		final List<TrafficAmountInfo> amounts = new ArrayList<TrafficAmountInfo>();

		TrafficAmountInfo tmpAmount = domainDataService
				.getTcpIpv4TrafficAmountInfo(captureId);
		tmpAmount.setDescription("TCP over IPv4");
		if ((tmpAmount != null) && (tmpAmount.getTrafficAmount() != null)
				&& (tmpAmount.getPacketCount() > 0)) {
			amounts.add(tmpAmount);
		}

		tmpAmount = domainDataService.getTcpIpv6TrafficAmountInfo(captureId);
		tmpAmount.setDescription("TCP over IPv6");
		if ((tmpAmount != null) && (tmpAmount.getTrafficAmount() != null)
				&& (tmpAmount.getPacketCount() > 0)) {
			amounts.add(tmpAmount);
		}

		tmpAmount = domainDataService.getUdpIpv4TrafficAmountInfo(captureId);
		tmpAmount.setDescription("UDP over IPv4");
		if ((tmpAmount != null) && (tmpAmount.getTrafficAmount() != null)
				&& (tmpAmount.getPacketCount() > 0)) {
			amounts.add(tmpAmount);
		}

		tmpAmount = domainDataService.getUdpIpv6TrafficAmountInfo(captureId);
		tmpAmount.setDescription("UDP over IPv6");
		if ((tmpAmount != null) && (tmpAmount.getTrafficAmount() != null)
				&& (tmpAmount.getPacketCount() > 0)) {
			amounts.add(tmpAmount);
		}

		return amounts;
	}

	
	public List<TrafficAmountInfo> getTrafficRatioForPorts(final Long captureId) {
		LOGGER.info("Get traffic-ratio for tcp and udp ports for capture: "+captureId);
		
		final List<TrafficAmountInfo> amountsTcp = domainDataService
				.getTrafficAmountForWellKnownTcpPorts(captureId);
		for (TrafficAmountInfo trafficAmountInfo : amountsTcp) {
			trafficAmountInfo.setDescription(trafficAmountInfo.getDescription()
					+ "-"
					+ (Service.getServiceByPort(Integer
							.parseInt(trafficAmountInfo.getDescription()),
							"tcp")).getName());
		}

		final List<TrafficAmountInfo> amountsUdp = domainDataService
				.getTrafficAmountForWellKnownUdpPorts(captureId);
		for (TrafficAmountInfo trafficAmountInfo : amountsUdp) {
			trafficAmountInfo.setDescription(trafficAmountInfo.getDescription()
					+ "-"
					+ (Service.getServiceByPort(Integer
							.parseInt(trafficAmountInfo.getDescription()),
							"udp")).getName());
		}

		amountsTcp.addAll(amountsUdp);
		return amountsTcp;
	}

	
	public Long getTotalTrafficLength(final Long trafficCaptureId) {
		LOGGER.info("Get total traffic length for capture: "+trafficCaptureId);
		return domainDataService.findTotalTrafficLength(trafficCaptureId);
	}
	
	public Long coundDataExposures(final Long trafficCaptureId) {
		LOGGER.info("Count all data exposures for capture: "+trafficCaptureId);
		return domainDataService.coundDataExposures(trafficCaptureId);
	}
}
