package at.tugraz.iaik.nf4droid.server.analysis.testcase;

import at.tugraz.iaik.nf4droid.server.analysis.AbstractExposeTest;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;

public class AndroidIdTest extends AbstractExposeTest {

	@Override
	public void initValue() {
		if (this.trafficCapture != null) {
			testValue = this.trafficCapture.getAndroidId();
		}
	}

	@Override
	public ExposeType getExposeType() {
		return ExposeType.ANDROID_ID;
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
