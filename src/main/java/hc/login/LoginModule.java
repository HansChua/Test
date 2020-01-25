/**
 * 
 */
package hc.login;

import com.google.inject.AbstractModule;

/**
 * 
 */
public class LoginModule extends AbstractModule {

  @Override
  protected void configure() {
    super.configure();

    bind(LoginHandler.class).to(HttpLoginHandler.class);
  }

}
