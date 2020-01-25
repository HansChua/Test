/**
 * 
 */
package hc.calibre;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import hc.core.AppData;

/**
 * 
 */
public class WindowsCalibreAppPathProviderTest {

  private static final String CALIBRE_PATH = "C:/ABCD";
  private static final String EXECUTABLE_PATH = "C:/ABCD/ebook-convert.exe";

  private WindowsCalibreAppPathProvider pathProvider;

  @BeforeEach
  void setup() {
    pathProvider = new WindowsCalibreAppPathProvider();
  }

  @Test
  public void validateCorrectExecutable() {
    AppData appData = Mockito.mock(AppData.class);
    Mockito.when(appData.getCalibreFolder()).thenReturn(Paths.get(CALIBRE_PATH));

    String expected = Paths.get(EXECUTABLE_PATH).toString();
    String actual = pathProvider.get(appData);
    assertEquals(expected, actual);
  }

}
