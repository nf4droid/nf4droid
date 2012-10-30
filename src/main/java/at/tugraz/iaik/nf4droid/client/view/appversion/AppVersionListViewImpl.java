package at.tugraz.iaik.nf4droid.client.view.appversion;

import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.AppVersionProxy;
import at.tugraz.iaik.nf4droid.client.view.common.event.GetValue;
import at.tugraz.iaik.nf4droid.client.view.common.list.view.AbstractListView;
import at.tugraz.iaik.nf4droid.shared.dto.ClassVariableMapping;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.client.ui.Widget;

public class AppVersionListViewImpl extends AbstractListView<AppVersionProxy>
		implements AppVersionListView {

	private static class SingletonHolder {
		private static AppVersionListViewImpl instance = new AppVersionListViewImpl();
	}

	public static AppVersionListViewImpl getInstance() {
		return SingletonHolder.instance;
	}

	@Override
	protected Widget createAndBindUi() {
		return super.getUiBinder().createAndBindUi(this);
	}

	@Override
	protected void initTableColumns() {
		super.addColumn("App version code", new TextCell(),
				new GetValue<AppVersionProxy, String>() {
					public String getValue(AppVersionProxy object) {
						return object.getVersionCode().toString();
					}
				}, ClassVariableMapping.APP_VERSION_CODE, 50.0);
		super.addColumn("App version name", new TextCell(),
				new GetValue<AppVersionProxy, String>() {
					public String getValue(AppVersionProxy object) {
						return object.getVersionName().toString();
					}
				}, ClassVariableMapping.APP_VERSION_NAME, 50.0);

	}

}
