package at.tugraz.iaik.nf4droid.shared.requestfactory;

import at.tugraz.iaik.nf4droid.client.service.requestfactory.request.AppRequest;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.request.AppVersionRequest;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.request.DetailAppRequest;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.request.DetailAppVersionRequest;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.request.DetailDroidHttpReqRequest;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.request.DetailProcessResultRequest;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.request.DetailTrafficCaptureRequest;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.request.DroidEthernetFrameRequest;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.request.DroidHttpRequestRequest;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.request.DroidProcessingResultRequest;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.request.TrafficCaptureRequest;

import com.google.web.bindery.requestfactory.shared.LoggingRequest;
import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface AnalyticsRequestFactory extends RequestFactory {

	DroidEthernetFrameRequest ethFrameRequest();
	
	AppRequest appRequest();

	DetailAppRequest detailAppRequest();
	
	AppVersionRequest appVersionRequest();
	
	DetailAppVersionRequest detailAppVersionRequest();

	TrafficCaptureRequest trafficCaptureRequest();
	
	DetailTrafficCaptureRequest detailTrafficCaptureRequest();
	
	DetailProcessResultRequest detailProcessResulRequest();
	
	DroidProcessingResultRequest processingResultRequest();
	
	DroidHttpRequestRequest httpRequestRequest();
	
    LoggingRequest loggingRequest();
    
    DetailDroidHttpReqRequest detailHttpReqRequest();
}
