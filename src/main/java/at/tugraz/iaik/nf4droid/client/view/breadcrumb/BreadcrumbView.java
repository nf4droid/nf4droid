package at.tugraz.iaik.nf4droid.client.view.breadcrumb;

import com.google.gwt.user.client.ui.IsWidget;

public interface BreadcrumbView extends IsWidget {

	public void setHeader(final String title, final String subTitle);

	public void addLinkEntry(final String text, final String targetHistoryToken);

	public void addTextEntry(final String text);

	public void clearBreadcrumb();

}
