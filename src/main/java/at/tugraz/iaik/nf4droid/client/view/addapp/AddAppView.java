package at.tugraz.iaik.nf4droid.client.view.addapp;

import gwtupload.client.Uploader;

import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SuggestOracle;

public interface AddAppView extends IsWidget {

	public Uploader getUploader();

	public void showMessage(AlertType alertType, final String heading,
			final String message);

	public void hideMessage();
	
	public void showProgress(final AlertType alertType, final String heading,
			final String message);
	
	public void hideProgress();

	public void setAppPackageSuggestOracle(
			final SuggestOracle appPackageSuggestOracle);

	public void setAppVersionSuggestOracle(
			final SuggestOracle appVersionCodeSuggestOracle);
	
	public HasText getAppPackageTextBox();
}
