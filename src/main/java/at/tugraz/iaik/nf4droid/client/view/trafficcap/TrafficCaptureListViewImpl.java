package at.tugraz.iaik.nf4droid.client.view.trafficcap;

import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.TrafficCaptureProxy;
import at.tugraz.iaik.nf4droid.client.view.common.CommonUiUtil;
import at.tugraz.iaik.nf4droid.client.view.common.event.GetValue;
import at.tugraz.iaik.nf4droid.client.view.common.list.view.AbstractListView;
import at.tugraz.iaik.nf4droid.shared.dto.ClassVariableMapping;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.client.ui.Widget;

public class TrafficCaptureListViewImpl extends
		AbstractListView<TrafficCaptureProxy> implements TrafficCaptureListView {

	private static class SingletonHolder {
		private static TrafficCaptureListViewImpl instance = new TrafficCaptureListViewImpl();
	}

	public static TrafficCaptureListViewImpl getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	protected Widget createAndBindUi() {
		return super.getUiBinder().createAndBindUi(this);
	}

	@Override
	protected void initTableColumns() {
		super.addColumn("Capture date", new TextCell(),
				new GetValue<TrafficCaptureProxy, String>() {
					public String getValue(TrafficCaptureProxy object) {
						return CommonUiUtil.DEFAULT_DATE_TIME_FORMAT.format(object.getDateAdded());
					}
				}, ClassVariableMapping.CAPTURE_DATE, 50.0);
		super.addColumn("Capture description", new TextCell(),
				new GetValue<TrafficCaptureProxy, String>() {
					public String getValue(TrafficCaptureProxy object) {
						return object.getDescription();
					}
				}, ClassVariableMapping.CAPTURE_DESCRIPTION, 50.0);
	}

}
