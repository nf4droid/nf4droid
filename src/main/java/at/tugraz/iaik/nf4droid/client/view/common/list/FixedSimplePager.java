package at.tugraz.iaik.nf4droid.client.view.common.list;

import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.Range;

public class FixedSimplePager extends SimplePager {

	public FixedSimplePager() {
		super();
	}

	public FixedSimplePager(TextLocation location, Resources resources,
			boolean showFastForwardButton, int fastForwardRows,
			boolean showLastPageButton) {
		super(location, resources, showFastForwardButton, fastForwardRows,
				showLastPageButton);
	}

	public FixedSimplePager(TextLocation location) {
		super(location);
	}

	@Override
	public void setPageStart(int index) {
		if (this.getDisplay() != null) {
			Range range = this.getDisplay().getVisibleRange();
			int pageSize = range.getLength();
			// if (isRangeLimited && display.isRowCountExact()) {
			// index = Math.min(index, display.getRowCount() - pageSize);
			// }
			index = Math.max(0, index);
			if (index != range.getStart()) {
				this.getDisplay().setVisibleRange(index, pageSize);
			}
		}
	}

}
