package at.tugraz.iaik.nf4droid.server.analysis.testcase;

import at.tugraz.iaik.nf4droid.server.analysis.AbstractExposeTest;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;

public class BssidTest extends AbstractExposeTest {

	@Override
	public void initValue() {
		if (this.trafficCapture != null) {
			testValue = this.trafficCapture.getBssid();
		}
	}

	@Override
	public ExposeType getExposeType() {
		return ExposeType.BSSID;
	}
}
