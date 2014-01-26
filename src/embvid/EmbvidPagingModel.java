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

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;
//PagingModel.java
//A larger table model that performs "paging" of its data. This model
//reports a small number of rows (like 100 or so) as a "page" of data. You
//can switch pages to view all of the rows as needed using the pageDown()
//and pageUp() methods. Presumably, access to the other pages of data is
//dictated by other GUI elements such as up/down buttons, or maybe a text
//field that allows you to enter the page number you want to display.
//
import javax.swing.table.TableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.ebay.sdk.util.XmlUtil;
import com.mysql.jdbc.ResultSetMetaData;

/**
 * <p>
 * Title: EmbvidPagingModel.Java
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
class EmbvidPagingModel extends AbstractTableModel {
	
	public Class<String> getColumnClass(int columnIndex) {
		return String.class;
	}
	@Override
	public boolean isCellEditable(int row, int column) {
		if (column == 6)
			return true;
		return false;
	}
	


  protected int pageSize;

  protected int pageOffset;

  //protected int maxRows;
  //protected Record[] data;  // this is database
  
  protected dbRecord data = null;
  
  String db_url;
  String db_user;
  String db_pass;
  
  public EmbvidPagingModel() {
    this(20);
  }

  public EmbvidPagingModel(int size) {
	
	  pageSize = size;
	  data = new dbRecord();
    // Do not fill the record fetch when it's required 
    // Fill our table with random data (from the Record() constructor).
    //for (int i = 0; i < data.length; i++) {
    //  data[i] = new Record();
    //}
  }

  // Return values appropriate for the visible table part.
  public int getRowCount() {
    return Math.min(pageSize, data.getLength());
  }

  public int getColumnCount() {
    return dbRecord.getColumnCount();
  }

  // Work only on the visible part of the table.
  public Object getValueAt(int row, int col) {
    int realRow = row + (pageOffset * pageSize);
    return data.getValueAt(realRow, col);
  }

  public String getColumnName(int col) {
    return dbRecord.getColumnName(col);
  }

  // Use this method to figure out which page you are on.
  public int getPageOffset() {
    return pageOffset;
  }

  public int getPageCount() {
	  if(data.getLength() > 0 && data != null)
		  return (int) Math.ceil((double) data.getLength() / pageSize);
	  else 
		  return 0;
  }

  // Use this method if you want to know how big the real table is . . . we
  // could also write "getRealValueAt()" if needed.
  public int getRealRowCount() {
    return data.getLength();
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int s) {
    if (s == pageSize) {
      return;
    }
    int oldPageSize = pageSize;
    pageSize = s;
    pageOffset = (oldPageSize * pageOffset) / pageSize;
    fireTableDataChanged();
    /*
     * if (pageSize < oldPageSize) { fireTableRowsDeleted(pageSize,
     * oldPageSize - 1); } else { fireTableRowsInserted(oldPageSize,
     * pageSize - 1); }
     */
  }

  // Update the page offset and fire a data changed (all rows).
  public void pageDown() {
    if (pageOffset < getPageCount() - 1) {
      pageOffset++;
      fireTableDataChanged();
    }
  }

  // Update the page offset and fire a data changed (all rows).
  public void pageUp() {
    if (pageOffset > 0) {
      pageOffset--;
      fireTableDataChanged();
    }
  }

  // We provide our own version of a scrollpane that includes
  // the page up and page down buttons by default.
  public static JScrollPane createPagingScrollPaneForTable(JTable jt) {
    JScrollPane jsp = new JScrollPane(jt);
    TableModel tmodel = jt.getModel();

    // Don't choke if this is called on a regular table . . .
    if (!(tmodel instanceof EmbvidPagingModel)) {
      return jsp;
    }

    // Okay, go ahead and build the real scrollpane
    final EmbvidPagingModel model = (EmbvidPagingModel) tmodel;
    final JButton upButton = new JButton(new ArrowIcon(ArrowIcon.UP));
    upButton.setEnabled(false); // starts off at 0, so can't go up
    final JButton downButton = new JButton(new ArrowIcon(ArrowIcon.DOWN));
    if (model.getPageCount() <= 1) {
      downButton.setEnabled(false); // One page...can't scroll down
    }

    upButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        model.pageUp();

        // If we hit the top of the data, disable the up button.
        if (model.getPageOffset() == 0) {
          upButton.setEnabled(false);
        }
        downButton.setEnabled(true);
      }
    });

    downButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        model.pageDown();

        // If we hit the bottom of the data, disable the down button.
        if (model.getPageOffset() == (model.getPageCount() - 1)) {
          downButton.setEnabled(false);
        }
        upButton.setEnabled(true);
      }
    });

    // Turn on the scrollbars; otherwise we won't get our corners.
    jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

    // Add in the corners (page up/down).
    jsp.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER, upButton);
    jsp.setCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER, downButton);

    return jsp;
  }
}

