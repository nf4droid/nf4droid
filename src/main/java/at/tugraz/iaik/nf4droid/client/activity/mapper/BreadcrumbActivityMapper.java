package at.tugraz.iaik.nf4droid.client.activity.mapper;

import at.tugraz.iaik.nf4droid.client.activity.BreadcrumbActivity;
import at.tugraz.iaik.nf4droid.client.general.factory.ClientFactory;
import at.tugraz.iaik.nf4droid.client.view.breadcrumb.BreadcrumbViewImpl;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class BreadcrumbActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;

	public BreadcrumbActivityMapper(final ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		return new BreadcrumbActivity(BreadcrumbViewImpl.getInstance(),clientFactory, place);
	}

}
