package at.tugraz.iaik.nf4droid.client.view.common.bootstrap;

import com.github.gwtbootstrap.client.ui.base.HtmlWidget;

public class Span extends HtmlWidget {

	private static final String tag = "span";

	public Span() {
		super(tag);
	}

	public Span(String text) {
		super(tag, text);
	}

	/**
	 * get Inner Text
	 * 
	 * @return innter Text
	 */
	public String getText() {
		return getElement().getInnerText();
	}

	/**
	 * set Inner Text
	 * 
	 * @param text
	 *            set Text
	 */
	public void setText(String text) {
		getElement().setInnerText(text);

	}

	public void setHtml(String html) {
		getElement().setInnerHTML(html);
	}
}
