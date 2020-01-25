/**
 * 
 */
package hc.parser;

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
public class SeriesDataTest {

  @Test
  public void validateSettersAndGetters() {
    PojoClass data = PojoClassFactory.getPojoClass(SeriesData.class);

    Validator validator = ValidatorBuilder.create()
                                          .with(new SetterMustExistRule(), new GetterMustExistRule())
                                          .with(new SetterTester(), new GetterTester())
                                          .build();

    validator.validate(data);
  }

  @Test
  public void validateToString() {
    SeriesData seriesData = new SeriesData();
    String expected = "SeriesData [title=null, titleslug=null, author=null, illustrator=null, tags=null, volumes=[], parts=[]]";
    Assertions.assertEquals(expected, seriesData.toString());
  }

}
