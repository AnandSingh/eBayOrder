/*The MIT License (MIT)

Copyright (c) 2014 Anand Singh

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.*/

package embvid;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.prefs.Preferences;

import javax.crypto.spec.IvParameterSpec;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.ebay.sdk.ApiAccount;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.CallRetry;
import com.ebay.sdk.call.CompleteSaleCall;
import com.ebay.sdk.call.GetOrdersCall;
import com.ebay.sdk.call.LeaveFeedbackCall;
import com.ebay.sdk.helper.ShippingServiceHelper;
import com.ebay.sdk.helper.ui.ControlEntryTypes;
import com.ebay.sdk.helper.ui.ControlTagItem;
import com.ebay.sdk.helper.ui.DialogAccount;
import com.ebay.sdk.helper.ui.GuiUtil;
import com.ebay.sdk.util.SdkProxySelector;
import com.ebay.sdk.util.eBayUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import sun.util.calendar.CalendarDate;

import com.ebay.sdk.util.XmlUtil;
import com.ebay.soap.eBLBaseComponents.AbstractResponseType;
import com.ebay.soap.eBLBaseComponents.AddressType;
import com.ebay.soap.eBLBaseComponents.AmountType;
import com.ebay.soap.eBLBaseComponents.CheckoutStatusCodeType;
import com.ebay.soap.eBLBaseComponents.CommentTypeCodeType;
import com.ebay.soap.eBLBaseComponents.CompleteStatusCodeType;
import com.ebay.soap.eBLBaseComponents.DetailLevelCodeType;
import com.ebay.soap.eBLBaseComponents.ExternalTransactionType;
import com.ebay.soap.eBLBaseComponents.FeedbackDetailType;
import com.ebay.soap.eBLBaseComponents.OrderIDArrayType;
import com.ebay.soap.eBLBaseComponents.OrderStatusCodeType;
import com.ebay.soap.eBLBaseComponents.OrderType;
import com.ebay.soap.eBLBaseComponents.PaginationType;
import com.ebay.soap.eBLBaseComponents.ShipmentTrackingDetailsType;
import com.ebay.soap.eBLBaseComponents.ShipmentType;
import com.ebay.soap.eBLBaseComponents.ShippingCarrierCodeType;
import com.ebay.soap.eBLBaseComponents.ShippingServiceDetailsType;
import com.ebay.soap.eBLBaseComponents.ShippingServiceOptionsType;
import com.ebay.soap.eBLBaseComponents.SiteCodeType;
import com.ebay.soap.eBLBaseComponents.TradingRoleCodeType;
import com.ebay.soap.eBLBaseComponents.TransactionArrayType;
import com.ebay.soap.eBLBaseComponents.TransactionType;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

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
/**
 * <p>
 * Title:Embvid.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Anand Singh
 * @version 1.0
 */
