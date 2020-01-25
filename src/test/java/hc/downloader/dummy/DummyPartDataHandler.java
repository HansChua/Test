/**
 * 
 */
package hc.downloader.dummy;

import hc.login.LoginDetails;
import hc.parser.PartData;
import hc.parser.PartDataHandler;
import hc.parser.VolumePartInfo;

/**
 * 
 */
public class DummyPartDataHandler implements PartDataHandler {

  @Override
  public PartData get(LoginDetails loginDetails, VolumePartInfo partInfo) throws Exception {
    return null;
  }

}
