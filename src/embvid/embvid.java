package embvid;

import javax.swing.UIManager;

import com.ebay.sdk.helper.ui.GuiUtil;

/**
 * A Hello World-like sample, 
 * showing how to call eBay API using eBay SDK.
 *  
 * @author boyang
 */
public class embvid {
	boolean packFrame = false;
	  //Construct the application
	  public embvid() {
	    FrameDemo frame = new FrameDemo();
	    //Validate frames that have preset sizes
	    //Pack frames that have useful preferred size info, e.g. from their layout
	    if (packFrame) {
	      frame.pack();
	    }
	    else {
	      frame.validate();
	    }

	    GuiUtil.CenterComponent(frame);
	    //frame.pack();
	    frame.setVisible(true);
	    frame.select();
  
    
	  }
	  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		      //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		      UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		    }
		    catch(Exception e) {
		      e.printStackTrace();
		    }
		    new embvid();
	}
}
