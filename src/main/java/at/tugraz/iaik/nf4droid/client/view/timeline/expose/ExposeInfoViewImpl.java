package at.tugraz.iaik.nf4droid.client.view.timeline.expose;

import at.tugraz.iaik.nf4droid.shared.constant.ExposeObscuring;
import at.tugraz.iaik.nf4droid.shared.constant.ExposePoint;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;

import com.github.gwtbootstrap.client.ui.Well;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class ExposeInfoViewImpl extends Composite implements ExposeInfoView {

	private static final String LABEL_COLUMN_WIDTH = "115px";

	private FlexTable table;

	public ExposeInfoViewImpl() {
		super();
		SimplePanel panel = new SimplePanel();

		table = new FlexTable();
		panel.add(table);

		initWidget(panel);
	}

	public void setData(ExposeType exposeType, ExposeObscuring exposeObscuring,
			ExposePoint exposePoint, String exposedValue, String localIp,
			String remoteIp, String localPort, String remotePort,
			String remoteCountry, String remoteCity, String url,
			String headerFields, String httpParameters) {
		int index = 0;
		Label firstLabel = new Label("Expose value:");
		firstLabel.setWidth(LABEL_COLUMN_WIDTH);
		table.setWidget(index, 0, firstLabel);
		table.setWidget(index++, 1, new Label(exposedValue));
		table.setWidget(index, 0, new Label("Expose type:"));
		table.setWidget(index++, 1, new Label(exposeType.getDescription()));
		table.setWidget(index, 0, new Label("Expose obscuring:"));
		table.setWidget(index++, 1, new Label(exposeObscuring.getDescription()));
		table.setWidget(index, 0, new Label("Expose point:"));
		table.setWidget(index++, 1, new Label(exposePoint.getDescription()));
		table.setWidget(index, 0, new Label("Source IP:"));
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

		table.setWidget(index, 0, new Label("Full URL:"));
		if (exposePoint.equals(ExposePoint.HTTP_PARAMETER)) {
			url = highlightExposedValue(url, exposedValue);
		}
		table.setWidget(index++, 1, createBox(url));

		table.setWidget(index, 0, new Label("Http Parameters:"));
		if (exposePoint.equals(ExposePoint.HTTP_PARAMETER)) {
			httpParameters = highlightExposedValue(httpParameters, exposedValue);
		}
		table.setWidget(index++, 1, createBox(httpParameters));

		table.setWidget(index, 0, new Label("Header Fields:"));
		if (exposePoint.equals(ExposePoint.HEADER_FIELD)) {
			headerFields = highlightExposedValue(headerFields, exposedValue);
		}
		table.setWidget(index++, 1, createBox(headerFields));

	}

	private String highlightExposedValue(String text, final String highlight) {
		if (text != null && highlight != null
				&& text.toLowerCase().contains(highlight.toLowerCase())) {
			final RegExp pattern = RegExp
					.compile(quoteString(highlight), "gim");
			text = pattern.replace(text,
					"<span class=\"textHighlight\">$&</span>");

		}
		return text;
	}

	private static String quoteString(final String input) {
		final RegExp pattern = RegExp.compile("[.*+?|()\\[\\]{}\\\\]", "g");
		return pattern.replace(input, "\\$&");
	}

	private Widget createBox(final String text) {
		final HTML textBox = new HTML(text);
		textBox.setStylePrimaryName("textArea");
		final Well wellBox = new Well();
		wellBox.add(textBox);
		return wellBox;
	}
}
