/**
 * 
 */
package hc.parser;

import javax.inject.Inject;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.json.JsonMapper;

import hc.login.LoginDetails;
import hc.util.HttpClientBuilderFactory;
import hc.util.RequestBuilderFactory;

/**
 * 
 */
public class HttpPartDataHandler implements PartDataHandler {

  static final String URL = "https://api.j-novel.club/api/parts/%s/partData";

  private final JsonMapper mapper;
  private final HttpClientBuilderFactory httpClientBuilderFactory;
  private final RequestBuilderFactory requestBuilderFactory;

  @Inject
  HttpPartDataHandler(JsonMapper mapper,
                      HttpClientBuilderFactory httpClientBuilderFactory,
                      RequestBuilderFactory requestBuilderFactory) {
    this.mapper = mapper;
    this.httpClientBuilderFactory = httpClientBuilderFactory;
    this.requestBuilderFactory = requestBuilderFactory;
  }

  @Override
  public PartData get(LoginDetails loginDetails, VolumePartInfo partInfo) throws Exception {
    final String partDataUrl = String.format(URL, partInfo.getId());

    HttpUriRequest request = requestBuilderFactory.get(partDataUrl)
                                                  .setHeader(HttpHeaders.AUTHORIZATION, loginDetails.getAuthorization())
                                                  .build();

    try (
        CloseableHttpClient client = httpClientBuilderFactory.newInstance()
                                                             .setDefaultCookieStore(loginDetails.getCookies())
                                                             .build();
        CloseableHttpResponse response = client.execute(request)) {
      String string = EntityUtils.toString(response.getEntity());
      return mapper.readValue(string, PartData.class);
    }
  }

}
