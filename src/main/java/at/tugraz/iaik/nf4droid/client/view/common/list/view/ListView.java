package at.tugraz.iaik.nf4droid.client.view.common.list.view;

import at.tugraz.iaik.nf4droid.client.view.common.event.HasColumnSortList;
import at.tugraz.iaik.nf4droid.client.view.common.event.HasSearchString;

import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent.HasSelectionChangedHandlers;
import com.google.web.bindery.requestfactory.shared.EntityProxy;

public interface ListView<T extends EntityProxy> extends IsWidget,
		HasColumnSortList, HasSearchString, HasSelectionChangedHandlers {

	public HasData<T> getTable();

	public HandlerRegistration addSubmitSearchClickHandler(ClickHandler handler);

	public HandlerRegistration addTypeSearchKeyUpHandler(KeyUpHandler handler);

	public HandlerRegistration addClearSearchClickHandler(ClickHandler handler);

	public HandlerRegistration addColumnSortEventHandler(
			ColumnSortEvent.Handler handler);

	public void goToFirstPage();

	public void clearSearch();
	
	public void showMessage(AlertType alertType, final String heading, final String message);
	public void hideMessage();
}
