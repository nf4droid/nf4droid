package at.tugraz.iaik.nf4droid.server.analysis.testcase;

import at.tugraz.iaik.nf4droid.server.analysis.AbstractExposeTest;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;

public class AndroidIdAdWhirlTest extends AbstractExposeTest {

	private static final String AD_WHIRL_CONSTANT = "AdWhirl";
	
	@Override
	public void initValue() {
		if (this.trafficCapture != null) {
			testValue = this.trafficCapture.getAndroidId()+AD_WHIRL_CONSTANT;
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