//Record.java
//A simple data structure for use with the PagingModel demo.
//

class dbRecord {
	static final String CONFIG_XML_NAME = "Config.xml";
static String[] headers = { 
							"OrderDate",   // 0 - 0 
							//"OrderStatus",   - 1 
							"OrderID" ,    // 1 - 1
							//"PaisaPayID" ,//    2
							"Item" ,       // 2 - 3
							"Quantity" ,  //  3 - 4
							//"Price" ,   //    - 5
							"BuyerID" ,   //  4 - 6
							//"BuyerEmail" , //  - 7
							"ShipAddress" ,  // 5 - 8
							"ShippingStatus" // 6 - 9
							//,"Feedback" 
							};  //7 - 10
static int counter;

Connection con;

int maxRow;
String db_url;
String db_user;
String db_pass;


public dbRecord() {
	//Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    try
    {
    String xmlPath = CONFIG_XML_NAME;
	Document doc = XmlUtil.createDomByPathname(xmlPath);
	Node config = XmlUtil.getChildByName(doc, "Configuration");
	if( config == null )
	{
		  JOptionPane.showMessageDialog(null, "No Config.xml file found", "InfoBox: " + "Initilizing SQL Fails  !!", JOptionPane.INFORMATION_MESSAGE);
		  return;
	}

	db_url = XmlUtil.getChildString(config, "DB_URL").trim();
	db_user = XmlUtil.getChildString(config, "DB_USER").trim();;
    db_pass = XmlUtil.getChildString(config, "DB_PASS").trim();;
   

	
    //String url = "jdbc:mysql://localhost:3306/test1db";
    //String user = "test111";
    //String password = "test222";

    
        con = DriverManager.getConnection(db_url, db_user, db_pass);
        st = con.createStatement();
             
        //TODO: get the total roe count in SQL database
	    rs = st.executeQuery("SELECT * FROM test1db.EMBVID_ORDERS5");

		rs = st.executeQuery("SELECT COUNT(*) FROM test1db.EMBVID_ORDERS5");
		
		rs.next();
		maxRow = rs.getInt(1);
        
		System.out.println("Max Row: " + maxRow);
		
        st.close();
    } catch (SQLException ex) {
   

    	System.out.println("Exception :\n"+ex.getMessage());
    	JOptionPane.showMessageDialog(null, ex.getMessage(), "Exception: " + ex.getMessage(), JOptionPane.INFORMATION_MESSAGE);

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
           /*if (con != null) {
                con.close();
            }*/

        } catch (SQLException ex) {
        	
        	System.out.println("Exception :\n"+ex.getMessage());
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Exception: " + ex.getMessage(), JOptionPane.INFORMATION_MESSAGE);	
        }
   }
	
    //data = new String[] { "" + (counter++),
    //    "" + System.currentTimeMillis(), "Reserved" };
}

