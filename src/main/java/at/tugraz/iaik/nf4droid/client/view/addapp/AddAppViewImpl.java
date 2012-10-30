package at.tugraz.iaik.nf4droid.client.view.addapp;

import gwtupload.client.BaseUploadStatus;
import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus;
import gwtupload.client.Uploader;

import java.util.ArrayList;
import java.util.List;

import at.tugraz.iaik.nf4droid.client.view.common.bootstrap.Span;
import at.tugraz.iaik.nf4droid.shared.dto.AppSuggestion;
import at.tugraz.iaik.nf4droid.shared.dto.AppVersionSuggestion;

import com.github.gwtbootstrap.client.ui.AlertBlock;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.ControlLabel;
import com.github.gwtbootstrap.client.ui.Controls;
import com.github.gwtbootstrap.client.ui.Fieldset;
import com.github.gwtbootstrap.client.ui.FormActions;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.HelpBlock;
import com.github.gwtbootstrap.client.ui.HelpInline;
import com.github.gwtbootstrap.client.ui.Legend;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.LabelType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;

public class AddAppViewImpl extends Composite implements AddAppView {
	
	private static final int ERROR_MESSAGE_HIDE_DELAY = 50000;

	@UiField
	protected Column formArea;

	private Uploader uploader;

	@UiField
	protected AlertBlock alertBlock;
	
	@UiField
	protected AlertBlock progressBlock;

	private TextBoxBase appPackageTextBox;
	private FlowPanel appPackagPanel;
	private TextBox appVersionCodeBox;
	private FlowPanel appVersionCodePanel;
	private TextBox appVersionNameTextBox;
	private TextBox appNameTextBox;

	private static class SingletonHolder {
		private static AddAppViewImpl instance = new AddAppViewImpl();
	}

	public static AddAppViewImpl getInstance() {
		return SingletonHolder.instance;
	}

	private static AddAppViewImplUiBinder uiBinder = GWT
			.create(AddAppViewImplUiBinder.class);

	interface AddAppViewImplUiBinder extends UiBinder<Widget, AddAppViewImpl> {
	}

	private ControlGroup createControlGroupWithLabel(final String text,
			final String forId, final Widget... widgets) {
		final ControlGroup group = new ControlGroup();
		final ControlLabel label = new ControlLabel(text);
		label.setFor(forId);
		group.add(label);
		final Controls controls = new Controls();
		if (widgets != null) {
			for (int i = 0; i < widgets.length; i++) {
				controls.add(widgets[i]);
			}
		}

		group.add(controls);
		return group;
	}

	private FlowPanel wrapWidgetsInFlowPanel(final Widget... widgets) {
		final FlowPanel panel = new FlowPanel();
		if (widgets != null) {
			for (int i = 0; i < widgets.length; i++) {
				panel.add(widgets[i]);
			}
		}
		return panel;
	}

	private HelpInline createInlineHelp(final String text) {
		final HelpInline help = new HelpInline();
		help.setText(text);
		return help;
	}

	private HelpBlock createBlockHelp(final String text) {
		return new HelpBlock(text);
	}

	private TextBox createTextBox(final String name) {
		final TextBox textBox = new TextBox();
		textBox.setName(name);
		return textBox;
	}

