package at.tugraz.iaik.nf4droid.server.analysis;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import at.tugraz.iaik.nf4droid.server.common.utils.HashUtil;
import at.tugraz.iaik.nf4droid.server.domain.model.DataExpose;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpRequest;
import at.tugraz.iaik.nf4droid.server.domain.model.TrafficCapture;
import at.tugraz.iaik.nf4droid.server.domain.service.DomainGeneralDataService;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeObscuring;
import at.tugraz.iaik.nf4droid.shared.constant.ExposePoint;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;
import at.tugraz.iaik.nf4droid.shared.dto.TimeValuePair;

@Configurable
public abstract class AbstractExposeTest implements ExposeTest {

	@Autowired
	private DomainGeneralDataService domainGeneralDataService;

	protected TrafficCapture trafficCapture;
	private List<DataExpose> dataExposes;
	protected String testValue;

	@Override
	public void initTest(TrafficCapture trafficCapture) {
		this.trafficCapture = trafficCapture;
		this.dataExposes = new ArrayList<DataExpose>();
		initValue();
	}

	@Override
	public boolean testPlainText() {
		return true;
	}

	@Override
	public boolean testMd5Hash() {
		return false;
	}

	@Override
	public boolean testSha1Hash() {
		return false;
	}

	@Override
	public boolean testSha256Hash() {
		return false;
	}

	public boolean testHttpParameters() {
		return true;
	}

	public boolean testHttpHeaderFields() {
		return true;
	}

	public abstract void initValue();

	public abstract ExposeType getExposeType();

	public List<DataExpose> doTest() {
		if (testValue != null && !testValue.isEmpty()) {
			if (testPlainText()) {
				doTestOnValue(testValue, ExposeObscuring.PLAIN);
			}
			if (testMd5Hash()) {
				doTestOnValue(HashUtil.getMd5(testValue), ExposeObscuring.MD5);
				doTestOnValue(HashUtil.getMd5(testValue.toLowerCase()), ExposeObscuring.MD5);
				doTestOnValue(HashUtil.getMd5(testValue.toUpperCase()), ExposeObscuring.MD5);
			}
			if (testSha1Hash()) {
				doTestOnValue(HashUtil.getSha1(testValue), ExposeObscuring.SHA1);
				doTestOnValue(HashUtil.getSha1(testValue.toLowerCase()), ExposeObscuring.SHA1);
				doTestOnValue(HashUtil.getSha1(testValue.toUpperCase()), ExposeObscuring.SHA1);
			}
			if (testSha256Hash()) {
				doTestOnValue(HashUtil.getSha256(testValue),
						ExposeObscuring.SHA256);
				doTestOnValue(HashUtil.getSha256(testValue.toLowerCase()),
						ExposeObscuring.SHA256);
				doTestOnValue(HashUtil.getSha256(testValue.toUpperCase()),
						ExposeObscuring.SHA256);
			}
			return dataExposes;
		}
		return null;
	}

	private void doTestOnValue(final String exposedValue,
			final ExposeObscuring exposeObscuring) {
		if (exposedValue != null && !exposedValue.isEmpty()) {
			if (testHttpParameters()) {
				final List<TimeValuePair> httpReqIdTimeValues = domainGeneralDataService
						.findHttpReqIdTimeWhereParameterLike(exposedValue,
								trafficCapture.getId());
				addDataExposesToList(httpReqIdTimeValues, exposeObscuring,
						ExposePoint.HTTP_PARAMETER, exposedValue);
			}
			if (testHttpHeaderFields()) {
				final List<TimeValuePair> httpReqIdTimeValues = domainGeneralDataService
						.findHttpReqIdTimeWhereHeaderFieldLike(exposedValue,
								trafficCapture.getId());
				addDataExposesToList(httpReqIdTimeValues, exposeObscuring,
						ExposePoint.HEADER_FIELD, exposedValue);
			}
		}
		else {
			//TODO log that empty data
		}
	}

	private void addDataExposesToList(
			final List<TimeValuePair> httpReqIdTimeValues,
			ExposeObscuring exposeObscuring, ExposePoint exposePoint,
			String exposedValue) {
		if (httpReqIdTimeValues != null && !httpReqIdTimeValues.isEmpty()) {
			for (TimeValuePair timeValuePair : httpReqIdTimeValues) {
				dataExposes.add(new DataExpose(new Instant(timeValuePair
						.getTime()), DroidHttpRequest
						.findDroidHttpRequest(timeValuePair.getValue()),
						getExposeType(), exposeObscuring, exposePoint,
						exposedValue));
			}
		}
	}
}
