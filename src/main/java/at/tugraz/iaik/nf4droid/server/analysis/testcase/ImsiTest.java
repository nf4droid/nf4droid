package at.tugraz.iaik.nf4droid.server.analysis.testcase;

import at.tugraz.iaik.nf4droid.server.analysis.AbstractExposeTest;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;

public class ImsiTest extends AbstractExposeTest {

	@Override
	public void initValue() {
		if (this.trafficCapture != null) {
			final Long imsi = this.trafficCapture.getImsi();
			if (imsi != null) {
				testValue = imsi.toString();
			}
		}
	}

	@Override
	public ExposeType getExposeType() {
		return ExposeType.IMSI;
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
