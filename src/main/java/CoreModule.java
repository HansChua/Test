
/**
 * 
 */
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.inject.AbstractModule;

import hc.util.HttpClientBuilderFactory;
import hc.util.JsonMapperFactory;
import hc.util.RequestBuilderFactory;

/**
 * 
 */
public class CoreModule extends AbstractModule {

  @Override
  protected void configure() {
    super.configure();

    JsonMapperFactory mapperFactory = new JsonMapperFactory();
    bind(JsonMapper.class).toInstance(mapperFactory.newInstance());

    RequestBuilderFactory requestBuilderFactory = new RequestBuilderFactory();
    bind(RequestBuilderFactory.class).toInstance(requestBuilderFactory);

    HttpClientBuilderFactory httpClientBuilderFactory = new HttpClientBuilderFactory();
    bind(HttpClientBuilderFactory.class).toInstance(httpClientBuilderFactory);
  }

}
