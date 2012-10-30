package at.tugraz.iaik.nf4droid.server.analysis.testcase;

import at.tugraz.iaik.nf4droid.server.analysis.AbstractExposeTest;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;

public class ImeiTest extends AbstractExposeTest {

	@Override
	public void initValue() {
		if (this.trafficCapture != null) {
			final Long imei = this.trafficCapture.getImei();
			if (imei != null) {
				testValue = imei.toString();
			}
		}
	}

	@Override
	public ExposeType getExposeType() {
		return ExposeType.IMEI;
	}

	@Override
	public boolean testMd5Hash() {
		return true;
	}

	@Override
	public boolean testSha1Hash() {
		return true;
	}

	@Override
	public boolean testSha256Hash() {
		return true;
	}
}
