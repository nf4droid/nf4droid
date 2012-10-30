package at.tugraz.iaik.nf4droid.shared.dto;

import java.io.Serializable;
import java.util.List;

import at.tugraz.iaik.nf4droid.shared.constant.FilterOperation;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Criteria implements Serializable,  IsSerializable {

	public static enum IpFields {
		DEST_IP("dest_ip"), SOURCE_IP("source_ip"), COUNTRY("country"), CITY("city");
		
		private String fieldName;
		
		private IpFields(final String fieldName) {
			this.fieldName = fieldName;
		}
		
		public String getValue() {
			return fieldName;
		}
		
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FilterOperation operation;
	
	private String fieldName;
	private String value;
	
	private List<Criteria> criterias; 
	
	public Criteria() {
		super();
	}
	
	public Criteria(final String fieldName, final FilterOperation operation, final String value) {
		super();
		this.fieldName = fieldName;
		this.operation = operation;
		this.value = value;
	}
	
	public Criteria(final FilterOperation operation, final List<Criteria> criterias) {
		this.operation = operation;
		this.criterias = criterias;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @return the operation
	 */
	public FilterOperation getOperation() {
		return operation;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @return the criterias
	 */
	public List<Criteria> getCriterias() {
		return criterias;
	}

}
