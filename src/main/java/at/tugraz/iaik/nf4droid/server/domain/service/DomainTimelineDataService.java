package at.tugraz.iaik.nf4droid.server.domain.service;

import java.util.List;

import at.tugraz.iaik.nf4droid.shared.dto.Criteria;
import at.tugraz.iaik.nf4droid.shared.dto.TimeValuePair;

import com.mysema.query.Tuple;

public interface DomainTimelineDataService {

	public Tuple getFirstAndLastTimeValueForTrafficTimeline(
			Long trafficCaptureId);

	public List<TimeValuePair> findTrafficTimelineData(Long trafficCaptureId,
			Integer intervalInMs);

	public List<TimeValuePair> findTrafficTimelineDataForHttpRequests(
			Long trafficCaptureId, Integer intervalInMs);

	public List<TimeValuePair> findTrafficTimelineDataForIpv4ByCriteria(
			final Long trafficCaptureId, final Integer intervalInMs,
			final Criteria criterias);

}
