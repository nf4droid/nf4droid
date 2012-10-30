package at.tugraz.iaik.nf4droid.client.view.common.bootstrap;

import java.util.ArrayList;
import java.util.List;

import com.github.gwtbootstrap.client.ui.Nav;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.VerticalDivider;
import com.github.gwtbootstrap.client.ui.constants.IconSize;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class NavWidget extends Nav {

	List<NavLink> navLinks;
	
	public NavWidget() {
		super();
		add(new VerticalDivider());
		navLinks= new ArrayList<NavLink>();
	}

	public void addLink(final String text, final String title, final String targetHistoryToken, final boolean active, final IconType iconType, final IconSize iconSize) {
		final NavLink link = new NavLink();
		link.setText(text);
		link.setTitle(title);
		link.setTargetHistoryToken(targetHistoryToken);
		link.setIcon(iconType);
		link.setIconSize(iconSize);
		
		link.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				makeLinkActive(link);
			}
		});
		
		navLinks.add(link);
		add(link);
		if (active) {
			makeLinkActive(link);
		}
	}
	
	private void makeLinkActive(final NavLink link) {
		for (NavLink linkEntry : navLinks) {
			if (linkEntry != link) {
				linkEntry.setActive(false);
			} else {
				linkEntry.setActive(true);
			}
		}
	}
	
}
