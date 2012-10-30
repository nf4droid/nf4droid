package at.tugraz.iaik.nf4droid.client.view.breadcrumb;

import at.tugraz.iaik.nf4droid.client.view.common.bootstrap.Span;

import com.github.gwtbootstrap.client.ui.Icon;
import com.github.gwtbootstrap.client.ui.NavWidget;
import com.github.gwtbootstrap.client.ui.PageHeader;
import com.github.gwtbootstrap.client.ui.base.ListItem;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.github.gwtbootstrap.client.ui.incubator.Breadcrumbs;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class BreadcrumbViewImpl extends Composite implements BreadcrumbView {

	@UiField
	protected PageHeader header;
	
	@UiField
	protected Breadcrumbs breadcrumb;
	
	private static class SingletonHolder {
		private static BreadcrumbViewImpl instance = new BreadcrumbViewImpl();
	}

	public static BreadcrumbViewImpl getInstance() {
		return SingletonHolder.instance;
	}
	
	private static BreadcrumbViewImplUiBinder uiBinder = GWT
			.create(BreadcrumbViewImplUiBinder.class);

	interface BreadcrumbViewImplUiBinder extends
			UiBinder<Widget, BreadcrumbViewImpl> {
	}

	private BreadcrumbViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	public void setHeader(final String title, final String subTitle) {
		header.setText(title);
		header.setTitle(title);
		header.setSubtext(subTitle);
	}

	public void addLinkEntry(final String text, final String targetHistoryToken) {
		final NavWidget link = new NavWidget();
		link.setTargetHistoryToken(targetHistoryToken);
		link.setText(text);
		breadcrumb.add(link);	
		breadcrumb.add(new ListItem(new Icon(IconType.CHEVRON_RIGHT)));
	}
	
	public void clearBreadcrumb() {
		final int widgetCount = breadcrumb.getWidgetCount();
		
		if (widgetCount > 2) {
			for (int i = widgetCount-1; i > 1; i--) {
				breadcrumb.remove(i);
			}
		}
		//breadcrumb.clear();
	}

	@Override
	public void addTextEntry(String text) {
		breadcrumb.add(new ListItem(new Span(text)));
	}
}
