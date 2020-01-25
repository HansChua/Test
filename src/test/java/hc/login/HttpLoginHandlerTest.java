/**
 * 
 */
package hc.login;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.databind.json.JsonMapper;

import hc.util.HttpClientBuilderFactory;
import hc.util.JsonMapperFactory;
import hc.util.RequestBuilderFactory;

/**
 * 
 */
public class HttpLoginHandlerTest {

  JsonMapper jsonMapper;
  HttpClientBuilderFactory httpClientBuilderFactory;
  RequestBuilderFactory requestBuilderFactory;

  HttpLoginHandler loginHandler;

  @BeforeEach
  void setup() {
    jsonMapper = new JsonMapperFactory().newInstance();
    httpClientBuilderFactory = Mockito.spy(new HttpClientBuilderFactory());
    requestBuilderFactory = Mockito.spy(new RequestBuilderFactory());
    loginHandler = new HttpLoginHandler(jsonMapper, httpClientBuilderFactory, requestBuilderFactory);
  }

  @Test
  public void validateCorrectRetrieval() throws Exception {
    String id = "authXyZ";
    HttpEntity httpEntity = new StringEntity("{\"id\":\"authXyZ\"}", ContentType.APPLICATION_JSON);

    CloseableHttpResponse closeableHttpResponse = Mockito.mock(CloseableHttpResponse.class);
    Mockito.when(closeableHttpResponse.getEntity()).thenReturn(httpEntity);

    CloseableHttpClient closeableHttpClient = Mockito.mock(CloseableHttpClient.class);
    Mockito.when(closeableHttpClient.execute(Mockito.any())).thenReturn(closeableHttpResponse);

    HttpUriRequest request = new HttpGet();

    RequestBuilder requestBuilder = Mockito.spy(requestBuilderFactory.post(HttpLoginHandler.URL));
    Mockito.when(requestBuilderFactory.post(Mockito.eq(HttpLoginHandler.URL))).thenReturn(requestBuilder);
    Mockito.when(requestBuilder.build()).thenReturn(request);

    HttpClientBuilder httpClientBuilder = Mockito.spy(httpClientBuilderFactory.newInstance());
    Mockito.when(httpClientBuilder.build()).thenReturn(closeableHttpClient);
    Mockito.when(httpClientBuilderFactory.newInstance()).thenReturn(httpClientBuilder);

    LoginDetails loginDetails = loginHandler.login(new LoginRequestPayload("", ""));

    Assertions.assertNotNull(loginDetails);
    Assertions.assertEquals(id, loginDetails.getAuthorization());

    Mockito.verify(requestBuilder, Mockito.atLeast(1)).build();
    Mockito.verify(requestBuilderFactory, Mockito.atLeast(1)).post(HttpLoginHandler.URL);
  }

  @Test
  public void validateErrorThrown() throws Exception {
    HttpEntity httpEntity = new StringEntity("{\"error\":{}}", ContentType.APPLICATION_JSON);

    CloseableHttpResponse closeableHttpResponse = Mockito.mock(CloseableHttpResponse.class);
    Mockito.when(closeableHttpResponse.getEntity()).thenReturn(httpEntity);

    CloseableHttpClient closeableHttpClient = Mockito.mock(CloseableHttpClient.class);
    Mockito.when(closeableHttpClient.execute(Mockito.any())).thenReturn(closeableHttpResponse);

    HttpUriRequest request = new HttpGet();

    RequestBuilder requestBuilder = Mockito.spy(requestBuilderFactory.post(HttpLoginHandler.URL));
    Mockito.when(requestBuilderFactory.post(Mockito.eq(HttpLoginHandler.URL))).thenReturn(requestBuilder);
    Mockito.when(requestBuilder.build()).thenReturn(request);

    HttpClientBuilder httpClientBuilder = Mockito.spy(httpClientBuilderFactory.newInstance());
    Mockito.when(httpClientBuilder.build()).thenReturn(closeableHttpClient);
    Mockito.when(httpClientBuilderFactory.newInstance()).thenReturn(httpClientBuilder);

    LoginRequestPayload loginPayload = new LoginRequestPayload("", "");
    Exception exception = Assertions.assertThrows(SecurityException.class, () -> loginHandler.login(loginPayload));

    Assertions.assertNotNull(exception);
  }

}
