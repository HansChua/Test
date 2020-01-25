/**
 * 
 */
package hc.downloader.dummy;

import java.io.IOException;

import hc.parser.SeriesData;
import hc.parser.SeriesHandler;

/**
 * 
 */
public class DummySeriesHandler implements SeriesHandler {

  @Override
  public SeriesData get(String titleslug) throws IOException {
    return null;
  }

}
