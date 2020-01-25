/**
 * 
 */
package hc.parser;

import com.google.inject.AbstractModule;

/**
 * 
 */
public class ParserModule extends AbstractModule {

  @Override
  protected void configure() {
    super.configure();

    bind(PartDataHandler.class).to(HttpPartDataHandler.class);
    bind(SeriesHandler.class).to(HttpSeriesHandler.class);
  }

}
