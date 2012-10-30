package at.tugraz.iaik.nf4droid.client.view.common.list;

import at.tugraz.iaik.nf4droid.client.view.common.event.GetValue;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.user.cellview.client.Column;

public class SortableColumn<T, C> extends Column<T, C> {
	
	private GetValue<T, C> getter;
	private String propetyName;

	public SortableColumn(Cell<C> cell) {
		super(cell);
	}
	
	public SortableColumn(Cell<C> cell, GetValue<T, C> getter,
			String propetyName) {
		super(cell);
		this.getter = getter;
		this.propetyName = propetyName;
	}

	@Override
	public C getValue(T object) {
		return getter.getValue(object);
	}
	

	public String getPropetyName() {
		return propetyName;
	}

}
