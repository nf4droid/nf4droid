package at.tugraz.iaik.nf4droid.server.analysis;

import java.util.List;

import at.tugraz.iaik.nf4droid.server.domain.model.DataExpose;
import at.tugraz.iaik.nf4droid.server.domain.model.TrafficCapture;

public interface ExposeTest {

	public void initTest(final TrafficCapture trafficCapture);
	public void initValue();
	public List<DataExpose> doTest();
	public boolean testPlainText();
	public boolean testMd5Hash();
	public boolean testSha1Hash();
	public boolean testSha256Hash();
	public boolean testHttpParameters();
	public boolean testHttpHeaderFields();
	
}
