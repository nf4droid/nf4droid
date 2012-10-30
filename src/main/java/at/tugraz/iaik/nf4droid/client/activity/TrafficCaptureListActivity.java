package at.tugraz.iaik.nf4droid.client.activity;

import java.util.List;

import at.tugraz.iaik.nf4droid.client.general.factory.ClientFactory;
import at.tugraz.iaik.nf4droid.client.place.CaptureDashboardPlace;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.TrafficCaptureProxy;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.request.DetailTrafficCaptureRequest;
import at.tugraz.iaik.nf4droid.client.view.common.list.ListAsyncDataProvider;
import at.tugraz.iaik.nf4droid.client.view.common.list.view.ListView;

import com.google.gwt.view.client.Range;

public class TrafficCaptureListActivity extends
		AbstractListActivity<TrafficCaptureProxy> {

	private Long appVersionId;

	public TrafficCaptureListActivity(final ListView<TrafficCaptureProxy> view,
			final ClientFactory clientFactory, final Long appVersionId) {
		super(view, clientFactory);
		this.appVersionId = appVersionId;
	}

	@Override
	public void initDataProvider() {
		dataProvider = new ListAsyncDataProvider<TrafficCaptureProxy>() {

			@Override
			public void retrieveData(Range range, String searchString,
					List<String> orderByProperties,
					List<Boolean> orderByValues, DataReceiver receiver) {
				DetailTrafficCaptureRequest trafficCaptureRequest = clientFactory
						.getRequestFactory().detailTrafficCaptureRequest();

				trafficCaptureRequest
						.findTrafficCapturesForAppVersionByDateAddedOrDescriptionLikeLimitOrderBy(
								appVersionId, searchString, range.getStart(),
								range.getLength(), orderByProperties,
								orderByValues).fire(receiver);
			}

			@Override
			public void countData(String searchString, CountReceiver receiver) {
				DetailTrafficCaptureRequest trafficCaptureRequest = clientFactory
						.getRequestFactory().detailTrafficCaptureRequest();

				trafficCaptureRequest
						.countTrafficCapturesForAppVersionByDateAddedOrDescriptionLike(
								appVersionId, searchString).fire(receiver);
			}
		};
	}

	@Override
	protected void onListEntrySelected(TrafficCaptureProxy proxy) {
		goTo(new CaptureDashboardPlace(proxy.getId()));
	}

}
