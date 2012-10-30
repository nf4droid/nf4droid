package at.tugraz.iaik.nf4droid.client.activity;

import java.util.ArrayList;
import java.util.List;

import at.tugraz.iaik.nf4droid.client.general.factory.ClientFactory;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.HandlerRegistration;

public abstract class BaseActivity extends AbstractActivity {

	protected List<HandlerRegistration> handlerRegistrations;
	protected ClientFactory clientFactory;

	public BaseActivity(final ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
		this.handlerRegistrations = new ArrayList<HandlerRegistration>();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		bind();
	}
	
	@Override
	public void onStop() {
		unbind();
		super.onStop();
	}

	public abstract void bind();

	public void unbind() {

		for (HandlerRegistration reg : handlerRegistrations) {
			reg.removeHandler();
		}
		handlerRegistrations.clear();

	}

	protected void registerHandler(final HandlerRegistration handlerRegistration) {
		handlerRegistrations.add(handlerRegistration);
	}

}
