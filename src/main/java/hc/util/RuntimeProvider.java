/**
 * 
 */
package hc.util;

import javax.inject.Provider;

/**
 * 
 */
public class RuntimeProvider implements Provider<Runtime> {

  @Override
  public Runtime get() {
    return Runtime.getRuntime();
  }

}
