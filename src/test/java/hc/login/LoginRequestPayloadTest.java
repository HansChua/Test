/**
 * 
 */
package hc.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

/**
 * 
 */
public class LoginRequestPayloadTest {

  @Test
  public void validateSettersAndGetters() {
    PojoClass data = PojoClassFactory.getPojoClass(LoginRequestPayload.class);

    Validator validator = ValidatorBuilder.create()
                                          .with(new SetterMustExistRule(), new GetterMustExistRule())
                                          .with(new SetterTester(), new GetterTester())
                                          .build();

    validator.validate(data);
  }

  @Test
  public void validateConstructor() {
    LoginRequestPayload payload = new LoginRequestPayload("email", "pw");
    Assertions.assertEquals("email", payload.getEmail());
    Assertions.assertEquals("pw", payload.getPassword());
  }

}
