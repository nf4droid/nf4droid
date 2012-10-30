package at.tugraz.iaik.nf4droid.client.view.timeline.expose;

import at.tugraz.iaik.nf4droid.shared.constant.ExposeObscuring;
import at.tugraz.iaik.nf4droid.shared.constant.ExposePoint;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;

import com.google.gwt.user.client.ui.IsWidget;

public interface ExposeInfoView extends IsWidget {
	public void setData(ExposeType exposeType, ExposeObscuring exposeObscuring,
			ExposePoint exposePoint, String exposedValue, String localIp,
			String remoteIp, String localPort, String remotePort,
			String remoteCountry, String remoteCity, String url,
			String headerFields, String httpParameters);
}
