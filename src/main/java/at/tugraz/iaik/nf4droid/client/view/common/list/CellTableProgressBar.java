package at.tugraz.iaik.nf4droid.client.view.common.list;

import com.github.gwtbootstrap.client.ui.ProgressBar;
import com.github.gwtbootstrap.client.ui.base.DivWidget;

public class CellTableProgressBar extends DivWidget {

	public CellTableProgressBar() {
		super();
		final ProgressBar progressBar = new ProgressBar();
		progressBar.setType(ProgressBar.Style.ANIMATED);
		progressBar.setPercent(100);
		super.add(progressBar);
		super.setStylePrimaryName("progressWidget");
	}

}
