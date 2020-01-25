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

    bind(PartInfoHandler.class).to(HttpPartInfoHandler.class);
    bind(PartDataHandler.class).to(HttpPartDataHandler.class);
  }

}
