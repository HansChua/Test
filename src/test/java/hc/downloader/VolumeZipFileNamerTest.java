/**
 * 
 */
package hc.downloader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * 
 */
public class VolumeZipFileNamerTest {

  VolumeZipFileNamer volumeZipFileNamer;

  @BeforeEach
  void setup() {
    volumeZipFileNamer = new VolumeZipFileNamer();
  }

  @Test
  public void validateZipFileName(@TempDir Path tempDir) throws IOException {
    Volume volume = new Volume("test-vol-1");

    Path expected = tempDir.resolve("test-vol-1.zip").getFileName();
    Path actual = volumeZipFileNamer.get(tempDir, volume).getFileName();
    assertEquals(expected, actual);
  }

}
