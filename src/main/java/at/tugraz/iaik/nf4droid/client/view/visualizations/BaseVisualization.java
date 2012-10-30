package at.tugraz.iaik.nf4droid.client.view.visualizations;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class BaseVisualization implements IsWidget {

	protected SimplePanel visualizationPanel;
	
	public BaseVisualization() {
		super();
		initVisualization();
	}
	
	private void initVisualization() {
		visualizationPanel = new SimplePanel();
	}

	public Widget asWidget() {
		return visualizationPanel;
	}
}
