/**
 * 
 */
package hc.parser;

import hc.login.LoginDetails;

/**
 * 
 */
public interface PartDataHandler {

  public PartData get(LoginDetails loginDetails, VolumePartInfo partInfo) throws Exception;

}
