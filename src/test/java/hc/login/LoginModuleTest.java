/**
 * 
 */
package hc.login;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.inject.Guice;
import com.google.inject.testing.fieldbinder.BoundFieldModule;

/**
 * 
 */
public class LoginModuleTest {

  @Inject
  LoginHandler loginHandler;

  @BeforeEach
  void setup() {
    Guice.createInjector(new LoginModule(), BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void validateNotNull() {
    Assertions.assertNotNull(loginHandler);
  }

}
