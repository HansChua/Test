/**
 * 
 */
package hc.parser;

import java.io.IOException;
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
public class HttpSeriesHandler implements SeriesHandler {

  static final String FILTER_PARAM = "{\"where\":{\"titleslug\":\"%s\"},\"include\":[\"volumes\",\"parts\"]}";
  static final String URL = "https://api.j-novel.club/api/series/findOne";

  private final JsonMapper mapper;
  private final HttpClientBuilderFactory httpClientBuilderFactory;
  private final RequestBuilderFactory requestBuilderFactory;

  @Inject
  HttpSeriesHandler(JsonMapper mapper,
                    HttpClientBuilderFactory httpClientBuilderFactory,
                    RequestBuilderFactory requestBuilderFactory) {
    this.mapper = mapper;
    this.httpClientBuilderFactory = httpClientBuilderFactory;
    this.requestBuilderFactory = requestBuilderFactory;
  }

  @Override
  public SeriesData get(String titleslug) throws IOException {
    String filterParam = String.format(FILTER_PARAM, titleslug);
    HttpUriRequest request = requestBuilderFactory.get(URL).addParameter("filter", filterParam).build();

    try (CloseableHttpClient client = httpClientBuilderFactory.newInstance().build();
        CloseableHttpResponse response = client.execute(request)) {
      String string = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
      return mapper.readValue(string, SeriesData.class);
    }
  }

}
