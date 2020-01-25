/**
 * 
 */
package hc.parser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.databind.json.JsonMapper;

import hc.login.LoginDetails;
import hc.util.HttpClientBuilderFactory;
import hc.util.JsonMapperFactory;
import hc.util.RequestBuilderFactory;

/**
 * 
 */
public class HttpPartDataHandlerTest {

  JsonMapper jsonMapper;
  HttpClientBuilderFactory httpClientBuilderFactory;
  RequestBuilderFactory requestBuilderFactory;

  HttpPartDataHandler partDataHandler;

  @BeforeEach
  void setup() {
    jsonMapper = new JsonMapperFactory().newInstance();
    httpClientBuilderFactory = Mockito.spy(new HttpClientBuilderFactory());
    requestBuilderFactory = Mockito.spy(new RequestBuilderFactory());
    partDataHandler = new HttpPartDataHandler(jsonMapper, httpClientBuilderFactory, requestBuilderFactory);
  }

  @Test
  public void validateCorrectRetrieval() throws Exception {
    LoginDetails loginDetails = new LoginDetails("authXyZ", new BasicCookieStore());
    VolumePartInfo partInfo = new VolumePartInfo();
    partInfo.setId("abc");

    HttpEntity httpEntity = new StringEntity("{}", ContentType.APPLICATION_JSON);

    CloseableHttpResponse closeableHttpResponse = Mockito.mock(CloseableHttpResponse.class);
    Mockito.when(closeableHttpResponse.getEntity()).thenReturn(httpEntity);

    CloseableHttpClient closeableHttpClient = Mockito.mock(CloseableHttpClient.class);
    Mockito.when(closeableHttpClient.execute(Mockito.any())).thenReturn(closeableHttpResponse);

    HttpUriRequest request = new HttpGet();

    String expectedUrl = String.format(HttpPartDataHandler.URL, partInfo.getId());
    RequestBuilder requestBuilder = Mockito.spy(requestBuilderFactory.get(expectedUrl));
    Mockito.when(requestBuilderFactory.get(Mockito.eq(expectedUrl))).thenReturn(requestBuilder);
    Mockito.when(requestBuilder.build()).thenReturn(request);

    HttpClientBuilder httpClientBuilder = Mockito.spy(httpClientBuilderFactory.newInstance());
    Mockito.when(httpClientBuilder.build()).thenReturn(closeableHttpClient);
    Mockito.when(httpClientBuilderFactory.newInstance()).thenReturn(httpClientBuilder);

    PartData partData = partDataHandler.get(loginDetails, partInfo);

    Assertions.assertNotNull(partData);
    Mockito.verify(requestBuilder, Mockito.atLeast(1))
           .setHeader(HttpHeaders.AUTHORIZATION, loginDetails.getAuthorization());
    Mockito.verify(requestBuilder, Mockito.atLeast(1)).build();
    Mockito.verify(requestBuilderFactory, Mockito.atLeast(1)).get(expectedUrl);
  }

}