public String getValueAt(int row, int col) {

	if(maxRow == 0)
	{
		return null;
	}
	
	String data = "";
	row = row+1;
	
	//col = col+1;
	try {
		
	//System.out.println("@@@ Value for Row: "+row+" Col: "+col);
	
	String selRow = String.format("%d", row); //String.format("%d", (maxRow - row));
	/*Lets first find out if the order is completed or not*/
	  
		
	
			String selCol = "";
			if(col == 0 )
			{
				selCol = " OrderDate, OrderStatus ";
			}
			else if( col == 1 || col == 2)
			{
				selCol = " "+headers[col]+" ";
			}
			else if(col == 3)
			{
				selCol = " Quantity, Price ";
			}else if(col == 4)
			{
				selCol = " BuyerID, BuyerEmail ";
			}else if (col == 5 || col ==6 || col == 7)
			{
				selCol = " "+headers[col]+" ";
			}
			
			
			
			//con = DriverManager.getConnection(db_url, db_user, db_pass);
		 
		    
		    Statement st = con.createStatement();
		    
			String sql = "SELECT"+selCol+"FROM test1db.EMBVID_ORDERS5 "+
						  "WHERE ID="+selRow;
			
			ResultSet rs = st.executeQuery(sql);
			
			
		    
		    while(rs.next()){
		    	switch (col)
		    	{
		    	case 0:
		    		//System.out.println(" : " +rs.getLong("OrderDate"));
		    		long longDate = rs.getLong("OrderDate"); // from the database
		    		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		    		data = formatter.format(new Date(longDate));
		    		data = data+"\n\n"+rs.getString("OrderStatus");
		    		break;
		    	case 1:
		    		data = rs.getString("OrderID");
		    		break;
		    	case 2:
		    		data = rs.getString("Item");
		    		break;
		    	case 3:
		    		data = "Qty: ";
		    		data = data + rs.getString("Quantity");
		    		data = data+"\nPrice: "+rs.getString("Price");
		    		break;
		    	case 4:
		    		data = rs.getString("BuyerID");
		    		data = data+"\n"+rs.getString("BuyerEmail");
		    		break;
		    	case 5:
		    		data = rs.getString("ShipAddress");
		    		break;
		    	case 6:
		    		data = rs.getString("ShippingStatus");
		    		break;
		    	case 7:
		    		data = rs.getString("Feedback");
		    		break;
		    	default:
		    		break;
		    	}
		    }
		    st.close();
		    rs.close();
	
    //return data;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return data;
	
	
}

public static String getColumnName(int i) {
	//System.out.println("Col Name:" + headers[i]);
  return headers[i];
}

public static int getColumnCount() {
	//System.out.println("Col length:" + headers.length);
  return headers.length;
}

public int getLength() {
	  return maxRow;
	}


}


//ArrowIcon.java
//A simple implementation of the Icon interface that can make
//Up and Down arrows.
//

class ArrowIcon implements Icon {

public static final int UP = 0;

public static final int DOWN = 1;

private int direction;

private Polygon pagePolygon = new Polygon(new int[] { 2, 4, 4, 10, 10, 2 },
    new int[] { 4, 4, 2, 2, 12, 12 }, 6);

private int[] arrowX = { 4, 9, 6 };

private Polygon arrowUpPolygon = new Polygon(arrowX,
    new int[] { 10, 10, 4 }, 3);

private Polygon arrowDownPolygon = new Polygon(arrowX,
    new int[] { 6, 6, 11 }, 3);

public ArrowIcon(int which) {
  direction = which;
}

public int getIconWidth() {
  return 14;
}

public int getIconHeight() {
  return 14;
}

public void paintIcon(Component c, Graphics g, int x, int y) {
  g.setColor(Color.black);
  pagePolygon.translate(x, y);
  g.drawPolygon(pagePolygon);
  pagePolygon.translate(-x, -y);
  if (direction == UP) {
    arrowUpPolygon.translate(x, y);
    g.fillPolygon(arrowUpPolygon);
    arrowUpPolygon.translate(-x, -y);
  } else {
    arrowDownPolygon.translate(x, y);
    g.fillPolygon(arrowDownPolygon);
    arrowDownPolygon.translate(-x, -y);
  }
}
}