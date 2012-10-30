package at.tugraz.iaik.nf4droid.server.analysis.testcase;

import at.tugraz.iaik.nf4droid.server.analysis.AbstractExposeTest;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;

public class PasswordTest extends AbstractExposeTest {

	@Override
	public void initValue() {
		if (this.trafficCapture != null) {
			testValue = this.trafficCapture.getPassword();
		}
	}

	@Override
	public ExposeType getExposeType() {
		return ExposeType.PASSWORD;
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
