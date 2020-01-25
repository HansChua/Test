import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;

import hc.calibre.CalibreEpubConverter;
import hc.core.App;
import hc.core.AppData;
import hc.downloader.DownloaderModule;
import hc.downloader.VolumeSaveInfo;
import hc.login.LoginModule;
import hc.parser.ParserModule;
import hc.util.UtilityModule;

/**
 * 
 */
public class Main {

  public static void main(String[] args) throws Exception {
    Injector injector = Guice.createInjector(new UtilityModule(),
                                             new LoginModule(),
                                             new ParserModule(),
                                             new DownloaderModule());

    Path configPath = Paths.get("app_data.json");
    String configString = Files.readAllLines(configPath, StandardCharsets.UTF_8).stream().collect(Collectors.joining());

    JsonMapper mapper = injector.getInstance(JsonMapper.class);
    AppData appData = mapper.readValue(configString, AppData.class);

    App app = injector.getInstance(App.class);
    CalibreEpubConverter converter = injector.getInstance(CalibreEpubConverter.class);

    for (VolumeSaveInfo saveInfo : app.download(appData)) {
      converter.convert(appData, saveInfo);
    }
  }

}
