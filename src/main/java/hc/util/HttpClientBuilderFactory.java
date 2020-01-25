/**
 * 
 */
package hc.util;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * 
 */
public class HttpClientBuilderFactory {

  public HttpClientBuilder newInstance() {
    RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
    return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig);
  }

}
