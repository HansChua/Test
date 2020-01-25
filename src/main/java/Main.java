
/**
 * 
 */
import com.google.inject.Guice;
import com.google.inject.Injector;

import hc.login.LoginDetails;
import hc.login.LoginHandler;
import hc.login.LoginModule;
import hc.login.LoginRequestPayload;
import hc.parser.ParserModule;
import hc.parser.PartData;
import hc.parser.PartDataHandler;
import hc.parser.PartInfo;
import hc.parser.PartInfoHandler;

/**
 * 
 */
public class Main {

  public static void main(String[] args) throws Exception {
    String titleSlug = "";
    String username = "";
    String password = "";

    Injector injector = Guice.createInjector(new CoreModule(), new LoginModule(), new ParserModule());

    LoginHandler loginHandler = injector.getInstance(LoginHandler.class);
    LoginDetails loginDetails = loginHandler.login(new LoginRequestPayload(username, password));

    PartInfoHandler partInfoHandler = injector.getInstance(PartInfoHandler.class);
    PartInfo partInfo = partInfoHandler.get(titleSlug);

    PartDataHandler partDataHandler = injector.getInstance(PartDataHandler.class);
    PartData partData = partDataHandler.get(loginDetails, partInfo);

    // TODO save to file
  }

}
