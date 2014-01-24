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
