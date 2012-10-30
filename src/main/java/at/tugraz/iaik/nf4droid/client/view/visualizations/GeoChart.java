package at.tugraz.iaik.nf4droid.client.view.visualizations;

import com.google.gwt.ajaxloader.client.ArrayHelper;
import com.google.gwt.ajaxloader.client.Properties;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Element;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.CommonChartOptions;
import com.google.gwt.visualization.client.Selectable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.Visualization;

public class GeoChart extends Visualization<GeoChart.Options> implements
		Selectable {

	public static final String PACKAGE = "geochart";

	public static enum Resolution {
		COUNTRIES, PROVINCES, METROS;
	}

	public static enum DisplayMode {
		AUTO, REGIONS, MARKERS;
	}

	public static class Options extends CommonChartOptions {
		public static Options create() {
			return JavaScriptObject.createObject().cast();
		}

		protected Options() {
		}

		public final void setDisplayMode(DisplayMode mode) {
			this.set("displayMode", mode.toString().toLowerCase());
		}

		public final void setRegion(String region) {
			this.set("region", region);
		}

		public final void setResolution(Resolution resolution) {
			this.set("resolution", resolution.toString().toLowerCase());
		}

		public final void setMagnifyingGlass(boolean enable) {
			this.set("magnifyingGlass.enable", enable);
		}
		
		public final void setLegendNumberFormat(final String icuFormatString) {
			this.set("legend.numberFormat", icuFormatString);
		}

		public final void setColorAxis(int minValue, int maxValue,
				String... colors) {
			setColorAxis(ColorAxis.create(ArrayHelper.toJsArrayString(colors),
					minValue, maxValue));
		}

		public final void setColorAxis(String... colors) {
			setColorAxis(ColorAxis.create(ArrayHelper.toJsArrayString(colors)));
		}

		private void setColorAxis(ColorAxis colorAxis) {
			this.set("colorAxis", colorAxis);
		}
	}

	public static class ColorAxis extends Properties {
		public static ColorAxis create() {
			return JavaScriptObject.createObject().cast();
		}

		public static ColorAxis create(JsArrayString colors) {
			ColorAxis result = create();
			result.set("colors", colors);
			return result;
		}

		public static ColorAxis create(JsArrayString colors, int minValue,
				int maxValue) {
			ColorAxis result = create();
			result.set("colors", colors);
			result.setMinValue(minValue);
			result.setMaxValue(maxValue);
			return result;
		}

		public static ColorAxis create(JsArrayString colors, int minValue,
				int maxValue, JsArrayNumber values) {
			ColorAxis result = create();
			result.set("colors", colors);
			result.set("values", values);
			result.setMinValue(minValue);
			result.setMaxValue(maxValue);
			return result;
		}

		protected ColorAxis() {
		}

		public final native void setMinValue(int minValue) /*-{
			this.minValue = minValue;
		}-*/;

		public final native void setMaxValue(int maxValue) /*-{
			this.maxValue = maxValue;
		}-*/;

	}

	public GeoChart() {
		super();
	}

	public GeoChart(AbstractDataTable data, Options options) {
		super(data, options);
	}

	@Override
	public void addSelectHandler(SelectHandler handler) {
		Selection.addSelectHandler(this, handler);
	}

	@Override
	public JsArray<Selection> getSelections() {
		return Selection.getSelections(this);
	}

	@Override
	public void setSelections(JsArray<Selection> sel) {
		Selection.setSelections(this, sel);
	}

	@Override
	protected native JavaScriptObject createJso(Element parent) /*-{
		return new $wnd.google.visualization.GeoChart(parent);
	}-*/;

}
