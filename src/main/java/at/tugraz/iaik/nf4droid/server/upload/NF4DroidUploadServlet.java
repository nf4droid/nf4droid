package at.tugraz.iaik.nf4droid.server.upload;

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormat;

import at.tugraz.iaik.nf4droid.server.analysis.DataExposeAnalysis;
import at.tugraz.iaik.nf4droid.server.analysis.DataExposeAnalysisImpl;
import at.tugraz.iaik.nf4droid.server.domain.model.App;
import at.tugraz.iaik.nf4droid.server.domain.model.AppVersion;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidProcessingResult;
import at.tugraz.iaik.nf4droid.server.domain.model.TrafficCapture;
import at.tugraz.iaik.nf4droid.server.process.KrakenPcapProcessor;

public class NF4DroidUploadServlet extends UploadAction {

	
	private static final Logger LOGGER = Logger
			.getLogger(NF4DroidUploadServlet.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int ERROR_MISSING_REQ_FIELD = -1;
	private static final int ERROR_PROCESSING = -2;
	private static final int ERROR_EMPTY_FILE = -3;
	private static final int ERROR_IO_PROBLEM = -4;

	/**
	 * Override executeAction to save the received files in a custom place and
	 * delete this items from session.
	 */
	@Override
	public String executeAction(HttpServletRequest request,
			List<FileItem> sessionFiles) throws UploadActionException {
		LOGGER.info("Start handling upload of new app traffic capture...");

		String ret = null;
		String appName = null;
		String appPackage = null;
		int versionCode = -1;
		String versionName = null;
		byte[] pcapFile = null;

		for (FileItem item : sessionFiles) {
			if (item.isFormField()) {
				final String fieldName = item.getFieldName();
				final String fieldValue = item.getString();
				LOGGER.info("POST form field: " + fieldName + "=[" + fieldValue
						+ "]");
				if (fieldName.equals("appName")) {
					appName = fieldValue;
				} else if (fieldName.equals("appPackage")) {
					appPackage = fieldValue;
				} else if (fieldName.equals("appVersionCode")) {
					if (!fieldValue.isEmpty()) {
						versionCode = Integer.parseInt(item.getString());
					}
				} else if (fieldName.equals("appVersionName")) {
					if (!item.getString().isEmpty()) {
						versionName = fieldValue;
					}
				}

			} else {
				LOGGER.info("POST file: " + item.getFieldName() + "=["
						+ item.getName() + "(" + Long.toString(item.getSize())
						+ ")]");
				try {

					pcapFile = IOUtils.toByteArray(item.getInputStream());

				} catch (NullPointerException e) {
					LOGGER.error("Received empty file!",new UploadActionException(e)); 
					return Integer.toString(ERROR_EMPTY_FILE);
				} 
				catch (IOException e) {
					LOGGER.error("I/O problem during upload!",new UploadActionException(e)); 
					return Integer.toString(ERROR_IO_PROBLEM);
				}
			}
		}

		
		if ((appName != null) && (pcapFile != null) && (appPackage != null)
				&& (versionCode != -1) && (versionName != null)) {
			App app = App.findAppWherePackageEq(appPackage); 
			if (app == null) {
				app = new App();
				app.setAppName(appName);
				app.setAppPackage(appPackage);
				app.persist();
			}

			AppVersion appVersion = AppVersion.findAppVersionForAppWhereVersionCodeEq(app.getId(), versionCode);
			if (appVersion == null) {
				appVersion = new AppVersion();
				appVersion.setVersionCode(versionCode);
				appVersion.setVersionName(versionName);
				appVersion.setApp(app);
				appVersion.persist();
			}

			TrafficCapture trafficCapture = new TrafficCapture();
			trafficCapture.setDateAdded(new Date());
			trafficCapture.setPcapFile(pcapFile);
			trafficCapture.setAppVersion(appVersion);
			
			final Vector<FileItem> fields = new Vector<FileItem>(sessionFiles);
			
			trafficCapture.setDescription(getFormField(fields, "description"));;
			trafficCapture.setAndroidId(getFormField(fields, "androidId"));
			trafficCapture.setPhoneNr(getFormField(fields, "phoneNr"));
			trafficCapture.setUser(getFormField(fields, "user"));
			trafficCapture.setPassword(getFormField(fields, "password"));
			trafficCapture.setSsid(getFormField(fields, "ssid"));
			trafficCapture.setBssid(getFormField(fields, "bssid"));
			trafficCapture.setLatitude(getFormField(fields, "latitude"));
			final String imei = getFormField(fields, "imei");
			if (imei != null && !imei.isEmpty()) {
				trafficCapture.setImei(Long.parseLong(imei));
			}
			final String imsi = getFormField(fields, "imsi");
			if (imsi != null && !imsi.isEmpty()) {
				trafficCapture.setImsi(Long.parseLong(imsi));
			}
			
			trafficCapture.persist();
			
			//process traffic capture
			final int status = process(trafficCapture);
			if (status > 0) {
				DataExposeAnalysis analysis = DataExposeAnalysisImpl.getInstance();
				analysis.analyseDataExpose(trafficCapture);
				
				ret = Long.toString(trafficCapture.getId());
			} else {
				ret = Integer.toString(ERROR_PROCESSING);
			}

		} else {
			LOGGER.error("POST is missing some parameters ");
			ret = Integer.toString(ERROR_MISSING_REQ_FIELD);
		}

		removeSessionFileItems(request);
		
		LOGGER.info("Finished handling of new app traffic capture upload...");

		return ret;
	}
	

	private int process(final TrafficCapture capture) {
		File tmpPcapFile = null;
		try {
			tmpPcapFile = File.createTempFile("traffic_capture_"
					+ capture.getId(), ".pcap");
			FileUtils.writeByteArrayToFile(tmpPcapFile,
					capture.getPcapFile());

			DateTime startTime = new DateTime();
			KrakenPcapProcessor pcapProcessor = new KrakenPcapProcessor(
					tmpPcapFile);
			DroidProcessingResult processingResult = pcapProcessor
					.processPcapFile();
			LOGGER.info("processing time: "
					+ PeriodFormat.getDefault().print(
							new Period(startTime, new DateTime(),
									PeriodType.time())));

			LOGGER.info("start persist result");
			LOGGER.info(PeriodFormat.getDefault()
					.print(new Period(startTime, new DateTime(), PeriodType
							.time())));
			processingResult.persist();
			LOGGER.info("start merge capture");
			LOGGER.info(PeriodFormat.getDefault()
					.print(new Period(startTime, new DateTime(), PeriodType
							.time())));
			capture.setProcessingResult(processingResult);
			capture.merge();
			LOGGER.info("finished merge capture");
			LOGGER.info(PeriodFormat.getDefault()
					.print(new Period(startTime, new DateTime(), PeriodType
							.time())));

			LOGGER.info("successfully processed traffic capture (id: "
					+ capture.getId() + ")");
			return 1;
		} catch (IOException e) {
			LOGGER.error("error processing traffic capture (id: "
					+ capture.getId() + ")");
			return -1;
		} finally {
			FileUtils.deleteQuietly(tmpPcapFile);
		}
	}
}
