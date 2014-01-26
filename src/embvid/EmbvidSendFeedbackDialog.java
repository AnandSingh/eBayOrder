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
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.SdkException;
import com.ebay.sdk.call.AddToItemDescriptionCall;

public class EmbvidSendFeedbackDialog extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private ApiContext apiContext = new ApiContext();

  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JButton btnCallAddToItemDescription = new JButton();
  JPanel jPanel6 = new JPanel();
  JPanel jPanel7 = new JPanel();
  JTextField txtItemID = new JTextField();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JTextPane txtDescriptionToAppend = new JTextPane();
  BorderLayout borderLayout3 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();

  public EmbvidSendFeedbackDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();

      EmbvidFrame fd = (EmbvidFrame)frame;
      this.apiContext = fd.getApiContext();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public EmbvidSendFeedbackDialog() {
    this(null, "", false);
  }

  private void jbInit() throws Exception {
    panel1.setLayout(borderLayout1);
    jPanel1.setLayout(borderLayout2);
    btnCallAddToItemDescription.setText("AddToItemDescription");
    btnCallAddToItemDescription.addActionListener(new EmbvidSendFeedbackDialog_btnCallAddToItemDescription_actionAdapter(this));
    jLabel1.setPreferredSize(new Dimension(60, 15));
    jLabel1.setText("Item ID");
    jLabel2.setPreferredSize(new Dimension(60, 15));
    jLabel2.setText("Description");
    txtDescriptionToAppend.setPreferredSize(new Dimension(200, 100));
    jPanel4.setLayout(borderLayout3);
    txtItemID.setMinimumSize(new Dimension(6, 21));
    txtItemID.setPreferredSize(new Dimension(205, 21));
    txtItemID.setText("");
    jPanel5.setPreferredSize(new Dimension(149, 40));
    jPanel6.setMinimumSize(new Dimension(52, 31));
    jPanel6.setPreferredSize(new Dimension(280, 35));
    getContentPane().add(panel1);
    panel1.add(jPanel1,  BorderLayout.NORTH);
    panel1.add(jPanel2,  BorderLayout.CENTER);
    panel1.add(jPanel3, BorderLayout.SOUTH);
    jPanel1.add(jPanel4,  BorderLayout.NORTH);
    jPanel4.add(jPanel6,  BorderLayout.NORTH);
    jPanel6.add(jLabel1, null);
    jPanel6.add(txtItemID, null);
    jPanel4.add(jPanel7, BorderLayout.CENTER);
    jPanel7.add(jLabel2, null);
    jPanel7.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(txtDescriptionToAppend, null);
    jPanel1.add(jPanel5, BorderLayout.CENTER);
    jPanel5.add(btnCallAddToItemDescription, null);
    this.setSize(new Dimension(300, 210));
    this.setResizable(false);
  }

  void btnDialogSendFeedback_actionPerformed(ActionEvent e) {
    try
    {
      AddToItemDescriptionCall api = new AddToItemDescriptionCall(this.apiContext);

      String itemID = this.txtItemID.getText();
      if( itemID.length() == 0 )
        throw new SdkException("Please enter item ID.");
      api.setItemID(itemID);

      String desc = this.txtDescriptionToAppend.getText();
      if( desc.length() == 0 )
        throw new SdkException("Please enter description to be appended.");
      api.setDescription(desc);

      api.addToItemDescription();

      ((EmbvidFrame)this.getParent()).showInfoMessage("The description has been appended successfully.");
    }
    catch(Exception ex)
    {
      String msg = ex.getClass().getName() + " : " + ex.getMessage();
      ((EmbvidFrame)this.getParent()).showErrorMessage(msg);
    }
  }
}

class EmbvidSendFeedbackDialog_btnCallAddToItemDescription_actionAdapter implements java.awt.event.ActionListener {
	EmbvidSendFeedbackDialog adaptee;

	EmbvidSendFeedbackDialog_btnCallAddToItemDescription_actionAdapter(EmbvidSendFeedbackDialog adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDialogSendFeedback_actionPerformed(e);
  }
}
