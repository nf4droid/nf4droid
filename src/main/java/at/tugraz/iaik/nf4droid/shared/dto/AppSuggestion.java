package at.tugraz.iaik.nf4droid.shared.dto;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.mysema.query.annotations.QueryProjection;

public class AppSuggestion implements SuggestOracle.Suggestion, IsSerializable,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String appPackage;
	private String appName;
	private String highlight;

	public AppSuggestion() {
		super();
	}

	@QueryProjection
	public AppSuggestion(String appPackage, String appName) {
		super();
		this.appPackage = appPackage;
		this.appName = appName;
	}

	@Override
	public String getDisplayString() {
		return appPackage.replace(highlight, "<strong>" + highlight
				+ "</strong>");
	}

	@Override
	public String getReplacementString() {
		return appPackage;
	}

	/**
	 * @return the appPackage
	 */
	public String getAppPackage() {
		return appPackage;
	}

	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * @param highlight
	 *            the highlight to set
	 */
	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

}