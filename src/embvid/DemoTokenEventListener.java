/*
Copyright (c) 2013 eBay, Inc.
This program is licensed under the terms of the eBay Common Development and
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent  version 
thereof released by eBay.  The then-current version of the License can be found 
at http://www.opensource.org/licenses/cddl1.php and in the eBaySDKLicense file that 
is under the eBay SDK ../docs directory.
*/

package embvid;

import com.ebay.sdk.TokenEventListener;
import com.ebay.sdk.util.eBayUtil;
 
public class DemoTokenEventListener implements TokenEventListener {

  FrameDemo mainFrame;

  public DemoTokenEventListener(FrameDemo mainFrame) {
    this.mainFrame = mainFrame;
  }

  public void renewToken(String newToken)
  {
    mainFrame.showInfoMessage("eBay Token has been renewed successfully!");
  }

  public void warnHardExpiration(java.util.Date expirationDate)
  {
    mainFrame.showInfoMessage("Received token hard-expiration-warning: " + eBayUtil.toAPITimeString(expirationDate));
  }
}
