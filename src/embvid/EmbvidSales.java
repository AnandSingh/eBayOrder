package com.embvid;

/*
 Copyright (c) 2013 eBay, Inc.
 This program is licensed under the terms of the eBay Common Development and
 Distribution License (CDDL) Version 1.0 (the "License") and any subsequent  version 
 thereof released by eBay.  The then-current version of the License can be found 
 at http://www.opensource.org/licenses/cddl1.php and in the eBaySDKLicense file that 
 is under the eBay SDK ../docs directory.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.call.GetMyeBaySellingCall;
import com.ebay.sdk.helper.ui.ControlTagItem;
import com.ebay.sdk.util.eBayUtil;
import com.ebay.soap.eBLBaseComponents.AmountType;
import com.ebay.soap.eBLBaseComponents.ItemListCustomizationType;
import com.ebay.soap.eBLBaseComponents.ItemSortTypeCodeType;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.OrderTransactionType;
import com.ebay.soap.eBLBaseComponents.PaginationType;
import com.ebay.soap.eBLBaseComponents.SellingStatusType;
import com.ebay.soap.eBLBaseComponents.TransactionType;
import com.ebay.soap.eBLBaseComponents.UserType;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class EmbvidSales extends JDialog {

	private ApiContext apiContext = new ApiContext();

	final static int totalColumns = 6;
	final static int totalColumns2 = 5;
	final String[] colNames = new String[] { "ItemID", "Title", "Price",
			"BidCount", "StartTime", "Quantity" };
	final String[] soldListColNames = new String[] { "ItemID", "Title",
			"Price", "EndTime", "Buyer" };
	final String[] scheduledListColNames = new String[] { "ItemID", "Title",
			"StartTime", "StartPrice", "Quantity" };

	final String[] unSoldListColNames = new String[] { "ItemID", "Title",
			"Price", "StartTime", "EndTime", "Quantity" };

	JPanel mainPanel = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();

	JPanel dataPanel = new JPanel();
	JPanel jPanel3 = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();

	JPanel inputPanel = new JPanel();
	JPanel inputPanel2 = new JPanel();
	JPanel jPanel5 = new JPanel();

	JPanel jPanel6 = new JPanel();
	JPanel maxListPanel = new JPanel();
	JPanel itemSortPanel = new JPanel();

	JPanel jPanel8 = new JPanel();
	JPanel jPanel9 = new JPanel();
	JPanel jPanel10 = new JPanel();
	JComboBox cbxItemSort = new JComboBox();
	JLabel jLabel1 = new JLabel();
	JTextField txtMaxPerList = new JTextField();
	JLabel jLabel3 = new JLabel();
	JButton btnGetMyeBay = new JButton();
	JScrollPane jScrollPane1 = new JScrollPane();
	JScrollPane jScrollPane2 = new JScrollPane();
	JScrollPane jScrollPane3 = new JScrollPane();
	JScrollPane jScrollPane4 = new JScrollPane();
	JTable tblActiveList = new JTable();
	JTable tblScheduleList = new JTable();
	JTable tblSoldList = new JTable();
	JTable tblUnSoldList = new JTable();

	BorderLayout borderLayout3 = new BorderLayout();
	JPanel jPanelActiveList = new JPanel();
	JPanel jPanelScheduleList = new JPanel();
	JPanel jPanelSoldList = new JPanel();
	JPanel jPanelUnSoldList = new JPanel();

	BorderLayout borderLayout4 = new BorderLayout();
	JPanel jPanel11 = new JPanel();
	JPanel jPanel12 = new JPanel();
	JLabel jLabel4 = new JLabel();
	JPanel jPanel21 = new JPanel();
	JPanel jPanel22 = new JPanel();

	BorderLayout borderLayout5 = new BorderLayout();
	JPanel jPanel13 = new JPanel();
	JPanel jPanel14 = new JPanel();
	JLabel jLabel5 = new JLabel();
	JPanel jPanel23 = new JPanel();
	JPanel jPanel24 = new JPanel();

	BorderLayout borderLayout6 = new BorderLayout();
	JPanel jPanel15 = new JPanel();
	JPanel jPanel16 = new JPanel();
	JLabel jLabel8 = new JLabel();
	JPanel jPanel25 = new JPanel();
	JPanel jPanel26 = new JPanel();

	BorderLayout borderLayout7 = new BorderLayout();
	JPanel jPanel17 = new JPanel();
	JPanel jPanel18 = new JPanel();
	JLabel jLabel9 = new JLabel();
	JPanel jPanel27 = new JPanel();
	JPanel jPanel28 = new JPanel();

	static Object[] getTagItemArray() {
		Object[] list = new Object[] {
				new ControlTagItem("ItemID Ascending",
						ItemSortTypeCodeType.ITEM_ID),
				new ControlTagItem("ItemID Descending",
						ItemSortTypeCodeType.ITEM_ID_DESCENDING), };
		return list;
	}

	public EmbvidSales(Frame frame, String title, boolean modal) {
		super(frame, title, modal);
		try {
			jbInit();
			pack();

			//
			EmbvidFrame fd = (EmbvidFrame) frame;
			this.apiContext = fd.getApiContext();

			// Initialize combo box.
			ComboBoxModel dataModel = new DefaultComboBoxModel(
					getTagItemArray());
			this.cbxItemSort.setModel(dataModel);
			this.cbxItemSort.setSelectedIndex(1);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public EmbvidSales() {
		this(null, "", false);
	}

	private void jbInit() throws Exception {
		mainPanel.setLayout(borderLayout1);

		jLabel1.setPreferredSize(new Dimension(99, 15));

		jLabel1.setText("Sort  items:");
		cbxItemSort.setMinimumSize(new Dimension(26, 21));
		cbxItemSort.setPreferredSize(new Dimension(150, 21));

		jLabel3.setText("Maximum number of items per list:");
		txtMaxPerList.setPreferredSize(new Dimension(80, 21));
		txtMaxPerList.setSelectionStart(5);

		// jPanelActiveList.setLayout(borderLayout4);
		// jLabel4.setText("Active Items:");

		// jPanelScheduleList.setLayout(borderLayout5);
		// jLabel5.setText("Schedule Items:");

		jPanelSoldList.setLayout(borderLayout6);
		jLabel8.setText("Sold Items:");

		// jPanelUnSoldList.setLayout(borderLayout7);
		// jLabel9.setText("UnSold Items:");

		inputPanel.setLayout(new GridLayout(2, 1));
		inputPanel2.setLayout(new FlowLayout());

		inputPanel2.add(maxListPanel, null);
		inputPanel2.add(itemSortPanel, null);
		maxListPanel.add(jLabel3, null);
		maxListPanel.add(txtMaxPerList, null);

		itemSortPanel.add(jLabel1, null);
		itemSortPanel.add(cbxItemSort, null);

		inputPanel.add(inputPanel2);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(btnGetMyeBay, BorderLayout.CENTER);
		btnGetMyeBay.setText("GetMyeBaySelling");
		btnGetMyeBay
				.addActionListener(new DialogGetMyeBaySelling_btnGetMyeBay_actionAdapter(
						this));
		inputPanel.add(buttonPanel);

		mainPanel.add(inputPanel, BorderLayout.NORTH);
		mainPanel.add(dataPanel, BorderLayout.CENTER);

		jScrollPane2.getViewport().setBackground(Color.white);
		jScrollPane2.setPreferredSize(new Dimension(450, 250));

		jScrollPane1.getViewport().setBackground(Color.white);
		jScrollPane1.setPreferredSize(new Dimension(450, 250));

		jScrollPane3.getViewport().setBackground(Color.white);
		jScrollPane3.setPreferredSize(new java.awt.Dimension(678, 331));

		jScrollPane4.getViewport().setBackground(Color.white);
		jScrollPane4.setPreferredSize(new Dimension(450, 250));

		getContentPane().add(mainPanel);

		dataPanel.setLayout(new GridLayout(2, 2));
		dataPanel.setBorder(BorderFactory.createEtchedBorder());

		// dataPanel.setLayout(new FlowLayout());
		// dataPanel.add(jPanelActiveList);

		// dataPanel.add(jPanelScheduleList);

		dataPanel.add(jLabel8);
		dataPanel.add(jPanelSoldList);

		// dataPanel.add(jPanelUnSoldList);

		/*
		 * jPanelActiveList.add(jPanel11, BorderLayout.NORTH);
		 * jPanel11.add(jLabel4, null); jPanelActiveList.add(jScrollPane1,
		 * BorderLayout.CENTER); jScrollPane1.getViewport().add(tblActiveList,
		 * null);
		 * 
		 * jPanelScheduleList.add(jPanel13, BorderLayout.NORTH);
		 * jPanel13.add(jLabel5, null); jPanelScheduleList.add(jScrollPane2,
		 * BorderLayout.CENTER); jScrollPane2.getViewport().add(tblScheduleList,
		 * null);
		 */

		jPanelSoldList.add(jPanel15, BorderLayout.NORTH);
		jPanelSoldList.add(jScrollPane3, BorderLayout.CENTER);
		jScrollPane3.getViewport().add(tblSoldList, null);

		/*
		 * jPanelUnSoldList.add(jPanel17, BorderLayout.NORTH);
		 * jPanel17.add(jLabel9, null); jPanelUnSoldList.add(jScrollPane4,
		 * BorderLayout.CENTER); jScrollPane4.getViewport().add(tblUnSoldList,
		 * null);
		 */

		this.setSize(692, 419);
	}

	static String[] itemToColumns(ItemType item) {
		String[] cols = new String[totalColumns];
		int i = 0;
		cols[i++] = item.getItemID() != null ? item.getItemID() : "";
		cols[i++] = item.getTitle() != null ? item.getTitle() : "";

		SellingStatusType sst = item.getSellingStatus();
		if (sst != null) {
			AmountType amt = sst.getCurrentPrice();
			cols[i++] = amt != null ? (new Double(amt.getValue()).toString())
					: "";
			cols[i++] = sst.getBidCount() != null ? sst.getBidCount()
					.toString() : "";

		}

		java.util.Calendar startTime = item.getListingDetails() == null ? null
				: item.getListingDetails().getStartTime();
		cols[i++] = startTime != null ? eBayUtil.toAPITimeString(startTime
				.getTime()) : "";

		Integer quantity = item.getQuantity();
		if (quantity != null) {
			cols[i++] = quantity.toString();
		}
		return cols;
	}

	static String[] schItemToColumns(ItemType item) {
		String[] cols = new String[totalColumns2];
		int i = 0;
		cols[i++] = item.getItemID() != null ? item.getItemID() : "";
		cols[i++] = item.getTitle() != null ? item.getTitle() : "";

		java.util.Calendar startTime = item.getListingDetails() == null ? null
				: item.getListingDetails().getStartTime();
		cols[i++] = startTime != null ? eBayUtil.toAPITimeString(startTime
				.getTime()) : "";
		AmountType amt = item.getStartPrice();
		cols[i++] = amt != null ? (new Double(amt.getValue()).toString()) : "";

		Integer quantity = item.getQuantity();
		if (quantity != null) {
			cols[i++] = quantity.toString();
		}
		return cols;

	}

	static String[] unsoldItemToColumns(ItemType item) {
		String[] cols = new String[totalColumns];
		int i = 0;
		cols[i++] = item.getItemID() != null ? item.getItemID() : "";
		cols[i++] = item.getTitle() != null ? item.getTitle() : "";

		AmountType amt = item.getStartPrice();
		cols[i++] = amt != null ? (new Double(amt.getValue()).toString()) : "";

		java.util.Calendar startTime = item.getListingDetails() == null ? null
				: item.getListingDetails().getStartTime();
		cols[i++] = startTime != null ? eBayUtil.toAPITimeString(startTime
				.getTime()) : "";

		java.util.Calendar endTime = item.getListingDetails() == null ? null
				: item.getListingDetails().getEndTime();
		cols[i++] = endTime != null ? eBayUtil.toAPITimeString(endTime
				.getTime()) : "";

		Integer quantity = item.getQuantity();
		if (quantity != null) {
			cols[i++] = quantity.toString();
		}
		return cols;

	}

	static String[] OrderToColumns(OrderTransactionType order) {
		String[] cols = new String[totalColumns2];
		int i = 0;
		if (order.getTransaction() != null) {
			TransactionType transaction = order.getTransaction();
			ItemType item = transaction.getItem();
			if (item != null) {
				cols[i++] = item.getItemID();
				cols[i++] = item.getTitle();
				SellingStatusType sst = item.getSellingStatus();
				if (sst != null)
					cols[i++] = new Double((sst.getCurrentPrice()).getValue())
							.toString();
				cols[i++] = eBayUtil.toAPITimeString(item.getListingDetails()
						.getEndTime().getTime());
			}

			UserType buyer = transaction.getBuyer();
			if (buyer != null)
				cols[i++] = buyer.getUserID();

		}

		return cols;
	}

	void btnGetMyeBay_actionPerformed(ActionEvent e) {
		try {
			GetMyeBaySellingCall api = new GetMyeBaySellingCall(this.apiContext);

			ItemListCustomizationType itemListType = new ItemListCustomizationType();
			itemListType.setInclude(Boolean.TRUE);
			itemListType
					.setSort((ItemSortTypeCodeType) ((ControlTagItem) cbxItemSort
							.getSelectedItem()).Tag);
			// String txt = this.txtMaxPerList.getText();
			// if( txt.length() > 0 ) {
			try {
				PaginationType page = new PaginationType();
				// page.setEntriesPerPage(Integer.valueOf(txt));
				page.setEntriesPerPage(Integer.valueOf(10));
				itemListType.setPagination(page);

			} catch (Exception ex) {
				String msg = ex.getClass().getName() + " : " + ex.getMessage();
				((EmbvidFrame) this.getParent()).showErrorMessage(msg);
			}
			// }

			// ControlTagItem ct;
			// ct = (ControlTagItem)this.cbxActiveSort.getSelectedItem();
			// api.setActiveList(itemListType);
			// api.setScheduledList(itemListType);
			api.setSoldList(itemListType);
			// api.setUnsoldList(itemListType);
			// api.getReturnedSoldList();

			api.getMyeBaySelling();

			/*
			 * // set Active List // ItemType[] tempActiveItems = null; if
			 * (api.getReturnedActiveList()!=null) tempActiveItems=
			 * (api.getReturnedActiveList().getItemArray()).getItem(); final
			 * ItemType[] activeItems = tempActiveItems; // Display active items
			 * in table. TableModel dataModel = new AbstractTableModel() {
			 * public int getColumnCount() { return totalColumns; } public int
			 * getRowCount() { return activeItems == null ? 0 :
			 * activeItems.length;} public String getColumnName(int
			 * columnIndex){ return colNames[columnIndex]; } public Object
			 * getValueAt(int row, int col) { ItemType item = activeItems[row];
			 * return itemToColumns(item)[col]; } };
			 * 
			 * this.tblActiveList.setModel(dataModel); // ItemType[] tempItems =
			 * null; if(api.getReturnedScheduledList()!=null) tempItems=
			 * (api.getReturnedScheduledList().getItemArray()).getItem(); final
			 * ItemType[] scheItems = tempItems; // Display Scheduled items in
			 * table. dataModel = new AbstractTableModel() { public int
			 * getColumnCount() { return totalColumns2; } public int
			 * getRowCount() { return scheItems == null ? 0 : scheItems.length;}
			 * public String getColumnName(int columnIndex){ return
			 * scheduledListColNames[columnIndex]; } public Object
			 * getValueAt(int row, int col) { ItemType item = scheItems[row];
			 * return schItemToColumns(item)[col]; } };
			 * 
			 * this.tblScheduleList.setModel(dataModel);
			 */
			//
			OrderTransactionType[] tempSoldItems = null;
			if (api.getReturnedSoldList() != null)
				tempSoldItems = (api.getReturnedSoldList()
						.getOrderTransactionArray()).getOrderTransaction();
			final OrderTransactionType[] soldItems = tempSoldItems;
			// Display Sold items in table.
			TableModel dataModel = new AbstractTableModel() {
				public int getColumnCount() {
					return totalColumns2;
				}

				public int getRowCount() {
					return soldItems == null ? 0 : soldItems.length;
				}

				public String getColumnName(int columnIndex) {
					return soldListColNames[columnIndex];
				}

				public Object getValueAt(int row, int col) {
					OrderTransactionType orderTrans = soldItems[row];
					return OrderToColumns(orderTrans)[col];
				}
			};

			this.tblSoldList.setModel(dataModel);
			/*
			 * // ItemType[] tempUnSoldItems = null; if
			 * (api.getReturnedUnsoldList()!=null ) tempUnSoldItems =
			 * (api.getReturnedUnsoldList().getItemArray()).getItem(); final
			 * ItemType[] unSoldItems = tempUnSoldItems; // Display unsold items
			 * in table. dataModel = new AbstractTableModel() { public int
			 * getColumnCount() { return totalColumns; } public int
			 * getRowCount() { return unSoldItems == null ? 0 :
			 * unSoldItems.length;} public String getColumnName(int
			 * columnIndex){ return unSoldListColNames[columnIndex]; } public
			 * Object getValueAt(int row, int col) { ItemType item =
			 * unSoldItems[row]; return unsoldItemToColumns(item)[col]; } };
			 * 
			 * this.tblUnSoldList.setModel(dataModel);
			 */
		} catch (Exception ex) {
			String msg = ex.getClass().getName() + " : " + ex.getMessage();
			((EmbvidFrame) this.getParent()).showErrorMessage(msg);
		}
	}
}

class DialogGetMyeBaySelling_btnGetMyeBay_actionAdapter implements
		java.awt.event.ActionListener {
	EmbvidSales adaptee;

	DialogGetMyeBaySelling_btnGetMyeBay_actionAdapter(EmbvidSales adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnGetMyeBay_actionPerformed(e);
	}
}
