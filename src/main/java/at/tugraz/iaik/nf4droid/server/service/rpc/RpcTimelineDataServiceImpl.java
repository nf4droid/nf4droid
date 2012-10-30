package at.tugraz.iaik.nf4droid.server.service.rpc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import at.tugraz.iaik.nf4droid.client.service.rpc.RpcTimelineDataService;
import at.tugraz.iaik.nf4droid.server.domain.model.DataExpose;
import at.tugraz.iaik.nf4droid.server.domain.model.TrafficCapture;
import at.tugraz.iaik.nf4droid.server.domain.service.DomainTimelineDataService;
import at.tugraz.iaik.nf4droid.shared.dto.Criteria;
import at.tugraz.iaik.nf4droid.shared.dto.Expose;
import at.tugraz.iaik.nf4droid.shared.dto.TimeValuePair;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficTimeLineData;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficTimelineDataSet;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mysema.query.Tuple;

@Configurable
public class RpcTimelineDataServiceImpl extends RemoteServiceServlet implements
		RpcTimelineDataService {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger
			.getLogger(RpcTimelineDataServiceImpl.class);
	private static final int DEFAULT_NR_OF_DATA_POINTS = 200;

	@Autowired
	private DomainTimelineDataService domainDataService;

	public TimeValuePair getTrafficTimelineStartTimeAndInterval(
			Long trafficCaptureId) {
		LOGGER.info("Get traffic timeline start time and interval for capture: "+trafficCaptureId);
		
		final Tuple startEndTuple = domainDataService
				.getFirstAndLastTimeValueForTrafficTimeline(trafficCaptureId);

		final Long durationInMs = calculateDuration(
				startEndTuple.get(0, Instant.class),
				startEndTuple.get(1, Instant.class));
		return new TimeValuePair(startEndTuple.get(0, Instant.class).toDate(),
				durationInMs);
	}

	private Long calculateDuration(final Instant startTime,
			final Instant endTime) {
		return (new Interval(startTime, endTime)).toDurationMillis();
	}

	private int calculateDefaultInterval(final Long trafficCaptureId) {
		final Tuple startEndTuple = domainDataService
				.getFirstAndLastTimeValueForTrafficTimeline(trafficCaptureId);

		final Long durationInMs = calculateDuration(
				startEndTuple.get(0, Instant.class),
				startEndTuple.get(1, Instant.class));

		return (int) (durationInMs / DEFAULT_NR_OF_DATA_POINTS);
	}

	public TrafficTimeLineData getTotalTrafficTimelineData(
			final Long trafficCaptureId, int intervalInMs) {
		LOGGER.info("Get total traffic timeline data for capture: "+trafficCaptureId);
		
		if (intervalInMs < 1) {
			intervalInMs = calculateDefaultInterval(trafficCaptureId);
		}
		final List<TimeValuePair> totalTraffic = domainDataService
				.findTrafficTimelineData(trafficCaptureId, intervalInMs);

		if (totalTraffic != null && !totalTraffic.isEmpty()) {
			return new TrafficTimeLineData(totalTraffic, intervalInMs);
		} else {
			return null;
		}
	}

	
	public TrafficTimelineDataSet getTrafficTimelineHttpRequestDataset(
			final Long trafficCaptureId, int intervalInMs) {
		LOGGER.info("Get traffic timeline data for http requests and data exposure for capture: "+trafficCaptureId);
		
		if (intervalInMs < 1) {
			intervalInMs = calculateDefaultInterval(trafficCaptureId);
		}

		final List<TimeValuePair> httpReqTraffic = domainDataService
				.findTrafficTimelineDataForHttpRequests(trafficCaptureId,
						intervalInMs);

		if (httpReqTraffic == null || httpReqTraffic.isEmpty()) {
			return null;
		}

		final TrafficCapture trafficCapture = TrafficCapture
				.findTrafficCapture(trafficCaptureId);
		final List<DataExpose> dataExposes = trafficCapture
				.getProcessingResult().getDataExposes();

		// set the traffic amount of the data exposure according to the http request amount at the corresponding time
		// (required for correct placing of the markers in the graph of the ui)
		List<Expose> exposes = new ArrayList<Expose>();
		if (dataExposes != null) {

			Date time;
			for (DataExpose dataExpose : dataExposes) {
				time = new Date((long) Math.floor((double) dataExpose
						.getTimeInstant().getMillis() / (double) intervalInMs)
						* intervalInMs);
				for (TimeValuePair timeValuePair : httpReqTraffic) {
					if (time.equals(timeValuePair.getTime())) {
						dataExpose.setStartTotalTrafficAmount(timeValuePair
								.getValue());
					}
				}

				Expose ex = new Expose(time, dataExpose.getHttpRequest()
						.getId(), dataExpose.getExposeType(),
						dataExpose.getExposeObscuring(),
						dataExpose.getExposePoint(),
						dataExpose.getExposedData());
				ex.setStartTotalTrafficAmount(dataExpose
						.getStartTotalTrafficAmount());
				exposes.add(ex);
			}
		}

		return new TrafficTimelineDataSet(new TrafficTimeLineData(
				httpReqTraffic, intervalInMs), exposes);
	}

	public TrafficTimeLineData getTrafficTimelineDataForIpv4ByCriteria(
			final Long trafficCaptureId, Integer intervalInMs,
			final Criteria criterias) {
		LOGGER.info("Get traffic timeline data for ipv with custom criteria for capture: "+trafficCaptureId);
		
		if (intervalInMs < 1) {
			intervalInMs = calculateDefaultInterval(trafficCaptureId);
		}

		List<TimeValuePair> timeValuePairList = domainDataService
				.findTrafficTimelineDataForIpv4ByCriteria(trafficCaptureId,
						intervalInMs, criterias);
		if (timeValuePairList != null && !timeValuePairList.isEmpty()) {
			return new TrafficTimeLineData(timeValuePairList, intervalInMs);
		} else {
			return null;
		}
	}
}
