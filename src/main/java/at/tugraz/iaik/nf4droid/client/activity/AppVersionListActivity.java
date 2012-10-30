package at.tugraz.iaik.nf4droid.client.activity;

import java.util.List;

import at.tugraz.iaik.nf4droid.client.general.factory.ClientFactory;
import at.tugraz.iaik.nf4droid.client.place.TrafficCaptureListPlace;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.AppVersionProxy;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.request.DetailAppVersionRequest;
import at.tugraz.iaik.nf4droid.client.view.common.list.ListAsyncDataProvider;
import at.tugraz.iaik.nf4droid.client.view.common.list.view.ListView;

import com.google.gwt.view.client.Range;

public class AppVersionListActivity extends AbstractListActivity<AppVersionProxy> {

	private Long appId;
	
	public AppVersionListActivity(ListView<AppVersionProxy> view,
			ClientFactory clientFactory, Long appId) {
		super(view, clientFactory);
		this.appId = appId;
	}

	@Override
	public void initDataProvider() {
		dataProvider = new ListAsyncDataProvider<AppVersionProxy>() {
			
			@Override
			public void retrieveData(Range range, String searchString,
					List<String> orderByProperties, List<Boolean> orderByValues,
					DataReceiver receiver) {
				DetailAppVersionRequest versionReq = clientFactory
						.getRequestFactory().detailAppVersionRequest();

				versionReq
						.findAppVersionsForAppByVersionCodeOrVerisonNameLikeLimitOrderBy(appId,
								searchString, range.getStart(), range.getLength(),
								orderByProperties, orderByValues).fire(receiver);
			}
			
			@Override
			public void countData(String searchString, CountReceiver receiver) {
				DetailAppVersionRequest versionReq = clientFactory
						.getRequestFactory().detailAppVersionRequest();

				versionReq.countAppVersionsForAppByVersionCodeOrVersionNameLike(appId,searchString).fire(receiver);
			}
		};
	}

	@Override
	protected void onListEntrySelected(AppVersionProxy proxy) {
		goTo(new TrafficCaptureListPlace(proxy.getId()));
	}

	
	
}
