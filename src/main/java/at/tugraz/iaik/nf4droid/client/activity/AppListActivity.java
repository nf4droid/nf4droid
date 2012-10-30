package at.tugraz.iaik.nf4droid.client.activity;

import java.util.List;

import at.tugraz.iaik.nf4droid.client.general.factory.ClientFactory;
import at.tugraz.iaik.nf4droid.client.place.AppVersionListPlace;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.AppProxy;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.request.DetailAppRequest;
import at.tugraz.iaik.nf4droid.client.view.common.list.ListAsyncDataProvider;
import at.tugraz.iaik.nf4droid.client.view.common.list.view.ListView;

import com.google.gwt.view.client.Range;

public class AppListActivity extends AbstractListActivity<AppProxy> {

	public AppListActivity(ListView<AppProxy> view, ClientFactory clientFactory) {
		super(view, clientFactory);
	}

	@Override
	public void initDataProvider() {
		dataProvider = new ListAsyncDataProvider<AppProxy>() {

			@Override
			public void retrieveData(Range range, String searchString,
					List<String> orderByProperties,
					List<Boolean> orderByValues, DataReceiver receiver) {
				DetailAppRequest appRequest = clientFactory.getRequestFactory()
						.detailAppRequest();
				appRequest.findAppsByAppNameOrAppPackageLikeLimitOrderBy(
						searchString, range.getStart(), range.getLength(),
						orderByProperties, orderByValues).fire(receiver);
			}

			@Override
			public void countData(String searchString, CountReceiver receiver) {
				DetailAppRequest appRequest = clientFactory.getRequestFactory()
						.detailAppRequest();
				appRequest.countAppsByAppNameOrAppPackageLike(searchString)
						.fire(receiver);
			}
		};
	}

	@Override
	protected void onListEntrySelected(AppProxy proxy) {
		goTo(new AppVersionListPlace(proxy.getId()));
	}
}
