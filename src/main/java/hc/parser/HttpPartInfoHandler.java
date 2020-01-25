/**
 * 
 */
package hc.parser;

import java.nio.charset.StandardCharsets;

import javax.inject.Inject;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.json.JsonMapper;

import hc.util.HttpClientBuilderFactory;
import hc.util.RequestBuilderFactory;

/**
 * 
 */
public class HttpPartInfoHandler implements PartInfoHandler {

  private static final String URL = "https://api.j-novel.club/api/parts/findOne";

  private final JsonMapper mapper;
  private final HttpClientBuilderFactory httpClientBuilderFactory;
  private final RequestBuilderFactory requestBuilderFactory;

  @Inject
  HttpPartInfoHandler(JsonMapper mapper,
                      HttpClientBuilderFactory httpClientBuilderFactory,
                      RequestBuilderFactory requestBuilderFactory) {
    this.mapper = mapper;
    this.httpClientBuilderFactory = httpClientBuilderFactory;
    this.requestBuilderFactory = requestBuilderFactory;
  }

  @Override
  public PartInfo get(String titleSlug) throws Exception {
    PartInfoRequest partInfoRequest = new PartInfoRequest(titleSlug);

    String filter = mapper.writeValueAsString(partInfoRequest);
    HttpUriRequest request = requestBuilderFactory.get(URL).addParameter("filter", filter).build();

    try (CloseableHttpClient client = httpClientBuilderFactory.newInstance().build();
        CloseableHttpResponse response = client.execute(request)) {
      String string = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
      PartInfo partInfo = mapper.readValue(string, PartInfo.class);
      return partInfo;
    }
  }

}
