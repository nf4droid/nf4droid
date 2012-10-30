package at.tugraz.iaik.nf4droid.client.view.httpreq.request;

import com.google.gwt.user.client.ui.IsWidget;

public interface HttpRequestView extends IsWidget {
	public void setData(final String localIp,
			final String remoteIp, final String localPort, final String remotePort,
			final String remoteCountry, final String remoteCity, final String method, final String version, final String url,
			final String headerFields, final String httpParameters);
}
