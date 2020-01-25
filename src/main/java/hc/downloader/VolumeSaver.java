/**
 * 
 */
package hc.downloader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.inject.Inject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.io.ByteStreams;

import hc.parser.PartData;

/**
 * 
 */
public class VolumeSaver {

  private static final String IMAGES_PATH = "images/";

  private final VolumeZipFileNamer volumeZipFileCreator;

  @Inject
  VolumeSaver(VolumeZipFileNamer volumeZipFileCreator) {
    this.volumeZipFileCreator = volumeZipFileCreator;
  }

  public VolumeSaveInfo save(Path folderPath, Volume volume) throws IOException {
    List<VolumePart> parts = volume.getParts();

    Path zipPath = volumeZipFileCreator.get(folderPath, volume);
    Path coverImagePath = null;

    try (OutputStream fileOut = Files.newOutputStream(zipPath);
        ZipOutputStream zipFile = new ZipOutputStream(fileOut)) {

      ZipEntry imgDir = new ZipEntry(IMAGES_PATH);
      zipFile.putNextEntry(imgDir);

      StringBuilder dataHtml = new StringBuilder();

      for (VolumePart part : parts) {
        PartData partData = part.getPartData();

        Document document = Jsoup.parse(partData.getDataHTML());
        Elements imgs = document.getElementsByTag("img");
        Iterator<Element> imgsItr = imgs.iterator();

        dataHtml.append(partData.getDataHTML());

        while (imgsItr.hasNext()) {
          Element img = imgsItr.next();
          String imgUrl = img.attr("src");
          String imgName = imgUrl.substring(imgUrl.lastIndexOf('/') + 1);

          ZipEntry imgEntry = new ZipEntry(IMAGES_PATH + imgName);
          zipFile.putNextEntry(imgEntry);

          try (InputStream in = new URL(imgUrl).openStream()) {
            ByteStreams.copy(in, zipFile);
          }

          int imgIdx = dataHtml.indexOf(imgUrl);
          dataHtml.replace(imgIdx, imgIdx + imgUrl.length(), IMAGES_PATH + imgName);

          if (coverImagePath == null) {
            coverImagePath = folderPath.resolve("cover_image.jpg");

            try (InputStream in = new URL(imgUrl).openStream();
                OutputStream out = Files.newOutputStream(coverImagePath)) {
              ByteStreams.copy(in, out);
            }
          }
        }
      }

      ZipEntry htmlEntry = new ZipEntry(volume.getVolumeSlug() + ".html");
      zipFile.putNextEntry(htmlEntry);

      try (PrintWriter pw = new PrintWriter(zipFile)) {
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>" + volume.getVolumeSlug() + "</title>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println(dataHtml.toString());
        pw.println("</body>");
        pw.println("</html>");
      }
    }

    return new VolumeSaveInfo(volume, folderPath, coverImagePath, zipPath);
  }

}
