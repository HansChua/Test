/**
 * 
 */
package hc.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */
public class RuntimeProviderTest {

  RuntimeProvider runtimeProvider;

  @BeforeEach
  void setup() {
    this.runtimeProvider = new RuntimeProvider();
  }

  @Test
  public void validateCorrectRuntime() {
    Runtime expected = Runtime.getRuntime();
    Runtime actual = runtimeProvider.get();
    Assertions.assertEquals(expected, actual);
  }

}
