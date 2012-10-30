package at.tugraz.iaik.nf4droid.server.domain.service;

import java.util.List;

import at.tugraz.iaik.nf4droid.shared.dto.BarChartExposeEntry;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficAmountInfo;

public interface DomainDashboardDataService {

	/**
	 * Get list of traffic amount and number of packets for TCP and UDP
	 * well-known ports.
	 * 
	 * @link https://en.wikipedia.org/wiki/Well_known_port#Well-known_ports
	 * 
	 */
	public List<TrafficAmountInfo> getTrafficAmountForWellKnownPorts(
			final Long trafficCaptureId);

	/**
	 * Get list of traffic amount and number of packets for TCP and UDP
	 * well-known and registered ports.
	 * 
	 * @link https://en.wikipedia.org/wiki/Registered_port
	 * 
	 */
	public List<TrafficAmountInfo> getTrafficAmountForRegisteredPorts(
			final Long trafficCaptureId);

	/**
	 * Get traffic amount and number of packets for IPv4 packets.
	 */
	public TrafficAmountInfo getIpv4TrafficAmountInfo(
			final Long trafficCaptureId);

	/**
	 * Get traffic amount and number of packets for IPv6 packets.
	 */
	public TrafficAmountInfo getIpv6TrafficAmountInfo(
			final Long trafficCaptureId);

	/**
	 * Get traffic amount and number of packets for ARP packets.
	 */
	public TrafficAmountInfo getArpTrafficAmountInfo(final Long trafficCaptureId);

	public TrafficAmountInfo getTcpIpv4TrafficAmountInfo(
			final Long trafficCaptureId);

	public TrafficAmountInfo getTcpIpv6TrafficAmountInfo(
			final Long trafficCaptureId);

	public TrafficAmountInfo getUdpIpv4TrafficAmountInfo(
			final Long trafficCaptureId);

	public TrafficAmountInfo getUdpIpv6TrafficAmountInfo(
			final Long trafficCaptureId);

	public List<TrafficAmountInfo> getTrafficAmountForWellKnownTcpPorts(
			Long trafficCaptureId);

	public List<TrafficAmountInfo> getTrafficAmountForWellKnownUdpPorts(
			Long trafficCaptureId);
	
	public Long findTotalTrafficLength(final Long trafficCaptureId);

	public List<BarChartExposeEntry> findDataExposureAmount(final Long trafficCaptureId);
	
	public Long coundDataExposures(final Long trafficCaptureId);
}
