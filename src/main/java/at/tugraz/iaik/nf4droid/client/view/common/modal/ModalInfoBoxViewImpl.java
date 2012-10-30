package at.tugraz.iaik.nf4droid.client.view.common.modal;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.ModalFooter;
import com.github.gwtbootstrap.client.ui.constants.BackdropType;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ModalInfoBoxViewImpl extends Modal implements ModalInfoBoxView {

	private static class SingletonHolder {
		private static ModalInfoBoxViewImpl instance = new ModalInfoBoxViewImpl();
	}

	public static ModalInfoBoxViewImpl getInstance() {
		return SingletonHolder.instance;
	}
	
	private Button closeButton;
	private VerticalPanel dialogContents;
	
	public ModalInfoBoxViewImpl() {
		super(true);
		this.setStylePrimaryName("modal large");
		this.setBackdrop(BackdropType.NORMAL);
		this.setCloseVisible(true);
		this.setKeyboard(true);

		dialogContents = new VerticalPanel();
		dialogContents.setSpacing(4);
		this.add(dialogContents);

		closeButton = new Button();
		closeButton.setText("Close");
		closeButton.setType(ButtonType.PRIMARY);
		closeButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});

		final ModalFooter footer = new ModalFooter(closeButton);
		this.add(footer);
	}

	@Override
	public void init(final String windowTitle,final IsWidget widget) {
		this.setTitle(windowTitle);
		dialogContents.clear();
		dialogContents.add(widget);
	}
	
}
