package at.tugraz.iaik.nf4droid.client.view.httpreq.request;

import com.github.gwtbootstrap.client.ui.Well;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class HttpRequestViewImpl extends Composite implements HttpRequestView {

	private static final String LABEL_COLUMN_WIDTH = "115px";

	private FlexTable table;

	public HttpRequestViewImpl() {
		super();
		SimplePanel panel = new SimplePanel();

		table = new FlexTable();
		panel.add(table);

		initWidget(panel);
	}

	public void setData(final String localIp,
			final String remoteIp, final String localPort, final String remotePort,
			final String remoteCountry, final String remoteCity, final String method, final String version, final String url,
			final String headerFields, final String httpParameters) {
		int index = 0;
		Label firstLabel = new Label("Source IP:");
		firstLabel.setWidth(LABEL_COLUMN_WIDTH);
		table.setWidget(index, 0, firstLabel);
		table.setWidget(index++, 1, new Label(localIp));
		table.setWidget(index, 0, new Label("Source Port:"));
		table.setWidget(index++, 1, new Label(localPort));
		table.setWidget(index, 0, new Label("Dest. IP:"));
		table.setWidget(index++, 1, new Label(remoteIp));
		table.setWidget(index, 0, new Label("Dest. Port:"));
		table.setWidget(index++, 1, new Label(remotePort));
		table.setWidget(index, 0, new Label("Dest. Country:"));
		table.setWidget(index++, 1, new Label(remoteCountry));
		table.setWidget(index, 0, new Label("Dest. City:"));
		table.setWidget(index++, 1, new Label(remoteCity));
		table.setWidget(index, 0, new Label("HTTP method"));
		table.setWidget(index++, 1, new Label(method));
		table.setWidget(index, 0, new Label("HTTP version"));
		table.setWidget(index++, 1, new Label(version));

		table.setWidget(index, 0, new Label("Full URL:"));
		table.setWidget(index++, 1, createBox(url));

		table.setWidget(index, 0, new Label("Http Parameters:"));
		table.setWidget(index++, 1, createBox(httpParameters));

		table.setWidget(index, 0, new Label("Header Fields:"));
		table.setWidget(index++, 1, createBox(headerFields));

	}
	
	private Widget createBox(final String text) {
		final HTML textBox = new HTML(text);
		textBox.setStylePrimaryName("textArea");
		final Well wellBox = new Well();
		wellBox.add(textBox);
		return wellBox;
	}
}
