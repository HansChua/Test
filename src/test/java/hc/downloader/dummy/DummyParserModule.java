/**
 * 
 */
package hc.downloader.dummy;

import com.google.inject.AbstractModule;

import hc.parser.PartDataHandler;
import hc.parser.SeriesHandler;

/**
 * 
 */
public class DummyParserModule extends AbstractModule {

  @Override
  protected void configure() {
    super.configure();

    bind(PartDataHandler.class).to(DummyPartDataHandler.class);
    bind(SeriesHandler.class).to(DummySeriesHandler.class);
  }

}
