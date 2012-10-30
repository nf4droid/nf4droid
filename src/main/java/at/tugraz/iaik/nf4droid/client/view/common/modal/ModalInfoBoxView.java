package at.tugraz.iaik.nf4droid.client.view.common.modal;

import com.google.gwt.user.client.ui.IsWidget;

public interface ModalInfoBoxView {

	public void init(final String windowTitle, final IsWidget widget);

	public void hide();
	
	public void show();
}