package at.tugraz.iaik.nf4droid.client.view.app;

import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.AppProxy;
import at.tugraz.iaik.nf4droid.client.view.common.event.GetValue;
import at.tugraz.iaik.nf4droid.client.view.common.list.view.AbstractListView;
import at.tugraz.iaik.nf4droid.shared.dto.ClassVariableMapping;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.client.ui.Widget;

public class AppListViewImpl extends AbstractListView<AppProxy> implements AppListView  {
	
	private static class SingletonHolder {
		private static AppListViewImpl instance = new AppListViewImpl();
	}

	public static AppListViewImpl getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	protected Widget createAndBindUi() {
		return super.getUiBinder().createAndBindUi(this);
	}

	@Override
	protected void initTableColumns() {
		
		super.addColumn("App name", new TextCell(), new GetValue<AppProxy, String>() {
			public String getValue(AppProxy object) {
				return object.getAppName();
			}
		}, ClassVariableMapping.APP_NAME, 50.0);
		
		super.addColumn("App package", new TextCell(), new GetValue<AppProxy, String>() {
			@Override
			public String getValue(AppProxy object) {
				return object.getAppPackage();
			}
		}, ClassVariableMapping.APP_PACKAGE, 50.0);
	}

}
