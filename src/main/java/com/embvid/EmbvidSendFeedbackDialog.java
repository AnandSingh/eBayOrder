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

package com.embvid;

/**
 * <p>
 * Title: EmbvidDensFeedBackDialog.Java
 * </p>
 * <p>
 * Description: A Java Application to process orders of ebay.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Anand Singh
 * @version 1.0
 */
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.TimeZone;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.SdkException;
import com.ebay.sdk.call.AddToItemDescriptionCall;
import com.ebay.sdk.call.LeaveFeedbackCall;
import com.ebay.sdk.util.XmlUtil;
import com.ebay.sdk.util.eBayUtil;
import com.ebay.soap.eBLBaseComponents.AbstractResponseType;
import com.ebay.soap.eBLBaseComponents.CommentTypeCodeType;
import com.ebay.soap.eBLBaseComponents.FeedbackDetailType;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class EmbvidSendFeedbackDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ApiContext apiContext = new ApiContext();

	final String CONFIG_XML_NAME = "Config.xml";
	JPanel panel1 = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	JPanel jPanel1 = new JPanel();
	JPanel jPanel3 = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();
	JPanel jPanel4 = new JPanel();
	JPanel jPanel5 = new JPanel();
	JButton btnCallAddToItemDescription = new JButton();
	JPanel jPanel6 = new JPanel();
	JPanel jPanel7 = new JPanel();
	JLabel jLabel1 = new JLabel();
	JTextPane txtDescriptionToAppend = new JTextPane();
	BorderLayout borderLayout3 = new BorderLayout();
	// private JTextArea jTextArea_LogMsg;
	// private JTextPane jTextPane1_log;
	protected JTextArea jTextPane1_log;

	public EmbvidSendFeedbackDialog(Frame frame, String title, boolean modal) {
		super(frame, title, modal);
		try {
			jbInit();

			EmbvidFrame fd = (EmbvidFrame) frame;
			this.apiContext = fd.getApiContext();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public EmbvidSendFeedbackDialog() {
		this(null, "", false);
	}

	private void jbInit() throws Exception {
		panel1.setLayout(borderLayout1);
		jPanel1.setLayout(borderLayout2);
		btnCallAddToItemDescription.setText("Leave Feedback");
		btnCallAddToItemDescription
				.addActionListener(new EmbvidSendFeedbackDialog_btnCallAddToItemDescription_actionAdapter(
						this));
		jLabel1.setPreferredSize(new java.awt.Dimension(128, 15));
		jLabel1.setText("Feedback Message");
		txtDescriptionToAppend
				.setPreferredSize(new java.awt.Dimension(457, 37));
		jPanel4.setLayout(borderLayout3);
		jPanel5.setPreferredSize(new Dimension(149, 40));
		jPanel6.setMinimumSize(new Dimension(52, 31));
		jPanel6.setPreferredSize(new java.awt.Dimension(354, 23));
		panel1.add(jPanel1, BorderLayout.NORTH);
		panel1.add(jPanel3, BorderLayout.SOUTH);
		jPanel3.setPreferredSize(new java.awt.Dimension(402, 169));
		{

			jTextPane1_log = new JTextArea();
			jTextPane1_log.setSize(new Dimension(381, 147));

			jTextPane1_log.setLineWrap(true);
			jTextPane1_log.setEditable(false);
			jTextPane1_log.setVisible(true);
			// jTextPane1_log.setPreferredSize(new java.awt.Dimension(119,
			// 156));

			JScrollPane scroll = new JScrollPane(jTextPane1_log);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			// scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			jPanel3.add(scroll, BorderLayout.CENTER);
			scroll.setPreferredSize(new java.awt.Dimension(466, 159));
			// scroll.setPreferredSize(new java.awt.Dimension(397, 244));

		}
		jPanel1.add(jPanel4, BorderLayout.NORTH);
		jPanel4.add(jPanel6, BorderLayout.NORTH);
		jPanel6.add(jLabel1, null);
		jPanel4.add(jPanel7, BorderLayout.CENTER);
		jPanel7.add(txtDescriptionToAppend);
		jPanel1.add(jPanel5, BorderLayout.CENTER);
		jPanel5.add(btnCallAddToItemDescription);
		btnCallAddToItemDescription.setPreferredSize(new java.awt.Dimension(
				125, 30));

		this.setSize(488, 317);
		this.setResizable(false);
		getContentPane().add(panel1, BorderLayout.NORTH);

		updateFeeddbackMsg();

	}

	void updateFeeddbackMsg() {
		try {

			String xmlPath = CONFIG_XML_NAME;
			Document doc = XmlUtil.createDomByPathname(xmlPath);
			Node config = XmlUtil.getChildByName(doc, "Configuration");
			if (config == null) {
				JOptionPane.showMessageDialog(null, "No Config.xml file found",
						"InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			String feedbacktxt = XmlUtil.getChildString(config, "FeedbackText")
					.trim();
			txtDescriptionToAppend.setText(feedbacktxt);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Config.xml file ERROR !!",
					"InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}
	
	/*
	 * Send an mail to buyer once feedback is updated 
	 */
	void SendMail(String BuyerId, String BuyerEmail)
	{
		String tmp_email = null;
		String tmp_pw = null;
		String smtp_host = null;
		String smtp_port = null;
		String to_email = null;
		String emailBody = null;

		try {

			String xmlPath = CONFIG_XML_NAME;
			Document doc = XmlUtil.createDomByPathname(xmlPath);
			Node config = XmlUtil.getChildByName(doc, "Configuration");
			if (config == null) {
				JOptionPane.showMessageDialog(null, "No Config.xml file found",
						"InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			tmp_email = XmlUtil.getChildString(config, "EMAIL_FOR_BUYER").trim();

			tmp_pw = XmlUtil.getChildString(config, "YOUR_PASSWORD").trim();

			smtp_host = XmlUtil.getChildString(config, "YOUR_SMTP_HOST").trim();
			smtp_port = XmlUtil.getChildString(config, "YOUR_SMTP_PORT").trim();
			//to_email = XmlUtil.getChildString(config, "TO_EMAIL").trim();
			to_email = BuyerEmail;
			//emailBody = XmlUtil.getChildString(config, "EMAIL_BODY").trim();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Config.xml file ERROR !!",
					"InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		final String email = tmp_email;
		final String pw = tmp_pw;
		
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
			message.setSubject("embvid:: Update for ebay Order");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();
			//emailBody = "<h1>Sample</h1><p>This is a sample HTML part</p>";
			
			emailBody = ""+
			"<br><br>Greetings from </font></font><font face=\"arial, sans-serif\"><font>Embvid</font></font><font face=\"arial, sans-serif\"><font>.<br>"+
			"<br>Hope "+
			"you are doing great.<br><br>Thank you for purchasing from us.</font></font></p><p style=\"margin-bottom:0.2in\"><font face=\"arial, sans-serif\"><font>To help you keep track of your purchases, we're sending you this order update. You can also view the latest order updated and details in </font></font><font face=\"arial, sans-serif\"><font><font><a href=\"https://signin.ebay.in/ws/eBayISAPI.dll?SignIn&UsingSSL=1&pUserId=&co_partnerId=2&siteid=203&ru=http%3A%2F%2Fmy.ebay.in%2Fws%2FeBayISAPI.dll%3FMyEbayBeta%26MyEbay%3D%26gbh%3D1%26guest%3D1&pageType=3984\" target=\"_blank\">"+
			"My Ebay</a>.</font></font></font></p><p style=\"margin-bottom:0.2in\"><font face=\"arial, sans-serif\"><font>Recent "+
			"updates to your order: </font></font><font face=\"arial, sans-serif\"><font>Your "+
			"order </font></font><font face=\"arial, sans-serif\"><font>is "+
			"marked as shipped</font></font><font face=\"arial, sans-serif\"><font>.<br></font></font></p><p style=\"margin-bottom:0.2in\"><font face=\"arial, sans-serif\"><font>For "+
			"any queries, feel free to contact us.</font></font></p><p style=\"margin-bottom:0.2in\"><font face=\"arial, sans-serif\"><font>Please leave an positive for us, we too have left an positive feedback for you.</font></font></p>"+
			"<p style=\"margin-bottom:0.2in\"><a href=\"http://feedback.ebay.in/ws/eBayISAPI.dll?LeaveFeedbackShow&amp;useridto=embvid&amp;item=%3Cxxxxxxxxxxx%3E&amp;transactid=%3Cxxxxxxxxxxxx%3E&amp;useridfrom=%3Cuser-id%3E\" target=\"_blank\"><font face=\"arial, sans-serif\"><font><b>Leave Feedback</b></font></font></a></p>"+
			"<p style=\"margin-bottom:0.2in\"><font face=\"arial, sans-serif\"><font></font></font><font face=\"arial, sans-serif\"><font>Thanks</font></font><font face=\"arial, sans-serif\"><font><br>Team"+
			"</font></font><font face=\"arial, sans-serif\"><font>Embvid</font></font></p><p style=\"margin-bottom:0.2in\"><font face=\"arial, sans-serif\"><font><a href=\"http://www.embvid.com/\" target=\"_blank\">www.</a></font></font><a href=\"http://www.embvid.com/\" target=\"_blank\"><font face=\"arial, sans-serif\"><font>embvid.com</font></font></a><font face=\"arial, sans-serif\"><font><br>"+
			"Like us @ Facebook : <a href=\"http://www.facebook.com/embvid\" target=\"_blank\">www.facebook.com/</a></font></font><a href=\"http://www.facebook.com/embvid.com\" target=\"_blank\"><font face=\"arial, sans-serif\"><font>embvid</font></font></a><font face=\"arial, sans-serif\"><font><br>"+
			"</font></font></p><p style=\"margin-bottom:0.2in\"></p><p style=\"margin-bottom:0.2in\"></p><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\"><tbody><tr><td>____________________________________________________________________________________<br><br>"+
			"<b>This is a <span>system</span> <span>generated</span> <span>mail</span>. Please do not reply to this email ID. If<br>you have a query or need any clarification you may: <br>(1) Call our 24-hour Customer Care or<br>"+
			"(2) Email Us <a href=\"mailto:info@embvid.com\" target=\"_blank\">info@embvid.com</a> </b> <br>____________________________________________________________________________________</td>";

			
			// Fill the message
			//messageBodyPart.setText(emailBody);
			// Create a multipar message
			Multipart multipart = new MimeMultipart();
			
			messageBodyPart
			.setText("Hi "+BuyerId+",");
			// Set text message part
			
			
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(emailBody, "text/html");

			// Part two is attachment
			//messageBodyPart = new MimeBodyPart();
			// String filename = "file.txt";
			// DataSource source = new FileDataSource(fileName + ".pdf");
			// messageBodyPart.setDataHandler(new DataHandler(source));
			// messageBodyPart.setFileName(fileName + ".pdf");
			// multipart.addBodyPart(messageBodyPart);

			//DataSource source = new FileDataSource(fileName + ".html");
			//messageBodyPart.setDataHandler(new DataHandler(source));
			//messageBodyPart.setFileName(fileName + ".html");
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(htmlPart);
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
		
		jTextPane1_log.append("Email sent to "+BuyerEmail +" ...\n");
		/*
		 * Make sure the new text is visible, even if there was a
		 * selection in the text area.
		 */
		jTextPane1_log.setCaretPosition(jTextPane1_log
				.getDocument().getLength());
		jTextPane1_log.setAutoscrolls(true);
	}

	/*
	 * Start sending the Feedback to user 
	 */
	void ProcessFeedback() {

		try {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			/*
			 * Get the current time The current will be compared with the
			 * OrderDate for validity to leave feedback
			 */
			new GregorianCalendar(TimeZone.getTimeZone("GMT"));
			Calendar to = GregorianCalendar.getInstance();
			long currentTime = to.getTimeInMillis();

			AddToItemDescriptionCall api = new AddToItemDescriptionCall(
					this.apiContext);

			String feedbackTxt = this.txtDescriptionToAppend.getText();
			if (feedbackTxt.length() == 0) {
				throw new SdkException("Please enter Feedback Text Msg.");
			}

			BuyerRecord db = new BuyerRecord();

			/*
			 * No record to update the Feedback
			 */
			if (db.getCount() == 0) {
				JOptionPane.showMessageDialog(null,
						"No Record Found to update Feedback !!", "InfoBox: ",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			for (int i = 0; i < db.getCount(); i++) {
				
				/*Get the BuyerID, BuyerEmail, OrderId*/
				String BuyerID = db.getBuyerID(i);
				String BuyerEmail = db.getBuyerEmail(i);
				String OrderId = db.getOrderId(i);

				String[] parts = OrderId.split("-");
				String ItemId = null;
				String TransactionId = null;

				if (parts.length == 1) {
					ItemId = parts[0];
				} else if (parts.length == 2) {
					ItemId = parts[0];
					TransactionId = parts[1];
				}

				try {
					long OrderDate = db.getOrderDate(i);
					/* get the time difference from Order date */
					long timeDifference = currentTime - OrderDate;

					long daysInBetween = timeDifference / (24 * 60 * 60 * 1000);

					/*
					 * if order date is old than 20 days then it's late to
					 * update the feedback
					 */
					if (daysInBetween <= 15) {
					LeaveFeedbackCall feedbackapi = new LeaveFeedbackCall(
							this.apiContext);

					FeedbackDetailType fd = new FeedbackDetailType();
					fd.setItemID(ItemId);
					fd.setCommentText(feedbackTxt);
					fd.setCommentType(CommentTypeCodeType.POSITIVE);

					feedbackapi.setTransactionID(TransactionId);

					feedbackapi.setTargetUser(BuyerID);
					feedbackapi.setFeedbackDetail(fd);

					feedbackapi.leaveFeedback();

					AbstractResponseType resp = feedbackapi.getResponseObject();
					Date dt = resp.getTimestamp().getTime();
					System.out.println("Feedback for (" + i + " )" + BuyerID
							+ ": " + resp.getAck().value() + " Time: "
							+ eBayUtil.toAPITimeString(dt));

					db.updateFeedbackRecord(OrderId, "true");

					jTextPane1_log.append("Feedback for " + BuyerID
							+ " sent... ++++\n");

					/*
					 * Make sure the new text is visible, even if there was a
					 * selection in the text area.
					 */
					jTextPane1_log.setCaretPosition(jTextPane1_log
							.getDocument().getLength());
					jTextPane1_log.setAutoscrolls(true);
					
					SendMail(BuyerID, BuyerEmail);
					
					}else {
						db.updateFeedbackRecord(OrderId, "proxy-true");
						jTextPane1_log.append("Feedback for (" + i + ") "
								+ BuyerID + " fail... [expired:"
								+ daysInBetween + "days]\n");
						
					}
					
			

				} catch (Exception ex1) {
					/*
					 * check if it's failed because it's an too old order, then
					 * do an proxy-true since this can't be updated any longer
					 */

					long OrderDate = db.getOrderDate(i);
					/* get the time difference from Order date */
					long timeDifference = currentTime - OrderDate;

					long daysInBetween = timeDifference / (24 * 60 * 60 * 1000);

					/*
					 * if order date is old than 20 days then it's late to
					 * update the feedback
					 */
					if (daysInBetween >= 15) {
						/*
						 * do an proxy update to database so it won't be invoked
						 * later for updates
						 */
						db.updateFeedbackRecord(OrderId, "proxy-true");
						jTextPane1_log.append("Feedback for (" + i + ") "
								+ BuyerID + " fail... [expired:"
								+ daysInBetween + "days]\n");
					} else {
						jTextPane1_log.append("Feedback for (" + i + ") "
								+ BuyerID + " fail... [unknow:" + daysInBetween
								+ "days]\n");
					}
					/*
					 * Make sure the new text is visible, even if there was a
					 * selection in the text area.
					 */
					jTextPane1_log.setCaretPosition(jTextPane1_log
							.getDocument().getLength());
				}

				try {
					Thread.sleep(5);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}

			}
			/* Show dialog box when update feedback is finished */
			JOptionPane.showMessageDialog(null, "Update Feedback done !!",
					"InfoBox: ", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception ex) {
			String msg = ex.getClass().getName() + " : " + ex.getMessage();
			((EmbvidFrame) this.getParent()).showErrorMessage(msg);
		} finally {
			this.setCursor(Cursor.getDefaultCursor());
		}

	}

	void btnDialogSendFeedback_actionPerformed(ActionEvent e) {
		/* Start the thread to process the feedback */
		/* test mail*/
		//SendMail("invincible.arpits, anand.krs", "invincible.arpit@gmail.com;anand.krs@gmail.com");
		
		new Thread() {
			public void run() {
				System.out.println("Start Update Feedback thread");
				ProcessFeedback();
			}
		}.start();
	}

	class EmbvidSendFeedbackDialog_btnCallAddToItemDescription_actionAdapter
			implements java.awt.event.ActionListener {
		EmbvidSendFeedbackDialog adaptee;

		EmbvidSendFeedbackDialog_btnCallAddToItemDescription_actionAdapter(
				EmbvidSendFeedbackDialog adaptee) {
			this.adaptee = adaptee;
		}

		public void actionPerformed(ActionEvent e) {
			adaptee.btnDialogSendFeedback_actionPerformed(e);
		}
	}

	class BuyerRecord {
		Connection con;

		String db_url;
		String db_user;
		String db_pass;

		long[] lstOrderdate;
		ArrayList<String> lstBuyerId;
		ArrayList<String> lstBuyerEmail;
		ArrayList<Integer> lstRowId;
		ArrayList<String> lstOrderId;

		int totalRecord = 0;

		public int getCount() {
			return totalRecord;
		}

		public String getBuyerID(int idx) {
			return lstBuyerId.get(idx).toString();
		}

		public String getBuyerEmail(int idx) {
			return lstBuyerEmail.get(idx).toString();
		}

		public String getOrderId(int idx) {
			return lstOrderId.get(idx).toString();
		}

		public long getOrderDate(int idx) {
			return lstOrderdate[idx];
		}

		public void updateFeedbackRecord(String orderID, String updateStr) {
			Statement st;
			try {
				st = con.createStatement();

				String sql = "UPDATE embvid.ORDERS SET Feedback='"
						+ updateStr + "' WHERE ORDERS.OrderID='"
						+ orderID + "'";

				st.executeUpdate(sql);
				st.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"Exception: ", JOptionPane.ERROR_MESSAGE);

			}
		}

		public BuyerRecord() {
			// Connection con = null;

			Statement st = null;
			ResultSet rs = null;
			try {
				String xmlPath = CONFIG_XML_NAME;
				Document doc = XmlUtil.createDomByPathname(xmlPath);
				Node config = XmlUtil.getChildByName(doc, "Configuration");
				if (config == null) {
					JOptionPane.showMessageDialog(null,
							"No Config.xml file found", "InfoBox: "
									+ "Initilizing SQL Fails  !!",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				db_url = XmlUtil.getChildString(config, "DB_URL").trim();
				db_user = XmlUtil.getChildString(config, "DB_USER").trim();
				db_pass = XmlUtil.getChildString(config, "DB_PASS").trim();

				con = DriverManager.getConnection(db_url, db_user, db_pass);
				st = con.createStatement();

				String sql = "SELECT ID, OrderDate, OrderID, BuyerID, BuyerEmail "
						+ "FROM embvid.ORDERS "
						+ "WHERE ShippingStatus='SHIPPED' AND Feedback='false'";

				rs = st.executeQuery(sql);

				lstOrderdate = new long[20000];
				lstBuyerId = new ArrayList<String>();
				lstBuyerEmail = new ArrayList<String>();
				lstOrderId = new ArrayList<String>();
				totalRecord = 0;
				while (rs.next()) {
					try {

						lstOrderdate[totalRecord] = rs.getLong("OrderDate");
						lstBuyerId.add(rs.getString("BuyerID"));
						lstBuyerEmail.add(rs.getString("BuyerEmail"));
						// lstRowId.add(rs.getInt("ID"));
						lstOrderId.add(rs.getString("OrderID"));
						totalRecord++;
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(),
								"Exception: ", JOptionPane.ERROR_MESSAGE);
					}
				}
				st.close();
			} catch (SQLException ex) {

				System.out.println("Exception :\n" + ex.getMessage());
				JOptionPane.showMessageDialog(null, ex.getMessage(),
						"Exception: ", JOptionPane.INFORMATION_MESSAGE);

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
					/*
					 * if (con != null) { con.close(); }
					 */

				} catch (SQLException ex) {

					System.out.println("Exception :\n" + ex.getMessage());
					JOptionPane.showMessageDialog(null, ex.getMessage(),
							"Exception: " + ex.getMessage(),
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

			// data = new String[] { "" + (counter++),
			// "" + System.currentTimeMillis(), "Reserved" };
		}
	}

}
