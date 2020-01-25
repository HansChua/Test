/**
 * 
 */
package hc.parser;

import java.io.IOException;

/**
 * 
 */
public interface SeriesHandler {

  public SeriesData get(String titleslug) throws IOException;

}
