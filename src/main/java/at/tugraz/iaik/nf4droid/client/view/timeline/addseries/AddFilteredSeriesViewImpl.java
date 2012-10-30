package at.tugraz.iaik.nf4droid.client.view.timeline.addseries;

import at.tugraz.iaik.nf4droid.shared.constant.FilterOperation;
import at.tugraz.iaik.nf4droid.shared.dto.Criteria;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ListBox;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SimplePanel;

public class AddFilteredSeriesViewImpl extends Composite implements
		AddFilteredSeriesView {

	private FlexTable table;
	//private int rowCount = 0;
	//private int columnCount = 0;
	ListBox netLayerType;
	ListBox transLayerType;

	private UiCriteria ipCriterias;

	public AddFilteredSeriesViewImpl() {
		SimplePanel panel = new SimplePanel();

		table = new FlexTable();
		panel.add(table);

		creatNetworkLayerFilter();
		netLayerType.setSelectedIndex(0);

		initWidget(panel);
	}

	private ListBox getConjunctionListBox() {
		ListBox box = new ListBox();
		box.addItem("Match any", FilterOperation.OR.toString());
		box.addItem("Match all", FilterOperation.AND.toString());
		return box;
	}

	private void creatNetworkLayerFilter() {
		final ListBox conjuntionListBox = getConjunctionListBox();
		
		
		netLayerType = getNetworkLayerFilterBox();
		netLayerType.setFocus(true);
		netLayerType.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ListBox source = (ListBox) event.getSource();
				String selectedValue = source.getValue(source
						.getSelectedIndex());
				if (selectedValue.equals("ipv4")) {
					removeTableRows(1);
					ipCriterias = new UiCriteria(conjuntionListBox);
					createIpFilter(ipCriterias);
				} else if (selectedValue.equals("ipv6")) {
					removeTableRows(1);
					ipCriterias = new UiCriteria(conjuntionListBox);
					createIpFilter(ipCriterias);
				} else if (selectedValue.equals("arp")) {
					removeTableRows(1);
				}

			}
		});

		// set default
		netLayerType.setSelectedIndex(0);
		ipCriterias = new UiCriteria(conjuntionListBox);
		createIpFilter(ipCriterias);
		
		table.setWidget(0, 0, netLayerType);
		table.setWidget(0, 1, conjuntionListBox);

	}
	
	private ListBox getNetworkLayerFilterBox() {
		ListBox box = new ListBox();
		box.setFocus(true);
		box.setTitle("Select network layer type");
		box.addItem("IPv4", "ipv4");
		box.addItem("IPv6", "ipv6");
		box.addItem("ARP", "arp");
		return box;
	}
	
	private void removeTableRows(final int startRow) {
		while (table.isCellPresent(startRow, 0)) {
			table.removeRow(startRow);
		}
	}
	
	private void createIpFilter(UiCriteria conjunctionCriteria) {
		addFilterRow(1, 1, conjunctionCriteria);
		addAddFilterRow(2, 1, conjunctionCriteria);
	}

	private void addFilterRow(final int row, final int column, final UiCriteria conjunctionCritera) {
		final Button removeButton = new Button("-");
		final ListBox fieldListBox = getIpFilterListBox();
		final ListBox opListBox = getLogicOperatorListBox();
		final TextBox valueTextBox = new TextBox();

		final UiCriteria criteria = conjunctionCritera.addCriteria(fieldListBox, opListBox, valueTextBox);

		removeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final int row = table.getCellForEvent(event).getRowIndex();
				conjunctionCritera.removeCriteria(criteria);
				table.removeRow(row);
			}
		});

		table.setWidget(row, column, fieldListBox);
		table.setWidget(row, column + 1, opListBox);
		table.setWidget(row, column + 2, valueTextBox);
		table.setWidget(row, column + 3, removeButton);
	}
	
	private void addAddFilterRow(final int row, final int column, final UiCriteria conjunctionCriteria) {
		final Button addButton = new Button("+");
		addButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final int row = table.insertRow(table.getCellForEvent(event)
						.getRowIndex());
				addFilterRow(row, column, conjunctionCriteria);
			}
		});
		table.setWidget(row, column, addButton);
	}

	private ListBox getIpFilterListBox() {
		ListBox box = new ListBox();
		box.addItem("Source Ip", Criteria.IpFields.SOURCE_IP.getValue());
		box.addItem("Dest. Ip", Criteria.IpFields.DEST_IP.getValue());
		box.addItem("Country", Criteria.IpFields.COUNTRY.getValue());
		box.addItem("City", Criteria.IpFields.CITY.getValue());
		return box;
	}

	private ListBox getLogicOperatorListBox() {
		ListBox box = new ListBox();
		box.addItem("LIKE", FilterOperation.LIKE.toString());
		box.addItem("NOT LIKE", FilterOperation.NOT_LIKE.toString()); 
		box.addItem("=", FilterOperation.EQUALS.toString());
		box.addItem("!=", FilterOperation.NOT_EQUALS.toString());
		return box;
	}

	/*
	private void createTransportLayerFilterForIpv4() {
		if (transLayerType != null) {
			table.remove(transLayerType);
		}

		transLayerType = new ListBox();
		transLayerType.setTitle("Select transport layer type");
		transLayerType.addItem("TCP", "tcp");
		transLayerType.addItem("UDP", "udp");
		transLayerType.addItem("ICMP", "icmp");

		table.setWidget(rowCount, columnCount, transLayerType);
		columnCount++;
	}

	private void createTransportLayerFilterForIpv6() {
		if (transLayerType != null) {
			table.remove(transLayerType);
		}

		transLayerType = new ListBox();
		transLayerType.setTitle("Select transport layer type");
		transLayerType.addItem("TCP", "tcp");
		transLayerType.addItem("UDP", "udp");
		transLayerType.addItem("ICMPv6", "icmpv6");

		table.setWidget(rowCount, columnCount, transLayerType);
		columnCount++;
	}
	*/

	/**
	 * @return the ipCriterias
	 */
	public UiCriteria getIpCriterias() {
		return ipCriterias;
	}

}