public class EmbvidFrame extends JFrame implements KeyListener,
		ListSelectionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7029657794494759558L;
	// private static final int USE_PANEL_CELL = 0;
	private static final int SOLD_DATE = 0;
	private static final int ORDER_ID = 1;
	private static final int ITEM_TITLE = 2;
	private static final int QTY_TOTAL = 3;
	private static final int BUYER_ID = 4;
	private static final int SHIP_ADDR = 5;
	private static final int TRACK = 6;
	private JTextField jTextField_shipCount;
	private JLabel jLabel10;
	private JLabel jLabel9;
	private JTextField jTextField_feedback;
	private JLabel jLabel8;
	private JLabel jLabel1;
	private JTextField jTextField_FromTime;
	private JLabel jLabel7;
	private JTextField jTextField_ToTime;
	private JLabel jLabel6;
	private JCheckBox jCheckBox;
	private JLabel jLabel5;
	private JLabel jLabel4;
	private JButton jBtnFeebback;

	boolean isTable = false;

	private JButton jBtnUpdate;
	private JLabel jLabel3;

	String db_url = null;
	String db_user = null;
	String db_pw = null;

	int db_maxRow = 0;
	Connection db_con = null;

	String fileName = "";
	private Preferences prefs;

	static byte[] raw = new byte[] { '*', 'T', 'h', 'i', 's', 'I', 's', 'A',
			'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y', '*' };

	static SecureRandom rnd = new SecureRandom();

	// String[] columnNames;
	// int size = 0;
	String[] columnNames = { "Date", "PaisaPay ID", "Item", "Order",
			"Buyer ID", "Shipping Address", "Update Shipping" };
	// Object[] [] dataTable;

	static IvParameterSpec iv = new IvParameterSpec(rnd.generateSeed(16));

	private ApiContext apiContext = new ApiContext();
	public static final String TITLE = "EmbVid eBay Application";
	static final String CONFIG_XML_NAME = "Config.xml";

	long lastMouseClickedTime;

	JPanel contentPane;
	JMenuBar jMenuBar1 = new JMenuBar();
	JMenu jMenuFile = new JMenu();
	JMenuItem jMenuFileExit = new JMenuItem();
	JMenu jMenuHelp = new JMenu();
	JMenuItem jMenuHelpAbout = new JMenuItem();
	JMenuItem jMenuItemAccount = new JMenuItem();
	GridLayout gridLayout1 = new GridLayout();
	BorderLayout borderLayout1 = new BorderLayout();

	// ////////////////////////////////////////////////////////////////////

	// BorderLayout borderLayout1 = new BorderLayout();
	JPanel jPanel0 = new JPanel();
	JPanel jPanel1 = new JPanel();
	JPanel jPanel2 = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();
	// TitledBorder titledBorder1;
	// TitledBorder titledBorder2;
	JButton btnGetOrders = new JButton();
	private JButton jButtonMail;

	BorderLayout borderLayout4 = new BorderLayout();
	JPanel jPanel7 = new JPanel();
	JPanel jPanel9 = new JPanel();
	GridBagLayout gridBagLayout1 = new GridBagLayout();
	JLabel jLabel2 = new JLabel();
	JTextField txtOrderId = new JTextField();
	// JTextField txtStartDate = new JTextField();
	// JTextField txtEndDate = new JTextField();
	BorderLayout borderLayout5 = new BorderLayout();
	JScrollPane jScrollPane1 = new JScrollPane();
	JLabel jLabel20 = new JLabel();
	JTextField txtNumberOfOrders = new JTextField();
	BorderLayout borderLayout7 = new BorderLayout();
	JPanel jPanel10 = new JPanel();
	JPanel jPanel11 = new JPanel();
	JPanel jPanel12 = new JPanel();
	JPanel jPanel13 = new JPanel();
	BorderLayout borderLayout8 = new BorderLayout();
	JPanel jPanel14 = new JPanel();
	JPanel jPanel16 = new JPanel();
	GridBagLayout gridBagLayout2 = new GridBagLayout();

	DefaultListModel lstModel = new DefaultListModel();

	TitledBorder titledBorder1;
	TitledBorder titledBorder2;
	TitledBorder titledBorder3;

	// Construct the frameemaiul
	public EmbvidFrame() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			jbInit();

			// Enable Call-Retry.
			CallRetry cr = new CallRetry();
			cr.setMaximumRetries(3);
			cr.setDelayTime(1000); // Wait for one second between each
									// retry-call.
			String[] apiErrorCodes = new String[] { "10007", // "Internal error to the application."
					"931", // "Validation of the authentication token in API request failed."
					"521", // Test of Call-Retry:
							// "The specified time window is invalid."
					"124" // Test of Call-Retry: "Developer name invalid."
			};
			cr.setTriggerApiErrorCodes(apiErrorCodes);

			// Set trigger exceptions for CallRetry.
			// Build a dummy SdkSoapException so that we can get its Class.
			@SuppressWarnings("rawtypes")
			java.lang.Class[] tcs = new java.lang.Class[] { com.ebay.sdk.SdkSoapException.class };
			cr.setTriggerExceptions(tcs);

			apiContext.setCallRetry(cr);

			apiContext.setTimeout(180000);

			this.loadConfiguration();

			// Add listener for token renewal event.
			apiContext.getApiCredential().addTokenEventListener(
					new EmbvidTokenEventListener(this));
			customInit();

			InitSqlDatabase();
			
			new Thread() {
				public void run() {
					
					//GetOrder();
				}
			}.start();

			new Thread() {
				public void run() {
					System.out.println("Start Update Table thread");
					UpdateGUITable();
				}
			}.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadConfiguration() throws Exception {
		String xmlPath = CONFIG_XML_NAME;
		Document doc = XmlUtil.createDomByPathname(xmlPath);// getConfigXmlText()
		Node config = XmlUtil.getChildByName(doc, "Configuration");
		if (config == null)
			throw new Exception("<Configuration> was not found.");

		String s;

		// this.apiContext = new ApiContext();

		s = XmlUtil.getChildString(config, "ServerUrl").trim();
		this.apiContext.setApiServerUrl(s);

		s = XmlUtil.getChildString(config, "EpsServerUrl").trim();
		this.apiContext.setEpsServerUrl(s);

		s = XmlUtil.getChildString(config, "SignInUrl").trim();
		this.apiContext.setSignInUrl(s);

		ApiCredential apiCred = new ApiCredential();
		this.apiContext.setApiCredential(apiCred);

		this.apiContext.setSite(SiteCodeType.INDIA); // Set site to INDIA

		ApiAccount ac = new ApiAccount();

		//
		Node apiCredential = XmlUtil.getChildByName(config, "ApiCredential");
		s = XmlUtil.getChildString(apiCredential, "Developer");
		ac.setDeveloper(s);
		s = XmlUtil.getChildString(apiCredential, "Application");
		ac.setApplication(s);
		s = XmlUtil.getChildString(apiCredential, "Certificate");
		ac.setCertificate(s);

		Node eBayCredential = XmlUtil.getChildByName(config, "eBayCredential");

		s = XmlUtil.getChildString(eBayCredential, "Token");
		apiCred.seteBayToken(s.trim());

		s = XmlUtil.getChildString(config, "RuName");
		if (s.length() > 0) {
			this.apiContext.setRuName(s);
		}

		s = XmlUtil.getChildString(config, "Timeout");
		if (s.length() > 0) {
			int timeout = Integer.parseInt(s);
			this.apiContext.setTimeout(timeout);
		}

		// proxy setting
		Node proxy = XmlUtil.getChildByName(config, "Proxy");

		if (proxy != null) {
			String host = XmlUtil.getChildString(proxy, "Host");
			String port = XmlUtil.getChildString(proxy, "Port");
			if (host.length() > 0 && port.length() > 0) {
				SdkProxySelector ps = null;
				String username = XmlUtil.getChildString(proxy, "Username");
				String password = XmlUtil.getChildString(proxy, "Password");
				if (username.length() > 0 && password.length() > 0) {
					ps = new SdkProxySelector(host, Integer.parseInt(port),
							username, password);
				} else {
					ps = new SdkProxySelector(host, Integer.parseInt(port));
				}
				ps.register();
			}
		}

	}

	// Component initialization
	private void jbInit() throws Exception {
		contentPane = (JPanel) getContentPane();

		titledBorder1 = new TitledBorder("");
		titledBorder2 = new TitledBorder("");
		titledBorder3 = new TitledBorder("");

		contentPane.setLayout(borderLayout1);
		this.setSize(437, 409);
		setTitle("EmbVid eBay Application");
		jMenuFile.setText("File");
		jMenuFileExit.setText("Exit");
		jMenuFileExit.addActionListener(new Frame_jMenuFileExit_ActionAdapter(
				this));
		jMenuHelp.setText("Help");
		jMenuHelpAbout.setText("About");
		jMenuHelpAbout
				.addActionListener(new Frame_jMenuHelpAbout_ActionAdapter(this));
		jMenuItemAccount.setRolloverEnabled(false);
		jMenuItemAccount.setText("Account");
		jMenuItemAccount
				.addActionListener(new Frame_jMenuItemAccount_actionAdapter(
						this));

		jPanel1.setLayout(gridLayout1);
		gridLayout1.setColumns(1);
		gridLayout1.setRows(2);
		gridLayout1.setVgap(0);

		jPanel2.setBorder(BorderFactory.createEtchedBorder());
		jPanel2.setPreferredSize(new java.awt.Dimension(427, 54));
		jPanel10.setPreferredSize(new Dimension(140, 31));
		jMenuFile.add(jMenuItemAccount);
		jMenuFile.add(jMenuFileExit);
		jMenuHelp.add(jMenuHelpAbout);
		jMenuBar1.add(jMenuFile);
		jMenuBar1.add(jMenuHelp);
		contentPane.add(jPanel1, BorderLayout.NORTH);
		jPanel1.setPreferredSize(new java.awt.Dimension(439, 51));
		jPanel1.add(jPanel10, null);
		contentPane.add(jPanel2, BorderLayout.SOUTH);

		// //////////////////////////////////////

		titledBorder1 = new TitledBorder("");
		titledBorder2 = new TitledBorder("");
		this.setTitle("eBay Embvid - GetOrders");

		jPanel1.setLayout(borderLayout2);

		btnGetOrders.setText("Refresh Orders");
		btnGetOrders.addActionListener(new Frame_btnGetOrders_actionAdapter(
				this));
		jPanel2.setLayout(borderLayout4);
		jPanel7.setLayout(gridBagLayout1);
		jLabel2.setText("        ");

		jPanel9.setLayout(borderLayout5);
		jPanel9.setBorder(null);
		jPanel9.setMaximumSize(new Dimension(32767, 32767));
		jScrollPane1.getViewport().setBackground(Color.white);
		jLabel20.setText("  Number of orders:  ");
		txtNumberOfOrders.setBackground(UIManager
				.getColor("activeCaptionBorder"));
		txtNumberOfOrders.setMinimumSize(new Dimension(60, 21));
		txtNumberOfOrders.setPreferredSize(new java.awt.Dimension(35, 25));

		jPanel0.setLayout(borderLayout7);
		jPanel0.setBorder(BorderFactory.createEtchedBorder());

		jPanel10.setDebugGraphicsOptions(0);
		jPanel10.setPreferredSize(new Dimension(10, 45));
		jPanel10.setRequestFocusEnabled(true);
		jPanel10.setLayout(borderLayout8);
		jPanel14.setBorder(null);
		jPanel14.setPreferredSize(new java.awt.Dimension(683, 45));
		jPanel14.setLayout(gridBagLayout2);
		jPanel7.setMinimumSize(new Dimension(262, 150));
		// cbxNumDays.setMinimumSize(new Dimension(100, 21));
		// cbxNumDays.setPreferredSize(new Dimension(100, 21));
		jPanel1.add(jPanel2, BorderLayout.NORTH);
		jPanel1.add(jPanel0, BorderLayout.CENTER);
		jPanel0.add(jPanel10, BorderLayout.NORTH);
		jPanel14.add(jLabel20, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 0, 0));
		jLabel20.setPreferredSize(new java.awt.Dimension(130, 25));
		jPanel14.add(txtNumberOfOrders, new GridBagConstraints(3, 0, 1, 2, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
		txtNumberOfOrders.setText("   ");
		{
			jLabel6 = new JLabel();
			jPanel14.add(jLabel6, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0), 0, 0));
			jLabel6.setText("       ");
		}
		{
			jTextField_FromTime = new JTextField();
			jPanel14.add(jTextField_FromTime, new GridBagConstraints(6, 0, 1,
					1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			jTextField_FromTime.setBackground(UIManager
					.getColor("activeCaptionBorder"));
			jTextField_FromTime.setMinimumSize(new Dimension(60, 21));
			jTextField_FromTime
					.setPreferredSize(new java.awt.Dimension(145, 25));
			jTextField_FromTime
					.setText("                                                          ");
			jTextField_FromTime.setSize(140, 25);
		}
		{
			jLabel7 = new JLabel();
			jPanel14.add(jLabel7, new GridBagConstraints(8, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0), 0, 0));
			jLabel7.setText(" - ");
		}
		{
			jTextField_ToTime = new JTextField();
			jPanel14.add(jTextField_ToTime, new GridBagConstraints(9, 0, 2, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			jTextField_ToTime.setBackground(UIManager
					.getColor("activeCaptionBorder"));
			jTextField_ToTime.setMinimumSize(new Dimension(60, 21));
			jTextField_ToTime.setPreferredSize(new java.awt.Dimension(145, 25));
		}
		jPanel10.add(jPanel16, BorderLayout.CENTER);
		jPanel10.add(jPanel14, BorderLayout.WEST);
		{
			jBtnUpdate = new JButton();
			jPanel16.add(jBtnUpdate);
			jBtnUpdate.setText("Update Shipping Tracking");
			jBtnUpdate.setPreferredSize(new java.awt.Dimension(175, 28));
			jBtnUpdate
					.addActionListener(new Frame_btnUpdate_actionAdapter(this));
		}
		{
			jLabel4 = new JLabel();
			jPanel16.add(jLabel4);
			jLabel4.setText("             ");
		}
		{
			jBtnFeebback = new JButton();
			jPanel16.add(jBtnFeebback);
			jBtnFeebback.setText("Send Buyer Feedback");
			jBtnFeebback.setPreferredSize(new java.awt.Dimension(150, 29));
			jBtnFeebback.addActionListener(new Frame_btnFeedback_actionAdapter(
					this));
		}
		jPanel0.add(jPanel11, BorderLayout.WEST);
		jPanel0.add(jPanel12, BorderLayout.EAST);
		jPanel0.add(jPanel13, BorderLayout.SOUTH);
		jPanel0.add(jScrollPane1, BorderLayout.CENTER);

		jPanel7.add(jLabel2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 0, 0));
		jPanel7.add(btnGetOrders, new GridBagConstraints(0, 14, 1, 2, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 0, 0));
		{
			jButtonMail = new JButton();
			jPanel7.add(jButtonMail, new GridBagConstraints(2, 14, 1, 2, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0), 0, 0));
			jButtonMail.setText("eMail Order");
			jButtonMail.addActionListener(new Frame_btnSendMail_actionAdapter(
					this));
		}
		{
			jLabel3 = new JLabel();
			jPanel7.add(jLabel3, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0), 0, 0));
			jLabel3.setText("              ");
			jLabel3.setEnabled(false);
		}
		{
			jLabel5 = new JLabel();
			jPanel7.add(jLabel5, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0), 0, 0));
			jLabel5.setText("         ");
		}
		{
			jCheckBox = new JCheckBox();
			jPanel7.add(jCheckBox, new GridBagConstraints(4, 0, 2, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0), 0, 0));
			jCheckBox.setText("Show Items To Be Shipped");
			jCheckBox.setSelected(true);
		}
		{
			jLabel1 = new JLabel();
			jPanel7.add(jLabel1, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0), 0, 0));
			jLabel1.setText("Total Items need to Shipped");
		}
		{
			jLabel8 = new JLabel();
			jPanel7.add(jLabel8, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0), 0, 0));
			jLabel8.setText("Total Feedback need to sent");
		}
		{
			jTextField_feedback = new JTextField();
			jPanel7.add(jTextField_feedback, new GridBagConstraints(2, 7, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			jTextField_feedback.setText("         ");
			jTextField_feedback
					.setPreferredSize(new java.awt.Dimension(34, 22));
		}
		{
			jLabel9 = new JLabel();
			jPanel7.add(jLabel9, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0), 0, 0));
			jLabel9.setText("                ");
		}
		{
			jLabel10 = new JLabel();
			jPanel7.add(jLabel10, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0), 0, 0));
			jLabel10.setText("    ");
		}
		{
			jTextField_shipCount = new JTextField();
			jPanel7.add(jTextField_shipCount, new GridBagConstraints(2, 0, 1,
					1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			jTextField_shipCount.setText("       ");
			jTextField_shipCount
					.setPreferredSize(new java.awt.Dimension(34, 22));
		}
		jPanel2.add(jPanel9, BorderLayout.SOUTH);
		this.getContentPane().add(jPanel1, BorderLayout.CENTER);

		String[] numDays = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", };

		ComboBoxModel dataModel = new DefaultComboBoxModel(numDays);

		jPanel2.setPreferredSize(new java.awt.Dimension(1190, 93));
		jPanel7.setPreferredSize(new java.awt.Dimension(1190, 88));
		jPanel9.setPreferredSize(new Dimension(900, 1));
		jPanel2.add(jPanel7, BorderLayout.NORTH);

		this.setSize(new Dimension(1200, 900));

		setJMenuBar(jMenuBar1);

		// GetOrder(this);
	}

	void customInit() {
		if (!GuiUtil.isApiContextFilled(apiContext, true)) {
			showInfoMessage("Please complete account information first.");
			return;
		}
	}

	public void select() {

	}

	private boolean readyToMakeApiCall() {
		if (!GuiUtil.isApiContextFilled(apiContext, true)) {
			showInfoMessage("Please complete account information first.");
			return false;
		}
		return true;
	}

	// File | Exit action performed
	public void jMenuFileExit_actionPerformed(ActionEvent e) {
		System.exit(0);
	}

	// Help | About action performed
	public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
		EmbvidAboutBox dlg = new EmbvidAboutBox(this);
		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);
	}

	// Overridden so we can exit when window is closed
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			jMenuFileExit_actionPerformed(null);
		}
	}

	private void showAccountDialog() {
		DialogAccount dlg = new DialogAccount(this, apiContext,
				"Config Account", true);
		// dlg.setSignInUrl(signInUrl);

		GuiUtil.CenterComponent(dlg);
		dlg.setVisible(true);

		// if( !dlg.isCancelled() )
		// signInUrl = dlg.getSignInUrl();
	}

	void jMenuItemAccount_actionPerformed(ActionEvent e) {
		showAccountDialog();
	}

	void btnAccount_actionPerformed(ActionEvent e) {
		showAccountDialog();
	}

	public ApiContext getApiContext() {
		return apiContext;
	}

	public void setApiContext(ApiContext apiContext) {
		this.apiContext = apiContext;
	}

	public void showErrorMessage(String error) {
		JOptionPane.showMessageDialog(this, error, TITLE,
				JOptionPane.ERROR_MESSAGE);
	}

	public void showInfoMessage(String error) {
		JOptionPane.showMessageDialog(this, error, TITLE,
				JOptionPane.INFORMATION_MESSAGE);
	}

	void btnSendMail_actionPerformed(ActionEvent e) {

		createHtml();

		String tmp_email = null;
		String tmp_pw = null;
		String smtp_host = null;
		String smtp_port = null;
		String to_email = null;

		try {

			String xmlPath = CONFIG_XML_NAME;
			Document doc = XmlUtil.createDomByPathname(xmlPath);
			Node config = XmlUtil.getChildByName(doc, "Configuration");
			if (config == null) {
				JOptionPane.showMessageDialog(null, "No Config.xml file found",
						"InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			tmp_email = XmlUtil.getChildString(config, "YOUR_EMAIL").trim();

			tmp_pw = XmlUtil.getChildString(config, "YOUR_PASSWORD").trim();

			smtp_host = XmlUtil.getChildString(config, "YOUR_SMTP_HOST").trim();
			smtp_port = XmlUtil.getChildString(config, "YOUR_SMTP_PORT").trim();
			to_email = XmlUtil.getChildString(config, "TO_EMAIL").trim();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Config.xml file ERROR !!",
					"InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		final String email = tmp_email;
		final String pw = tmp_pw;
		// String encrypt = encrypt(pwStr);
		// System.out.println("ecrypted value:" + encrypt);
		// System.out.println("decrypted value:" + (decrypt("ThisIsASecretKey",
		// encrypt)));

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", smtp_host);
		props.put("mail.smtp.port", smtp_port);

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(email, pw);
					}
				});

		try {

			String to_email_arr[] = to_email.split(";");
			InternetAddress[] adress = new InternetAddress[to_email_arr.length];

			for (int i = 0; i < to_email_arr.length; i++) {
				adress[i] = new InternetAddress(to_email_arr[i]);
			}
			// adress[0] = new InternetAddress("admin@embvid.com");
			// adress[1] = new InternetAddress("invincible.arpit@gmail.com");
			// adress[2] = new InternetAddress("anand.krs@gmail.com");

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(Message.RecipientType.TO, adress);
			// message.setRecipients(arg0,
			// arg1)setRecipients(Message.RecipientType.TO,
			// InternetAddress.parse(""));
			message.setSubject("embvid:: Orders From ebay");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart
					.setText("ebay Orders Check Attachment :"
							+ fileName
							+ ".pdf"
							+ "\n\nTesting Attachment \n\n SO IT's WORKING :)  \n\n No spam to my email, please!");
			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			// String filename = "file.txt";
			// DataSource source = new FileDataSource(fileName + ".pdf");
			// messageBodyPart.setDataHandler(new DataHandler(source));
			// messageBodyPart.setFileName(fileName + ".pdf");
			// multipart.addBodyPart(messageBodyPart);

			DataSource source = new FileDataSource(fileName + ".html");
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName + ".html");
			multipart.addBodyPart(messageBodyPart);
			// Send the complete message parts
			message.setContent(multipart);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException me) {
			JOptionPane.showMessageDialog(null,
					"MessagingException : " + me.getMessage(), "Exception: ",
					JOptionPane.ERROR_MESSAGE);
			// throw new RuntimeException(me);
		}
	}

	void btnGetOrders_actionPerformed(ActionEvent e) {
		GetOrder();
	}

	void InitSqlDatabase() {
		// Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			String xmlPath = CONFIG_XML_NAME;
			Document doc = XmlUtil.createDomByPathname(xmlPath);
			Node config = XmlUtil.getChildByName(doc, "Configuration");
			if (config == null) {
				JOptionPane.showMessageDialog(null, "No Config.xml file found",
						"InfoBox: " + "Initilizing SQL Fails  !!",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			db_url = XmlUtil.getChildString(config, "DB_URL").trim();
			db_user = XmlUtil.getChildString(config, "DB_USER").trim();
			;
			db_pw = XmlUtil.getChildString(config, "DB_PASS").trim();
			;

			// String url = "jdbc:mysql://localhost:3306/test1db";
			// String user = "test111";
			// String password = "test222";

			db_con = DriverManager.getConnection(db_url, db_user, db_pw);

			// st = con.createStatement();
			// rs = st.executeQuery("SELECT VERSION()");

			st = db_con.createStatement();
			String sql = "CREATE TABLE if not exists EMBVID_ORDERS5 "
					+ "(ID 			   INT NOT NULL AUTO_INCREMENT,"
					+ " OrderDate       bigint, "
					+ " OrderStatus      varchar(50), "
					+ " OrderID         varchar(255), "
					+ " PaisaPayID      varchar(255), "
					+ " Item		       TEXT     NOT NULL, "
					+ " Quantity        INT      NOT NULL, "
					+ " Price           float    NOT NULL, "
					+ " BuyerID         varchar(255) NOT NULL, "
					+ " BuyerEmail      varchar(255), "
					+ " ShipAddress     TEXT     NOT NULL, "
					+ " ShippingStatus  varchar(50), "
					+ " Feedback        varchar(50), " + " PRIMARY KEY (ID))";
			st.executeUpdate(sql);

			// TODO: get the total roe count in SQL database
			// Statement st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM test1db.EMBVID_ORDERS5");

			rs = st.executeQuery("SELECT COUNT(*) FROM test1db.EMBVID_ORDERS5");

			rs.next();
			db_maxRow = rs.getInt(1);

			st.close();
		} catch (SQLException ex) {

			System.out.println("Exception :\n" + ex.getMessage());
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Exception: "
					+ ex.getMessage(), JOptionPane.INFORMATION_MESSAGE);

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				// if (con != null) {
				// con.close();
				// }

			} catch (SQLException ex) {

				System.out.println("Exception :\n" + ex.getMessage());
				JOptionPane.showMessageDialog(null, ex.getMessage(),
						"Exception: " + ex.getMessage(),
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

	void updateDatabase(OrderDetails orderInfo) {
		try {

			// Connection con = DriverManager.getConnection(db_url, db_user,
			// db_pw);
			Statement db_st = db_con.createStatement();

			String sql = "INSERT INTO test1db.EMBVID_ORDERS5 "
					+ "(OrderDate, OrderStatus, OrderID, PaisaPayID,"
					+ " Item, Quantity, Price, BuyerID, BuyerEmail, ShipAddress,"
					+ " ShippingStatus, FeedBack) " + "SELECT "
					+ orderInfo.OrderDate
					+ ", '"
					+ orderInfo.OrderStatus
					+ "', '"
					+ orderInfo.OrderId
					+ "', '"
					+ orderInfo.PaisaPayID
					+ "', '"
					+ orderInfo.Item
					+ "', "
					+ orderInfo.Quantity
					+ ", "
					+ orderInfo.Price
					+ ", '"
					+ orderInfo.BuyerID
					+ "', '"
					+ orderInfo.BuyerEmail
					+ "', '"
					+ orderInfo.ShipAddress
					+ "', '"
					+ orderInfo.ShippingStatus
					+ "', 'false'"
					+ "FROM dual "
					+ "WHERE NOT EXISTS (SELECT * "
					+ "FROM test1db.EMBVID_ORDERS5 "
					+ "WHERE OrderID = '"
					+ orderInfo.OrderId
					+ "' "
					+ "AND OrderDate = "
					+ orderInfo.OrderDate + ")";

			db_st.executeUpdate(sql);
			db_st.close();
			// con.close();
		} catch (SQLException ex) {

			System.out.println("Exception :\n" + ex.getMessage());
			JOptionPane.showMessageDialog(null, "ex.getMessage()",
					"Exception: " + "", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	void UpdateGUITable() {

		RowFilter<EmbvidPagingModel, Object> rowFilter = new RowFilter<EmbvidPagingModel, Object>() {
			public boolean include(Entry entry) {
				String orderstatus = (String) entry.getValue(0);
				if (orderstatus.contains("INCOMPLETE") == true) {
					// Returning false indicates this row would not be
					// shown.
					return false;
				}
				/*
				 * If check box is slected then show only items to be shipped
				 * TODO: Add an listener to update the whole table if checkbox
				 * is deselected
				 */
				/*if (jCheckBox.isSelected() == true) {
					String shipStatus = (String) entry.getValue(6);
					System.out.println(shipStatus);
					if (shipStatus.contentEquals("NOT-SHIPPED") == false ) {
						System.out.println("----");
						return false;
					}
					System.out.println("++++++");
				}*/
				// Returning true means show the row
				return true;
			}
		};

		EmbvidPagingModel pm = new EmbvidPagingModel();
		JTable jt = new JTable(pm);

		TableRowSorter<EmbvidPagingModel> sorter = new TableRowSorter<EmbvidPagingModel>(
				pm);
		sorter.setRowFilter(rowFilter);
		jt.setRowSorter(sorter);

		jt.setDefaultRenderer(String.class,
				new EmbvidMultiLineTableCellRenderer());
		this.jScrollPane1.setEnabled(false);
		this.jScrollPane1.getViewport().add(
				EmbvidPagingModel.createPagingScrollPaneForTable(jt), null);
		this.jScrollPane1.getViewport().setOpaque(true);
		this.jScrollPane1.getViewport().setVisible(true);
		this.setVisible(true);
		System.out.println("Update Table done --");

	}

	class OrderDetails {
		private long OrderDate;
		private String OrderStatus;
		private String OrderId;
		private String PaisaPayID;
		private String Item;
		private int Quantity;
		private double Price;
		private String BuyerID;
		private String BuyerEmail;
		private String ShipAddress;
		private String ShippingStatus;
		private String Feedback;

		OrderDetails() {
			this.OrderDate = 0;
			this.OrderStatus = null;
			this.OrderId = null;
			this.PaisaPayID = null;
			this.Item = null;
			this.Quantity = 0;
			this.Price = 0;
			this.BuyerID = null;
			this.BuyerEmail = null;
			this.ShipAddress = null;
			this.ShippingStatus = null;
			this.Feedback = "false";
		};

		void setOrderDate(long OrderDate) {
			this.OrderDate = OrderDate;
		}

		void setOrderStatus(String OrderStatus) {
			this.OrderStatus = OrderStatus;
		}

		void setOrderId(String OrderId) {
			this.OrderId = OrderId;
		}

		void setPaisaPayID(String PaisaPayID) {
			this.PaisaPayID = PaisaPayID;
		}

		void setItem(String Item) {
			this.Item = Item;
			
			/*TODO: handle the multiple item brought in single transactions*/
			
			/*if (this.Item == null){
				this.Item = Item;
			}
			this.Item = this.Item + "\n" + Item;*/
		}

		void setQuantity(int Quantity) {
			this.Quantity = Quantity;
		}

		void setPrice(double Price) {
			this.Price = Price;
		}

		void setBuyerID(String BuyerID) {
			this.BuyerID = BuyerID;
		}

		void setBuyerEmail(String BuyerEmail) {
			this.BuyerEmail = BuyerEmail;
		}

		void setShipAddress(String ShipAddress) {
			this.ShipAddress = fixString(ShipAddress);
		}

		void setShippingStatus(String ShippingStatus) {
			this.ShippingStatus = ShippingStatus;
		}

		void setFeedback(String Feedback) {
			this.Feedback = Feedback;
		} /* Feedback is always false at beging */

		private String fixString(String str) {
			String ret = "";
			if (true == str.contains("\'")) {
				ret = str.replaceAll("\'", "\\\\'");
				return ret;
			}
			return str;
		}

		public String toString() {
			StringBuffer retString = new StringBuffer();
			retString.append("Date:").append(String.format("%d", OrderDate));
			retString.append("\nStatus:").append(OrderStatus);
			retString.append("\nOrderId:").append(OrderId);
			retString.append("\nPaisaPayID:").append(PaisaPayID);
			retString.append("\nItem:").append(Item);
			retString.append("\nQuantity:").append(
					String.format("%d", Quantity));
			retString.append("\nPrice:").append(String.format("%f", Price));
			retString.append("\nBuyerID:").append(BuyerID);
			retString.append("\nBuyerEmail:").append(BuyerEmail);
			retString.append("\nShipAddress:").append(ShipAddress);
			retString.append("\nShippingStatus:").append(ShippingStatus);
			retString.append("\nFeedback:").append(Feedback);
			return retString.toString();

		}

	};

	void GetOrder() {

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

			DetailLevelCodeType[] detailLevels = new DetailLevelCodeType[] {
					DetailLevelCodeType.RETURN_ALL,
					DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES,
					DetailLevelCodeType.ITEM_RETURN_DESCRIPTION };

			GetOrdersCall api = new GetOrdersCall(this.apiContext);
			api.setDetailLevel(detailLevels);

			new GregorianCalendar(TimeZone.getTimeZone("GMT"));
			Calendar to = GregorianCalendar.getInstance();

			// create a Preferences instance (somewhere later in the code)
			prefs = Preferences.userNodeForPackage(this.getClass());
			long lastFetchTime = prefs.getLong("LAST_ORDER_TIME", 0);
			System.out.println("Last fetch time : " + lastFetchTime);
			if (lastFetchTime == 0) {
				Calendar from = (GregorianCalendar) to.clone();
				from.add(Calendar.DAY_OF_YEAR, -30);
				// from.se, month, date)
				from.clear(Calendar.HOUR);
				from.clear(Calendar.HOUR_OF_DAY);
				from.clear(Calendar.MINUTE);// = 0;//, -);
				from.clear(Calendar.SECOND);// , 0);
				System.out.println("First Fectch time: "
						+ from.getTimeInMillis());
				api.setCreateTimeFrom(from);
				jTextField_FromTime.setText(formatter.format(from.getTime()));
				System.out.println("From:" + formatter.format(from.getTime())
						+ " to:" + formatter.format(to.getTime()));
			} else {

				Calendar from = GregorianCalendar.getInstance();
				from.setTimeInMillis(lastFetchTime);
				api.setCreateTimeFrom(from);
				jTextField_FromTime.setText(formatter.format(from.getTime()));
				System.out.println("From:" + formatter.format(from.getTime())
						+ " to:" + formatter.format(to.getTime()));
			}

			api.setCreateTimeTo(to);

			jTextField_ToTime.setText(formatter.format(to.getTime()));
			/* append the time to the output file */
			File dir = new File(".");
			String path = dir.getCanonicalPath();
			fileName = path + "//out//Orders_" + to.getTime().toString();

			// api.setOrderRole(role);
			api.setOrderRole(TradingRoleCodeType.SELLER);
			api.setOrderStatus(OrderStatusCodeType.ALL);

			// PaginationType pagination = new PaginationType();
			// pagination.setEntriesPerPage(50);

			// api.setPagination(pagination);

			int pageNumber = 0;
			String temp;

			// do {
			System.out
					.println("-------------------------------------------------");
			System.out.println("Calling getOrders");
			// System.out.println("Requesting Page : " + ++pageNumber +
			// " ....."+api.getReturnedPageNumber());

			OrderType[] orders = api.getOrders();

			System.out.println("Requesting Page : " + ++pageNumber + " ....."
					+ api.getReturnedPageNumber());
			System.out.println("actual orders: "
					+ api.getReturnedReturnedOrderCountActual());

			int size = orders != null ? orders.length : 0;

			if (api.getReturnedReturnedOrderCountActual() > 0) {
				int orderCounter = 0;
				for (OrderType order : orders) {
					System.out
							.println("-------------------------------------------");
					System.out.println("Order "
							+ ++orderCounter
							+ " of "
							+ api.getReturnedPaginationResult()
									.getTotalNumberOfEntries());
					/*
					 * ShippingServiceOptionsType shipping =
					 * order.getShippingServiceSelected(); if (shipping == null)
					 * { if (order.getCheckoutStatus().getStatus() ==
					 * CompleteStatusCodeType.COMPLETE) { shipping =
					 * order.getShippingDetails().getShippingServiceOptions(0);
					 * } else { shipping = new ShippingServiceOptionsType(); } }
					 */

					OrderDetails orderInfo = new OrderDetails();

					/*** Store Order ID ***/
					orderInfo.setOrderId(order.getOrderID());
					orderInfo.setBuyerID(order.getBuyerUserID());

					TransactionArrayType transactionArray = order
							.getTransactionArray();
					TransactionType[] transactions = transactionArray
							.getTransaction();

					int transactionCounter = 0;
					for (TransactionType transaction : transactions) {
						int qty = 0;
						// dataTable[rowIdx][ORDER_ID] = order.getOrderID();
						System.out.println("Order " + orderCounter + " of"
								+ "Transaction " + ++transactionCounter);

						/*** Store Buyer ID ***/
						orderInfo.setBuyerEmail(transaction.getBuyer()
								.getEmail());

						/*** Store Shipment Status ***/
						orderInfo.setItem(transaction.getItem().getTitle());

						if (order.getShippedTime() != null) {
							orderInfo.setShippingStatus("SHIPPED");
						} else {
							orderInfo.setShippingStatus("NOT-SHIPPED");
						}

						/*** Store Quantity ***/
						qty = qty
								+ transaction.getQuantityPurchased().intValue();
						orderInfo.setQuantity(qty);

						/*** Store Order Status ***/
						if (order.getCheckoutStatus().getStatus() == CompleteStatusCodeType.COMPLETE) {
							orderInfo.setOrderStatus("COMPLETE");
						} else {
							orderInfo.setOrderStatus("INCOMPLETE");
						}

						/*** Store Order Date ***/
						orderInfo.setOrderDate(transaction.getCreatedDate()
								.getTimeInMillis());

						/*** Store Paisa Pay ID ***/
						ExternalTransactionType[] extTrancType = order
								.getExternalTransaction();
						StringBuilder paisaPayId = new StringBuilder();
						if (order.getExternalTransactionLength() == 1) {
							paisaPayId.append(extTrancType[0]
									.getExternalTransactionID());
						} else if (order.getExternalTransactionLength() > 1) {
							for (int i = 0; i < order
									.getExternalTransactionLength(); i++) {
								paisaPayId.append(
										extTrancType[i]
												.getExternalTransactionID())
										.append(",");
							}
						}
						orderInfo.setPaisaPayID(paisaPayId.toString());

						/*** Store Payment Method ***/
						orderInfo.setPrice(order.getAmountPaid().getValue());

						/*** Parse and store buyer Adddress details ***/
						AddressType shippingAddress = order
								.getShippingAddress();

						StringBuffer ShipAddressBuff = new StringBuffer();
						temp = shippingAddress.getName();
						if (temp.length() > 0)
							ShipAddressBuff.append(temp);

						if (shippingAddress.getStreet() != null) {
							temp = shippingAddress.getStreet();
							if (temp.length() > 0) {
								ShipAddressBuff.append("\n").append(temp);

							}
						}
						if (shippingAddress.getStreet1() != null) {
							temp = shippingAddress.getStreet1();
							if (temp.length() > 0) {
								ShipAddressBuff.append("\n").append(temp);
							}
						}
						if (shippingAddress.getStreet2() != null) {
							temp = shippingAddress.getStreet2();
							if (temp.length() > 0) {
								ShipAddressBuff.append("\n").append(temp);
							}
						}

						if (shippingAddress.getCityName() != null) {
							temp = shippingAddress.getCityName();
							if (temp.length() > 0) {
								ShipAddressBuff.append("\n").append(temp);
							}
						}
						if (shippingAddress.getPostalCode() != null) {
							temp = shippingAddress.getPostalCode();
							ShipAddressBuff.append(" - ").append(temp);

						}
						if (shippingAddress.getStateOrProvince() != null) {
							String stateCode = shippingAddress
									.getStateOrProvince();
							String State = getStateName(stateCode);
							ShipAddressBuff.append("\n").append(State);
						}
						/*
						 * if (shippingAddress.getCountryName() != null) { temp
						 * = shippingAddress.getCountryName();
						 * ShipAddressBuff.append(" - ").append(temp); }
						 */
						if (shippingAddress.getPhone() != null) {
							temp = shippingAddress.getPhone();
							ShipAddressBuff.append("\nTel: ").append(temp);
						}
						orderInfo.setShipAddress(ShipAddressBuff.toString());

						System.out
								.println("---------------------------------------");
					}
					updateDatabase(orderInfo);
				}
			} else {
				System.out
						.println("There are no orders for the user within the specified filter. Please change the NumberOfDays/FromDate+ToDate filter");
			}
			System.out.println("More Orders : "
					+ api.getReturnedHasMoreOrders());
			// } while (api.getReturnedHasMoreOrders());
			System.out.println("---------------------------------------");

			// Store the last time fetch time
			System.out.println("Update Last Fetch time: "
					+ to.getTimeInMillis());
			prefs.putLong("LAST_ORDER_TIME", to.getTimeInMillis());

			UpdateShippedOrder();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());

			JOptionPane.showMessageDialog(null, "Exception: "
					+ ex.getMessage().toString(), "ERROR !!",
					JOptionPane.INFORMATION_MESSAGE);
		} finally {
			this.setCursor(Cursor.getDefaultCursor());
		}
	}

	void UpdateShippedOrder() {
		try {
			Statement db_st = db_con.createStatement();

			String sql = "SELECT OrderID, OrderDate "
					+ "FROM test1db.EMBVID_ORDERS5 "
					+ "WHERE OrderStatus='COMPLETE' AND ShippingStatus='NOT-SHIPPED'";

			ResultSet rs = db_st.executeQuery(sql);

			ArrayList<String> lstOrders = new ArrayList<String>();

			while (rs.next()) {
				try {
					lstOrders.add(rs.getString("OrderID"));
				} catch (Exception ex) {
					((EmbvidFrame) this.getParent()).showErrorMessage(ex
							.getMessage());
				}

			}
			rs.close();

			int needToShipCount = 0;

			int size = lstOrders.size();
			// Store the order in string form
			String[] orderIds = new String[size];
			for (int i = 0; i < size; i++) {
				orderIds[i] = lstOrders.get(i).toString();
			}
			DetailLevelCodeType[] detailLevels = new DetailLevelCodeType[] {
					DetailLevelCodeType.RETURN_ALL,
					DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES,
					DetailLevelCodeType.ITEM_RETURN_DESCRIPTION };

			GetOrdersCall api = new GetOrdersCall(this.apiContext);
			api.setDetailLevel(detailLevels);

			OrderIDArrayType oiat = new OrderIDArrayType();
			oiat.setOrderID(orderIds);
			api.setOrderIDArray(oiat);

			// api.setOrderStatus(status);
			// api.setOrderRole(role);
			api.setOrderStatus(OrderStatusCodeType.COMPLETED);
			api.setOrderRole(TradingRoleCodeType.SELLER);

			OrderType[] orders = api.getOrders();
			for (OrderType order : orders) {
				String orderID = order.getOrderID();

				System.out.println("Order " + orderID + "\n" + "Buyer "
						+ order.getBuyerUserID() + "\n" + "Title ");

				if (order.getShippedTime() != null) {
					System.out.println(">>> SHIPPED\n");
					/*** Update the database ***/
					sql = "UPDATE test1db.EMBVID_ORDERS5 SET ShippingStatus='SHIPPED' WHERE EMBVID_ORDERS5.OrderID='"
							+ orderID + "'";

					db_st.executeUpdate(sql);

				} else {
					System.out.println("<<< TO BE SHIPPED !!\n");
					needToShipCount++;

				}
				TransactionArrayType transactionArray = order
						.getTransactionArray();
				TransactionType[] transactions = transactionArray
						.getTransaction();
				for (TransactionType transaction : transactions) {
					System.out.println("Order Price : "
							+ transaction.getTransactionPrice()
							+ " CheckOut Status : "
							+ order.getCheckoutStatus().getStatus()

							+ " PaymentStatus : "
							+ order.getCheckoutStatus().getEBayPaymentStatus());
				}
			}
			jTextField_shipCount.setText(String.format("%d", needToShipCount));

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Exception: "
					+ ex.getMessage().toString(), "ERROR !!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	String getStateName(String code) {
		String state[][] = { { "Andhra Pradesh", "AP" },
				{ "Arunachal Pradesh ", "AR" }, { "Assam", "AS" },
				{ "Bihar", "BR" }, { "Chhattisgarh", "CG" }, { "Goa", "GA" },
				{ "Gujarat", "GJ" }, { "Haryana", "HR" },
				{ "Himachal Pradesh", "HP" }, { "Jammu & Kashmir", "JK" },
				{ "Jharkhand", "JH" }, { "Karnataka", "KA" },
				{ "Kerala", "KL" }, { "Madhya Pradesh", "MP" },
				{ "Maharashtra", "MH" }, { "Manipur", "MN" },
				{ "Meghalaya", "ML" }, { "Mizoram", "MZ" },
				{ "Nagaland", "NL" }, { "Odisha", "OR" }, { "Punjab", "PB" },
				{ "Rajasthan", "RJ" }, { "Sikkim", "SK" },
				{ "Tamil Nadu", "TN" }, { "Tripura", "TR" },
				{ "Uttarakhand", "UT" }, { "Uttar Pradesh", "UP" },
				{ "West Bengal", "WB" },

				{ "Andaman & Nicobar", "AN" }, { "Chandigarh", "CH" },
				{ "Dadra & Nagar Haveli", "DN" }, { "Daman & Diu", "DD" },
				{ "Delhi", "DL" }, { "Lakshadweep", "LD" },
				{ "Puducherry", "PY" } };
		for (int i = 0; i < state.length; i++) {
			if (state[i][1].equals(code)) {
				return state[i][0];
			}
		}

		return code;
	}

	String wrapAddress(String addr) {
		String retAddr = addr.replaceAll("\n", "&nbsp;&nbsp;<br>&nbsp;&nbsp;");
		retAddr = "&nbsp;&nbsp;" + retAddr;
		return retAddr;
	}

	String wrapTitle(String title) {
		String retTitle = title;
		StringBuilder builder = new StringBuilder();
		if (title.length() > 40) {
			for (int i = 0; i < 20; i++) {
				builder.append(title.charAt(i));
			}
			builder.append("...");
			int rem = title.length() - 20;
			for (int i = rem; i < title.length(); i++) {
				builder.append(title.charAt(i));
			}
			retTitle = builder.toString();
		}
		return retTitle;

	}

	void createHtml() {

		try {
			Statement db_st = db_con.createStatement();

			String sql = "SELECT ShipAddress, Item "
					+ "FROM test1db.EMBVID_ORDERS5 "
					+ "WHERE OrderStatus='COMPLETE' AND ShippingStatus='NOT-SHIPPED'";

			ResultSet rs = db_st.executeQuery(sql);

			/** Write to HTML ***/
			String htmlHeader = "<!DOCTYPE html><html>\n";
			String htmlHeadS = "<head>\n";
			String htmlMeta = "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">";
			String htmlHeadE = "</head>\n";
			String htmlTitle = "<title> Orders.html </title>\n";
			String htmlBodyS = "<body>\n";
			String htmlBodyE = "</body>\n";
			String htmlHeaderE = "</html>\n";

			File fileHtml = new File(fileName + ".html");

			// if file doesnt exists, then create it
			if (!fileHtml.exists()) {
				fileHtml.createNewFile();
			}

			FileWriter fwHtml = new FileWriter(fileHtml.getAbsoluteFile());
			BufferedWriter bwHtml = new BufferedWriter(fwHtml);
			bwHtml.write(htmlHeader);
			bwHtml.write(htmlHeadS);
			bwHtml.write(htmlMeta);
			bwHtml.write(htmlTitle);
			bwHtml.write(htmlHeadE);

			bwHtml.write(htmlBodyS);

			while (rs.next()) {
				String title = wrapTitle(rs.getString("Item"));
				String addressdata = wrapAddress(rs.getString("ShipAddress"));

				// System.out.println("test "+j);

				// System.out.println(tempTitle + "  .  " +Title);
				bwHtml.write("<p>\n");
				bwHtml.write("&nbsp;<u>" + title + "</u><br>");
				bwHtml.write("<table border=\"1\">\n<tr>\n");
				bwHtml.write("<td>" + addressdata + "</td>");
				bwHtml.write("</tr></table>\n");
				bwHtml.write("</p><br>\n");
			}
			bwHtml.write(htmlBodyE);

			bwHtml.write(htmlHeaderE);

			bwHtml.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Exception: "
					+ ex.getMessage().toString(), "ERROR !!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	void btnFeedback_actionPerformed(ActionEvent e) {
				
			EmbvidSendFeedbackDialog dlg = new EmbvidSendFeedbackDialog(this, "eBay SDK for Java - LeaveFeedback", true);
		    GuiUtil.CenterComponent(dlg);
		    dlg.setVisible(true);
	}

	void btnUpdate_actionPerformed(ActionEvent e) {
		try {

			if (table == null) {
				JOptionPane.showMessageDialog(this, "No Table Found !!");
			}
			table.getCellEditor().stopCellEditing();
			if (table.getModel().getRowCount() > 0) {
				// loadConfiguration();
				int rowcount = table.getModel().getRowCount();
				table.setOpaque(true);

				// Get the shipment text which need to be updated
				String xmlPath = CONFIG_XML_NAME;
				Document doc = XmlUtil.createDomByPathname(xmlPath);
				Node config = XmlUtil.getChildByName(doc, "Configuration");
				if (config == null) {
					JOptionPane.showMessageDialog(null,
							"No Config.xml file found", "InfoBox: "
									+ "Update Feedback !!",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				String shipRemarks = XmlUtil.getChildString(config,
						"ShipmentRemark").trim();

				// String[] trackNum = new String[rowcount];
				for (int i = 0; i < rowcount; i++) {
					String TarckingNum = (String) table.getModel().getValueAt(
							i, 6);
					if (TarckingNum.equals("TO-BE-SHIPPED") == false) {
						if (TarckingNum.equals("SHIPPED") == true)
							continue;
						// trackNum[idx] = ""+TarckingNum;
						// System.out.println(trackNum[idx++]);
						String OrderId = (String) table.getModel().getValueAt(
								i, 1);

						String[] parts = OrderId.split("-");
						String ItemId = parts[0].trim(); // 004
						String TransactionId = parts[1].trim(); // 034556

						CompleteSaleCall completeSaleApi = new CompleteSaleCall(
								this.apiContext);

						// completeSaleApi.setOrderLineItemID(OrderId);
						completeSaleApi.setItemID(ItemId);
						completeSaleApi.setTransactionID(TransactionId);

						completeSaleApi.setShipped(true);

						ShipmentTrackingDetailsType shpmnt = new ShipmentTrackingDetailsType();
						shpmnt.setShipmentTrackingNumber(TarckingNum);

						// ShippingCarrierCodeType[] shippingCarrier =
						// shpmnt.getShippingCarrierUsed();

						shpmnt.setShippingCarrierUsed(ShippingCarrierCodeType.OTHER
								.toString());

						new GregorianCalendar(TimeZone.getTimeZone("GMT"));
						Calendar to = GregorianCalendar.getInstance();
						ShipmentType shipType = new ShipmentType();
						// shipType.setShippedTime(to);

						shipType.setShipmentTrackingDetails(new ShipmentTrackingDetailsType[] { shpmnt });
						// shipType.setNotes(shipRemarks);
						completeSaleApi.setShipment(shipType);

						try {
							completeSaleApi.completeSale();
							AbstractResponseType resp = completeSaleApi
									.getResponseObject();
							Date dt = resp.getTimestamp().getTime();
							System.out.println("Response : "
									+ resp.getAck().value() + " Time: "
									+ eBayUtil.toAPITimeString(dt));

						} catch (Exception ex) {

							JOptionPane
									.showMessageDialog(null, "Update fail:  "
											+ TarckingNum + "\n("
											+ ex.getMessage().toString() + ")",
											"Fail !! ",
											JOptionPane.INFORMATION_MESSAGE);
						}

						// shipmnt.set
						System.out
								.println("------------------------------------------------------------------");
						System.out.println(OrderId);
						System.out.println("Set Tracking number :"
								+ TarckingNum + " for " + ItemId + "-"
								+ TransactionId);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "No Table Found !!");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "No Table Found !!");
		}
	}

	// KeyListner interfaces
	public void keyTyped(KeyEvent evt) {
	}

	public void keyPressed(KeyEvent evt) {

	}

	public void keyReleased(KeyEvent evt) {
	}

	// ListSelectionListner interfaces
	public void valueChanged(ListSelectionEvent e) {
		// String api = lstCommands.getSelectedValue().toString();
		// btnRun.setText(api);
	}

	// MouseListener interfaces
	/**
	 * Invoked when the mouse button has been clicked (pressed and released) on
	 * a component.
	 */
	public void mouseClicked(MouseEvent e) {

	}

	/**
	 * Invoked when a mouse button has been pressed on a component.
	 */
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * Invoked when a mouse button has been released on a component.
	 */
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Invoked when the mouse enters a component.
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Invoked when the mouse exits a component.
	 */
	public void mouseExited(MouseEvent e) {
	}
}

// Classes to handle the menu selection/button press events.
class Frame_jMenuFileExit_ActionAdapter implements ActionListener {
	EmbvidFrame adaptee;

	Frame_jMenuFileExit_ActionAdapter(EmbvidFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jMenuFileExit_actionPerformed(e);
	}
}

class Frame_jMenuHelpAbout_ActionAdapter implements ActionListener {
	EmbvidFrame adaptee;

	Frame_jMenuHelpAbout_ActionAdapter(EmbvidFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jMenuHelpAbout_actionPerformed(e);
	}
}

class Frame_jMenuItemAccount_actionAdapter implements
		java.awt.event.ActionListener {
	EmbvidFrame adaptee;

	Frame_jMenuItemAccount_actionAdapter(EmbvidFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jMenuItemAccount_actionPerformed(e);
	}
}

class Frame_btnSendMail_actionAdapter implements java.awt.event.ActionListener {
	EmbvidFrame adaptee;

	Frame_btnSendMail_actionAdapter(EmbvidFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnSendMail_actionPerformed(e);
	}
}

class Frame_btnGetOrders_actionAdapter implements java.awt.event.ActionListener {
	EmbvidFrame adaptee;

	Frame_btnGetOrders_actionAdapter(EmbvidFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnGetOrders_actionPerformed(e);
	}
}

class Frame_btnUpdate_actionAdapter implements java.awt.event.ActionListener {
	EmbvidFrame adaptee;

	Frame_btnUpdate_actionAdapter(EmbvidFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnUpdate_actionPerformed(e);
	}
}

class Frame_btnFeedback_actionAdapter implements java.awt.event.ActionListener {
	EmbvidFrame adaptee;

	Frame_btnFeedback_actionAdapter(EmbvidFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnFeedback_actionPerformed(e);
	}
}
