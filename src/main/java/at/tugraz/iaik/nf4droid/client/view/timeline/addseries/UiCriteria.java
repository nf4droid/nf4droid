package at.tugraz.iaik.nf4droid.client.view.timeline.addseries;

import java.util.ArrayList;
import java.util.List;

import at.tugraz.iaik.nf4droid.shared.constant.FilterOperation;
import at.tugraz.iaik.nf4droid.shared.dto.Criteria;

import com.github.gwtbootstrap.client.ui.ListBox;
import com.github.gwtbootstrap.client.ui.TextBox;



public class UiCriteria {

	private ListBox fieldListBox;
	private ListBox operationListBox;
	private TextBox valueTextBox;
	
	private List<UiCriteria> criterias;
	
	public UiCriteria(ListBox operationListBox) {
		super();
		this.operationListBox = operationListBox;
	}

	public UiCriteria(ListBox fieldListBox,
			ListBox operationListBox, TextBox valueTextBox) {
		super();
		this.fieldListBox = fieldListBox;
		this.operationListBox = operationListBox;
		this.valueTextBox = valueTextBox;
	}

	public String getFieldName() {
		return fieldListBox.getValue(fieldListBox.getSelectedIndex());
	}

	public String getOperation() {
		return operationListBox.getValue(operationListBox.getSelectedIndex());
	}

	public String getValue() {
		return valueTextBox.getValue();
	}

	public UiCriteria addCriteria(ListBox fieldListBox,
			ListBox operationListBox, TextBox valueTextBox) {
		if (criterias == null) {
			criterias = new ArrayList<UiCriteria>();
		}
		final UiCriteria criteria = new UiCriteria(fieldListBox, operationListBox, valueTextBox);
		criterias.add(criteria);
		return criteria;
	}
	
	public void addCriteria(UiCriteria criteria) {
		if (criterias == null) {
			criterias = new ArrayList<UiCriteria>();
		}
		criterias.add(criteria);
	}
	
	public void removeCriteria(UiCriteria criteria) {
		if (criterias != null && !criterias.isEmpty()) {
			criterias.remove(criteria);
		}
	}

	public Criteria toCriteria() {
		final Criteria criteria;
		final FilterOperation operation = FilterOperation.convertToFilterOperation(operationListBox.getValue(operationListBox.getSelectedIndex()));
		
		//conjunction criteria
		if (operation == FilterOperation.AND || operation == FilterOperation.OR) {
			List<Criteria> criteriaList = new ArrayList<Criteria>();
			for (UiCriteria uiCriteria : criterias) {
				criteriaList.add(uiCriteria.toCriteria());
			}
			criteria = new Criteria(operation, criteriaList);
		} else {
			final String fieldName = fieldListBox.getValue(fieldListBox.getSelectedIndex());
			final String value = valueTextBox.getValue();
			criteria = new Criteria(fieldName, operation, value);
		}
		
		return criteria;
	}
	
}
