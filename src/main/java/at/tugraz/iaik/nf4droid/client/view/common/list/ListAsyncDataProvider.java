package at.tugraz.iaik.nf4droid.client.view.common.list;

import java.util.ArrayList;
import java.util.List;

import at.tugraz.iaik.nf4droid.client.view.common.CommonUiUtil;
import at.tugraz.iaik.nf4droid.client.view.common.list.view.ListView;

import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.ColumnSortList.ColumnSortInfo;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public abstract class ListAsyncDataProvider<T extends EntityProxy> extends
		AsyncDataProvider<T> {

	private ListView<T> view;

	public void setDataDisplay(HasData<T> display, ListView<T> view) {
		this.view = view;
		super.addDataDisplay(display);
	}
	
	public void removeDataDisplay(HasData<T> display, ListView<T> view) {
		this.view = null;
		super.removeDataDisplay(display);
	}

	/**
	 * Method for refreshing data if range changed, sort order changed, or
	 * search.
	 */
	@Override
	public void onRangeChanged(HasData<T> display) {

		List<String> orderByProperties = null;
		List<Boolean> orderByAscValues = null;
		String searchString = "";

		if (view != null) {
			if (view.getColumnSortList() != null) {
				ColumnSortList list = view.getColumnSortList();
				if (list != null) {
					final int size = list.size();
					orderByProperties = new ArrayList<String>(size);
					orderByAscValues = new ArrayList<Boolean>(size);

					Column<?, ?> col;
					ColumnSortInfo sortInfo;
					for (int i = 0; i < size; i++) {
						sortInfo = list.get(i);
						col = list.get(i).getColumn();
						if (col instanceof SortableColumn<?, ?>) {
							SortableColumn<?, ?> sortCol = (SortableColumn<?, ?>) col;
							orderByProperties.add(sortCol.getPropetyName());
							orderByAscValues.add(sortInfo.isAscending());
						}
					}
				}
			}
			if (view.getSearchString() != null) {
				searchString = view.getSearchString();
			}
		}

		retrieveData(display.getVisibleRange(), searchString,
				orderByProperties, orderByAscValues,
				new DataReceiver(display.getVisibleRange()));

		countData(searchString, new CountReceiver());
	}

	/**
	 * Retrieve data.
	 * 
	 * @param range
	 *            Range of data to display.
	 * @param searchString
	 *            Search string to take into account.
	 * @param orderByProperties
	 *            List of properties which result should be order by.
	 * @param orderByValues
	 *            List of values according to the properties which result should
	 *            be order by.
	 * @param receiver
	 *            {@link DataReceiver} for actually updating connected displays.
	 */
	public abstract void retrieveData(Range range, String searchString,
			List<String> orderByProperties, List<Boolean> orderByValues,
			DataReceiver receiver);

	/**
	 * Count data.
	 * 
	 * @param range
	 *            Range of data to display.
	 * @param searchString
	 *            Search string to take into account.
	 * @param receiver
	 *            {@link DataReceiver} for actually updating connected displays.
	 */
	public abstract void countData(String searchString, CountReceiver receiver);

	/**
	 * Receiver for updating the display data.
	 * 
	 */
	public class DataReceiver extends Receiver<List<T>> {

		private Range range;

		/**
		 * Constructor
		 * 
		 * @param range
		 *            Range to be displayed in table.
		 */
		public DataReceiver(Range range) {
			super();
			this.range = range;
		}

		@Override
		public void onSuccess(List<T> response) {
			updateRowData(range.getStart(), response);
		}
		
		@Override
		public void onFailure(ServerFailure error) {
			view.showMessage(AlertType.ERROR, "Error", "Couldn't load table data.");
			GWT.log("Couldn't load table data.");
			super.onFailure(error);
		}
	}

	/**
	 * Receiver for updating the display row count.
	 * 
	 */
	public class CountReceiver extends Receiver<Long> {

		@Override
		public void onSuccess(Long response) {
			int rowCount = CommonUiUtil.safeLongToInt(response);
			updateRowCount(rowCount, true);
		}

		@Override
		public void onFailure(ServerFailure error) {
			view.showMessage(AlertType.ERROR, "Error", "Couldn't load table data count.");
			GWT.log("Couldn't load table data count.");
			super.onFailure(error);
		}

		
	}

}
