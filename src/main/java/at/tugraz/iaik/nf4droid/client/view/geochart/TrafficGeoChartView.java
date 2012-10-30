package at.tugraz.iaik.nf4droid.client.view.geochart;

import java.util.List;

import at.tugraz.iaik.nf4droid.shared.dto.TrafficAmountInfo;

import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.user.client.ui.IsWidget;

public interface TrafficGeoChartView extends IsWidget {

	public void drawGeoChart(final List<TrafficAmountInfo> countryTrafficAmounts);

	public void setTotalTrafficInfo(final Long trafficAmount);

	public void showWarningUnknownCountries(final Long trafficAmountUnknown,
			final double percentageUnknown);

	public void hideWarningUnknownCountries();

	public void showMessage(AlertType alertType, final String heading,
			final String message);
}
