/**
 * 
 */
package hc.calibre;

import hc.core.AppData;

/**
 * 
 */
public interface CalibreAppPathProvider {

  /**
   * @param appData app information
   * @return Calibre app to execute for conversion
   */
  String get(AppData appData);

}
