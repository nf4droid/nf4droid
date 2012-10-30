package at.tugraz.iaik.nf4droid.server.analysis.testcase;

import at.tugraz.iaik.nf4droid.server.analysis.AbstractExposeTest;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;

public class SsidTest extends AbstractExposeTest {

	@Override
	public void initValue() {
		if (this.trafficCapture != null) {
			testValue = this.trafficCapture.getSsid();
		}
	}

	@Override
	public ExposeType getExposeType() {
		return ExposeType.SSID;
	}

}
