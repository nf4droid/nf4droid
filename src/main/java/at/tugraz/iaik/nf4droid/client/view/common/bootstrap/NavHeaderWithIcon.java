package at.tugraz.iaik.nf4droid.client.view.common.bootstrap;

import com.github.gwtbootstrap.client.ui.Icon;
import com.github.gwtbootstrap.client.ui.base.ListItem;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.github.gwtbootstrap.client.ui.resources.Bootstrap;

public class NavHeaderWithIcon extends ListItem {
	
	private Icon icon;
	private Span span;

	public NavHeaderWithIcon() {
		super();
		
		icon = new Icon();
		super.add(icon);
		
		span = new Span();
		super.add(span);
		
		super.setStyleName(Bootstrap.nav_header);
	}
	
	public NavHeaderWithIcon(final String text) {
		this();
		setText(text);
	}
	
	public NavHeaderWithIcon(final IconType type, final String text) {
		this();
		setText(text);
		setIcon(type);
	}
	
	public void setIcon(final IconType type) {
		icon.setType(type);
	}
	
	public void setText(final String text) {
		span.setText(text);
	}
}
