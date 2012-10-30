package at.tugraz.iaik.nf4droid.shared.dto;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.mysema.query.annotations.QueryProjection;

public class AppVersionSuggestion implements SuggestOracle.Suggestion, IsSerializable,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer appVersionCode;
	private String appVersionName;
	private String highlight;
	
	
	public AppVersionSuggestion(){
		super();
	}
	
	@QueryProjection
	public AppVersionSuggestion(Integer appVersionCode, String appVersionName) {
		super();
		this.appVersionCode = appVersionCode;
		this.appVersionName = appVersionName;
	}
	
	public AppVersionSuggestion(Integer appVersionCode, String appVersionName,
			String highlight) {
		super();
		this.appVersionCode = appVersionCode;
		this.appVersionName = appVersionName;
		this.highlight = highlight;
	}

	@Override
	public String getDisplayString() {
		return Integer.toString(appVersionCode).replace(highlight, "<strong>"+highlight+"</strong>");
	}

	@Override
	public String getReplacementString() {
		return Integer.toString(appVersionCode);
	}


	public Integer getAppVersionCode() {
		return appVersionCode;
	}

	public String getAppVersionName() {
		return appVersionName;
	}

	/**
	 * @param highlight the highlight to set
	 */
	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	
	
}