	private AddAppViewImpl() {
		
		initWidget(uiBinder.createAndBindUi(this));

		uploader = new Uploader(FileInputType.BROWSER_INPUT);
		uploader.setTitle("Traffic capture file");
		uploader.setAutoSubmit(false);
		uploader.setValidExtensions("pcap", "cap");
		uploader.avoidEmptyFiles(true);

		final FormPanel form = uploader.getForm();
		form.setStylePrimaryName("form-horizontal");

		
		final Fieldset captureFieldset = new Fieldset();
		captureFieldset.add(new Legend("Traffic Capture"));
		form.add(captureFieldset);
		
		final ControlGroup fileGroup = createControlGroupWithLabel(
				"Traffic Capture File", "captureFile", uploader.getFileInput().getWidget(),createBlockHelp("Traffic capture file (pcap) recorded during the usage of an app."));
		captureFieldset.add(fileGroup);
		
		final TextArea descTextArea = new TextArea();
		descTextArea.setName("description");
		descTextArea.setSize("380px", "100px");
		
		final ControlGroup descriptionGroup = createControlGroupWithLabel(
				"Description", "description", descTextArea);
		captureFieldset.add(descriptionGroup);

		final Fieldset appFieldset = new Fieldset();
		appFieldset.add(new Legend("App infos"));
		form.add(appFieldset);

		appPackageTextBox = createTextBox("appPackage");
		appPackagPanel = wrapWidgetsInFlowPanel(
				appPackageTextBox,
				createInlineHelp("e.g. com.facebook.katana"),
				createBlockHelp("The app package name is the unqiue identifier of the app."));

		final ControlGroup appPackageGroup = createControlGroupWithLabel(
				"App package*", "appPackage", appPackagPanel);
		appFieldset.add(appPackageGroup);

		appNameTextBox = createTextBox("appName");
		final ControlGroup appNameGroup = createControlGroupWithLabel(
				"App name*", "appName", appNameTextBox,
				createInlineHelp("e.g. Facebook for Android"));
		appFieldset.add(appNameGroup);

		appVersionCodeBox = createTextBox("appVersionCode");
		appVersionCodePanel = wrapWidgetsInFlowPanel(
				appVersionCodeBox,
				createInlineHelp("e.g. 21878"),
				createBlockHelp("The app version code uniquely idtenifies a certain version of an app."));

		final ControlGroup appVersionCodeGroup = createControlGroupWithLabel(
				"App version code*", "appVersionCode", appVersionCodePanel);
		appFieldset.add(appVersionCodeGroup);

		appVersionNameTextBox = createTextBox("appVersionName");
		final ControlGroup appVersionNameGroup = createControlGroupWithLabel(
				"App version name*", "appVersionName", appVersionNameTextBox,
				createInlineHelp("e.g. 1.9.6"));
		appFieldset.add(appVersionNameGroup);

		final Fieldset envFieldset = new Fieldset();
		envFieldset.add(new Legend("Test Environment"));
		form.add(envFieldset);

		envFieldset.add(new Heading(6, "Cellular"));

		final ControlGroup phoneNrGroup = createControlGroupWithLabel(
				"Phone number", "phoneNr", createTextBox("phoneNr"),
				createInlineHelp("e.g. +436507230030"));
		envFieldset.add(phoneNrGroup);

		final ControlGroup imsiGroup = createControlGroupWithLabel("IMSI",
				"imsi", createTextBox("imsi"),
				createInlineHelp("e.g. 232072502440490"));
		envFieldset.add(imsiGroup);

		final ControlGroup imeiGroup = createControlGroupWithLabel("IMEI",
				"imei", createTextBox("imei"),
				createInlineHelp("e.g. 353160040532967"));
		envFieldset.add(imeiGroup);

		envFieldset.add(new Heading(6, "Android"));

		final ControlGroup androidIdGroup = createControlGroupWithLabel(
				"Android ID", "androidId", createTextBox("androidId"),
				createInlineHelp("e.g. e345846ceb0c6153"));
		envFieldset.add(androidIdGroup);

		envFieldset.add(new Heading(6, "Account"));

		final ControlGroup userGroup = createControlGroupWithLabel("User",
				"user", createTextBox("user"),
				createInlineHelp("e.g. nf4droid@gmail.com"));
		envFieldset.add(userGroup);

		final HelpBlock pwHelpBlock = new HelpBlock();
		pwHelpBlock.add(new com.github.gwtbootstrap.client.ui.Label(
				LabelType.WARNING, "Warning!"));
		pwHelpBlock
				.add(new Span(
						" Please only enter if you use a test account! Will be stored in plaintext!"));
		final ControlGroup passwordGroup = createControlGroupWithLabel(
				"Password", "password", createTextBox("password"), pwHelpBlock);
		envFieldset.add(passwordGroup);

		envFieldset.add(new Heading(6, "Location"));

		final ControlGroup locationGroup = createControlGroupWithLabel(
				"Location latitude", "latitude", createTextBox("latitude"),
				createInlineHelp("e.g. 47.07"));
		envFieldset.add(locationGroup);

		envFieldset.add(new Heading(6, "Wifi"));

		final ControlGroup ssidGroup = createControlGroupWithLabel("SSID",
				"ssid", createTextBox("ssid"),
				createInlineHelp("e.g. Linksys1234"));
		envFieldset.add(ssidGroup);

		final ControlGroup bssidGroup = createControlGroupWithLabel("BSSID",
				"bssid", createTextBox("bssid"),
				createInlineHelp("e.g. 06:24:FE:05:95:D0"));
		envFieldset.add(bssidGroup);

		final FormActions formActions = new FormActions();
		final Button submitButton = new Button("Submit");
		submitButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				// scroll to top
				Document.get().getElementById("Apps").scrollIntoView();

				if (validateInput()) {
					uploader.submit();
				}
			}
		});
		submitButton.setType(ButtonType.PRIMARY);
		formActions.add(submitButton);
		uploader.add(formActions);
		
		IUploadStatus statusWidget = new NF4DroidUploadStatus();
		uploader.setStatusWidget(statusWidget);

		formArea.add(uploader);

	}

	private boolean validateInput() {
		boolean isValid = true;
		List<String> invalidFields = new ArrayList<String>();

		if (uploader.getFileInput().getFilename() == null || uploader.getFileInput().getFilename().isEmpty()) {
			isValid = false;
			invalidFields.add("traffic capture file");
		}
		
		if (appPackageTextBox.getValue() == null || appPackageTextBox.getValue().isEmpty()) {
			isValid = false;
			invalidFields.add("app packge name");
		}
		
		if (appNameTextBox.getValue() == null || appNameTextBox.getValue().isEmpty()) {
			isValid = false;
			invalidFields.add("app name");
		} 
		
		if (appVersionCodeBox.getValue() == null || appVersionCodeBox.getValue().isEmpty()) {
			isValid = false;
			invalidFields.add("version code number");
		}
		else {
			try {
				Integer.valueOf(appVersionCodeBox.getValue());
			} catch (NumberFormatException e) {
				invalidFields.add("version code number is invalid");
				isValid = false;
			}
		}
		
		if (appVersionNameTextBox.getValue() == null || appVersionNameTextBox.getValue().isEmpty()) {
			isValid = false;
			invalidFields.add("version name");
		}
		
		if (!isValid) {
			
			StringBuilder stb = new StringBuilder("Please add/change: ");
			for (int i=0; i < invalidFields.size(); i++) {
				if (i > 0 && i < invalidFields.size()) {
					stb.append(", ");
				}
				stb.append(invalidFields.get(i));
			}
			
			showMessage(AlertType.WARNING, "Missing Required Fields", stb.toString());
		}
		
		return isValid;
	}
	
	private class NF4DroidUploadStatus extends BaseUploadStatus {

		@Override
		public void setProgress(int done, int total) {
			GWT.log("File upload - done: "+done+" total: "+total);
			
			// if file upload is finished, the processing starts
			if (done == total) {
				showProgress(AlertType.SUCCESS, "Processing...", "Processing of traffic capture file. Warning: This may take a while!!");
			}
		}

		
		@Override
		public void setStatus(Status stat) {
			GWT.log("GWTUpload status: "+stat.name());
			
			// start of file upload
			if (stat == Status.INPROGRESS) {
				showProgress(AlertType.SUCCESS, "Uploading...", "Upload of traffic capture file.");
			} 
			// error case
			else if (stat == Status.CANCELED || stat == Status.CANCELING || stat == Status.ERROR || stat == Status.INVALID) {
				hideProgress();
				showMessage(AlertType.ERROR, "Error", "Couldn't impor traffic capture!");
			}

		}

		@Override
		protected void updateStatusPanel(boolean showProgress, String message) {
			// do nothing
		}


		
		@Override
		public void setFileName(String name) {
			// do nothing
		}
		
		
		
	}

	public void setAppPackageSuggestOracle(
			final SuggestOracle appPackageSuggestOracle) {
		final SuggestBox appPackageSuggestBox = createSuggestBox(
				appPackageSuggestOracle, (TextBox) appPackageTextBox);

		// TODO check removal of handler
		appPackageSuggestBox
				.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {

					@Override
					public void onSelection(SelectionEvent<Suggestion> event) {

						if (event.getSelectedItem() != null
								&& event.getSelectedItem() instanceof AppSuggestion) {
							final AppSuggestion suggestion = (AppSuggestion) event
									.getSelectedItem();
							appNameTextBox.setValue(suggestion.getAppName());
						}
					}
				});
		appPackageTextBox = appPackageSuggestBox.getTextBox();
		appPackagPanel.insert(appPackageSuggestBox, 0);
	}

	public void setAppVersionSuggestOracle(
			final SuggestOracle appVersionCodeSuggestOracle) {

		final SuggestBox appVersionCodeSuggestBox = createSuggestBox(
				appVersionCodeSuggestOracle, appVersionCodeBox);

		// TODO check removal of handler
		appVersionCodeSuggestBox
				.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {

					@Override
					public void onSelection(SelectionEvent<Suggestion> event) {

						if (event.getSelectedItem() != null
								&& event.getSelectedItem() instanceof AppVersionSuggestion) {
							final AppVersionSuggestion suggestion = (AppVersionSuggestion) event
									.getSelectedItem();
							appVersionNameTextBox.setValue(suggestion
									.getAppVersionName());
						}
					}
				});

		appVersionCodePanel.insert(appVersionCodeSuggestBox, 0);
	}

	private SuggestBox createSuggestBox(final SuggestOracle oracle,
			final TextBox textBox) {
		final SuggestBox suggestBox = new SuggestBox(oracle, textBox);
		suggestBox.setLimit(10);
		suggestBox.getElement().setPropertyString("autocomplete", "off");

		return suggestBox;
	}

	public void showProgress(final AlertType alertType, final String heading,
			final String message) {
		progressBlock.setType(AlertType.SUCCESS);
		progressBlock.setText(message);
		progressBlock.setHeading(heading);
		progressBlock.setVisible(true);
	}
	
	public void hideProgress() {
		progressBlock.setVisible(false);
	}

	public void showMessage(final AlertType alertType, final String heading,
			final String message) {
		alertBlock.setType(alertType);
		alertBlock.setClose(true);
		alertBlock.setText(message);
		alertBlock.setHeading(heading);
		alertBlock.setVisible(true);

		final Timer t = new Timer() {
			public void run() {
				alertBlock.clear();
				alertBlock.setVisible(false);
			}
		};

		t.schedule(ERROR_MESSAGE_HIDE_DELAY);

	}
	
	public void hideMessage() {
		alertBlock.clear();
		alertBlock.setVisible(false);
	}

	@Override
	public Uploader getUploader() {
		return uploader;
	}

	@Override
	public HasText getAppPackageTextBox() {
		return appPackageTextBox;
	}


}
