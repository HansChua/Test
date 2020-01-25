/**
 * 
 */
package hc.login;

import javax.inject.Inject;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.json.JsonMapper;

import hc.util.HttpClientBuilderFactory;
import hc.util.RequestBuilderFactory;

/**
 * 
 */
public class HttpLoginHandler implements LoginHandler {

  private static final String URL = "https://api.j-novel.club/api/users/login?include=user";

  private final JsonMapper mapper;
  private final HttpClientBuilderFactory httpClientBuilderFactory;
  private final RequestBuilderFactory requestBuilderFactory;

  @Inject
  HttpLoginHandler(JsonMapper mapper,
                   HttpClientBuilderFactory httpClientBuilderFactory,
                   RequestBuilderFactory requestBuilderFactory) {
    this.mapper = mapper;
    this.httpClientBuilderFactory = httpClientBuilderFactory;
    this.requestBuilderFactory = requestBuilderFactory;
  }

  @Override
  public LoginDetails login(LoginRequestPayload requestPayload) throws Exception {
    final String payload = mapper.writeValueAsString(requestPayload);

    StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
    HttpUriRequest request = requestBuilderFactory.post(URL).setEntity(entity).build();

    CookieStore cookies = new BasicCookieStore();
    try (CloseableHttpClient client = httpClientBuilderFactory.newInstance().setDefaultCookieStore(cookies).build();
        CloseableHttpResponse response = client.execute(request)) {
      String string = EntityUtils.toString(response.getEntity());
      LoginResponse loginResponse = mapper.readValue(string, LoginResponse.class);
      if (null != loginResponse.getError()) {
        throw new SecurityException("Incorrect login");
      }
      return new LoginDetails(loginResponse.getId(), cookies);
    }
  }

}
