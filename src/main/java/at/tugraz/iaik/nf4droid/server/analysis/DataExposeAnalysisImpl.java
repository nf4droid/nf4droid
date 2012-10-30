package at.tugraz.iaik.nf4droid.server.analysis;

import java.util.ArrayList;
import java.util.List;

import at.tugraz.iaik.nf4droid.server.analysis.testcase.AndroidIdTest;
import at.tugraz.iaik.nf4droid.server.analysis.testcase.BssidTest;
import at.tugraz.iaik.nf4droid.server.analysis.testcase.ImeiTest;
import at.tugraz.iaik.nf4droid.server.analysis.testcase.ImsiTest;
import at.tugraz.iaik.nf4droid.server.analysis.testcase.LocationTest;
import at.tugraz.iaik.nf4droid.server.analysis.testcase.PasswordTest;
import at.tugraz.iaik.nf4droid.server.analysis.testcase.PhoneNrTest;
import at.tugraz.iaik.nf4droid.server.analysis.testcase.SsidTest;
import at.tugraz.iaik.nf4droid.server.analysis.testcase.UserTest;
import at.tugraz.iaik.nf4droid.server.domain.model.DataExpose;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidProcessingResult;
import at.tugraz.iaik.nf4droid.server.domain.model.TrafficCapture;

public class DataExposeAnalysisImpl implements DataExposeAnalysis {

	private List<ExposeTest> exposeTests;

	private static class SingletonHolder {
		private static DataExposeAnalysisImpl instance = new DataExposeAnalysisImpl();
	}

	public static DataExposeAnalysisImpl getInstance() {
		return SingletonHolder.instance;
	}

	private DataExposeAnalysisImpl() {
		super();
		exposeTests = new ArrayList<ExposeTest>();
		exposeTests.add(new AndroidIdTest());
		exposeTests.add(new ImeiTest());
		exposeTests.add(new ImsiTest());
		exposeTests.add(new LocationTest());
		exposeTests.add(new PhoneNrTest());
		exposeTests.add(new UserTest());
		exposeTests.add(new PasswordTest());
		exposeTests.add(new SsidTest());
		exposeTests.add(new BssidTest());
	}

	public void analyseDataExpose(final TrafficCapture trafficCapture) {
		final List<DataExpose> dataExposes = new ArrayList<DataExpose>();
		List<DataExpose> tmpDataExposes = null;

		for (ExposeTest exposeTest : this.exposeTests) {
			exposeTest.initTest(trafficCapture);
			tmpDataExposes = exposeTest.doTest();
			if (tmpDataExposes != null) {
				dataExposes.addAll(tmpDataExposes);
			}
		}

		persistDataExposes(dataExposes, trafficCapture);
	}

	private void persistDataExposes(final List<DataExpose> dataExposes, final TrafficCapture trafficCapture) {
		if (dataExposes != null && !dataExposes.isEmpty() && trafficCapture != null) {
			
			DroidProcessingResult procRes = trafficCapture.getProcessingResult();
			procRes.setDataExposes(dataExposes);
			procRes.persist();
		}
	}
}
