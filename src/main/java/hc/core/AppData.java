/**
 * 
 */
package hc.core;

import java.nio.file.Path;
import java.util.List;

import hc.downloader.VolumeDownloadRequest;

/**
 * 
 */
public class AppData {

  private Path calibreFolder;

  private String username;
  private String password;

  private Path outputFolder;
  private Path epubOutputFolder;

  private List<VolumeDownloadRequest> requests;

  public AppData() {
    super();
    this.calibreFolder = null;
    this.username = null;
    this.password = null;
    this.outputFolder = null;
    this.epubOutputFolder = null;
    this.requests = null;
  }

  public Path getCalibreFolder() {
    return calibreFolder;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public List<VolumeDownloadRequest> getRequests() {
    return requests;
  }

  public Path getOutputFolder() {
    return outputFolder;
  }

  public Path getEpubOutputFolder() {
    return epubOutputFolder;
  }

  public void setCalibreFolder(Path calibreFolder) {
    this.calibreFolder = calibreFolder;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setOutputFolder(Path outputFolder) {
    this.outputFolder = outputFolder;
  }

  public void setEpubOutputFolder(Path epubOutputFolder) {
    this.epubOutputFolder = epubOutputFolder;
  }

  public void setRequests(List<VolumeDownloadRequest> requests) {
    this.requests = requests;
  }

}
