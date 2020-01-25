/**
 * 
 */
package hc.downloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * 
 */
public class VolumeSaverTest {

  VolumeZipFileNamer volumeZipFileNamer;
  VolumeSaver volumeSaver;

  @BeforeEach
  void setup() {
    volumeZipFileNamer = new VolumeZipFileNamer();
    volumeSaver = new VolumeSaver(volumeZipFileNamer);
  }

  @Test
  public void validateZipFileGetsCreated(@TempDir Path tempDir) throws IOException {
    Volume volume = new Volume("test-vol-1");
    volumeSaver.save(tempDir, volume);

    Assertions.assertTrue(Files.exists(volumeZipFileNamer.get(tempDir, volume)));
  }

}
