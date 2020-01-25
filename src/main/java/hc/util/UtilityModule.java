package hc.util;

/**
 * 
 */
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.inject.AbstractModule;

/**
 * 
 */
public class UtilityModule extends AbstractModule {

  @Override
  protected void configure() {
    super.configure();

    bind(Runtime.class).toProvider(RuntimeProvider.class);

    JsonMapperFactory mapperFactory = new JsonMapperFactory();
    bind(JsonMapper.class).toInstance(mapperFactory.newInstance());

    RequestBuilderFactory requestBuilderFactory = new RequestBuilderFactory();
    bind(RequestBuilderFactory.class).toInstance(requestBuilderFactory);

    HttpClientBuilderFactory httpClientBuilderFactory = new HttpClientBuilderFactory();
    bind(HttpClientBuilderFactory.class).toInstance(httpClientBuilderFactory);
  }

}
