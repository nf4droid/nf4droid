package at.tugraz.iaik.nf4droid.client.activity;

import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;

import java.util.List;

import at.tugraz.iaik.nf4droid.client.general.factory.ClientFactory;
import at.tugraz.iaik.nf4droid.client.place.CaptureDashboardPlace;
import at.tugraz.iaik.nf4droid.client.view.addapp.AddAppView;
import at.tugraz.iaik.nf4droid.shared.dto.AppSuggestion;
import at.tugraz.iaik.nf4droid.shared.dto.AppVersionSuggestion;

import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.SuggestOracle;

public class AddAppActivity extends BaseActivity {

	private AddAppView view;

	public AddAppActivity(final AddAppView view,
			final ClientFactory clientFactory) {
		super(clientFactory);
		this.view = view;
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());

		view.hideMessage();
		view.hideProgress();
		
		initAppPackageSuggestOracle();
		initAppVersionSuggestOracle();

		super.start(panel, eventBus);
	}

	private void initAppPackageSuggestOracle() {
		view.setAppPackageSuggestOracle(new SuggestOracle() {

			@Override
			public boolean isDisplayStringHTML() {
				return true;
			}

			@Override
			public void requestSuggestions(final Request request,
					final Callback callback) {
				GWT.log("Requesting app package suggestions for "
						+ request.getQuery() + ".");
				clientFactory.getGeneralDataService().getAppPackageSuggestions(
						request.getQuery(), new Long(request.getLimit()),
						new AsyncCallback<List<AppSuggestion>>() {

							@Override
							public void onFailure(Throwable caught) {
								GWT.log("Couldn't retrieve app package suggestions!",
										caught);
							}

							@Override
							public void onSuccess(List<AppSuggestion> result) {
								callback.onSuggestionsReady(request,
										new Response(result));
								GWT.log("Received app package suggestions.");
							}
						});
			}
		});
	}

	private void initAppVersionSuggestOracle() {
		view.setAppVersionSuggestOracle(new SuggestOracle() {

			@Override
			public boolean isDisplayStringHTML() {
				return true;
			}

			@Override
			public void requestSuggestions(final Request request,
					final Callback callback) {
				GWT.log("Requesting app version suggestions for "
						+ request.getQuery() + ".");
				clientFactory
						.getGeneralDataService()
						.findAppVersionsWhereAppPackageEqAndVersionCodeLike(
								view.getAppPackageTextBox().getText(),
								request.getQuery(),
								new Long(request.getLimit()),
								new AsyncCallback<List<AppVersionSuggestion>>() {

									@Override
									public void onFailure(Throwable caught) {
										GWT.log("Couldn't retrieve app version suggestions!",
												caught);
									}

									@Override
									public void onSuccess(
											List<AppVersionSuggestion> result) {
										callback.onSuggestionsReady(request,
												new Response(result));
										GWT.log("Received app version suggestions.");
									}
								});
			}
		});
	}

	@Override
	public void bind() {

		registerHandler(view.getUploader().addOnFinishUploadHandler(
				new IUploader.OnFinishUploaderHandler() {

					@Override
					public void onFinish(IUploader uploader) {

						GWT.log("onFinishUpload "
								+ uploader.getStatus().toString());
						
						UploadedInfo info = uploader.getServerInfo();
							if ((info != null) && (info.message != null)) {
								final Long resp = Long.parseLong(info.message);

								if (resp >= 0) {
									view.showMessage(AlertType.INFO,
											"Finished",
											"Successuflly imported traffic capture. Redirecting to capture page...");
									GWT.log("Successuflly imported traffic capture. Redirecting to capture page.");
									// redirect to page for traffic capture
									goTo(new CaptureDashboardPlace(resp));
								} else if (resp == -1) {
									view.showMessage(AlertType.ERROR, "Error",
											"Missing some required fields for import.");
									GWT.log("Missing some required fields for traffic capture import.");
								} else {
									view.showMessage(AlertType.ERROR, "Error",
											"Error during traffic capture import: "
													+ (info == null ? "no info"
															: info.message));
									GWT.log("Error during traffic capture import "
											+ (info == null ? "no info"
													: info.message));
								}

							}
							else {
								view.showMessage(AlertType.ERROR, "Error",
										"Error during import of traffic capture.");
								GWT.log("Error during traffic capture import.");
							}

					}
				}));
	}

	protected void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}
}
