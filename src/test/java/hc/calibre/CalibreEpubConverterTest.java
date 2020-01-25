/**
 * 
 */
package hc.calibre;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.inject.Provider;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;

import com.fasterxml.jackson.databind.json.JsonMapper;

import hc.core.AppData;
import hc.downloader.Volume;
import hc.downloader.VolumeSaveInfo;
import hc.parser.VolumeInfo;
import hc.util.JsonMapperFactory;
import hc.util.RuntimeProvider;

/**
 * 
 */
public class CalibreEpubConverterTest {

  private static final String CALIBRE_COMMAND = "%s \"%s\" \"%s\" --cover \"%s\" --title \"test\" --authors \"test,test\" --publisher \"test\" --tags \"comedy\" --output-profile tablet";

  JsonMapper jsonMapper;
  Provider<Runtime> runtimeProvider;
  CalibreAppPathProvider pathProvider;
  CalibreEpubConverter epubConverter;

  @BeforeEach
  void setup() {
    jsonMapper = new JsonMapperFactory().newInstance();
    runtimeProvider = Mockito.mock(RuntimeProvider.class);
    pathProvider = new WindowsCalibreAppPathProvider();
    epubConverter = new CalibreEpubConverter(pathProvider, runtimeProvider);
  }

  @Test
  public void validateCorrectCommand(@TempDir Path epubOutputPath) throws Exception {
    Process process = Mockito.mock(Process.class);
    Mockito.when(process.waitFor()).thenReturn(0);

    Runtime runtime = Mockito.mock(Runtime.class);
    Mockito.when(runtime.exec(Mockito.anyString())).thenReturn(process);

    Mockito.when(runtimeProvider.get()).thenReturn(runtime);

    AppData appData = new AppData();
    appData.setEpubOutputFolder(epubOutputPath);
    appData.setCalibreFolder(Paths.get("C:/ABCD"));

    Path folderPath = Paths.get("");
    Path coverImagePath = Paths.get("test.jpg");
    Path zipPath = Paths.get("test.zip");

    VolumeInfo volumeInfo = new VolumeInfo();
    volumeInfo.setTitle("test");
    volumeInfo.setPublisherOriginal("test");
    volumeInfo.setAuthor("test,test");
    volumeInfo.setIllustrator("test");
    volumeInfo.setTags("comedy");

    Volume volume = new Volume("test-slug");
    volume.setVolumeInfo(volumeInfo);

    VolumeSaveInfo saveInfo = new VolumeSaveInfo(volume, folderPath, coverImagePath, zipPath);

    boolean converted = epubConverter.convert(appData, saveInfo);
    Assertions.assertTrue(converted);

    String expectedCommand = String.format(CALIBRE_COMMAND,
                                           pathProvider.get(appData),
                                           zipPath.toAbsolutePath().toString(),
                                           epubOutputPath.resolve("test-slug.epub").toAbsolutePath().toString(),
                                           coverImagePath.toAbsolutePath().toString());
    Mockito.verify(runtime, Mockito.times(1)).exec(expectedCommand);
  }

}
