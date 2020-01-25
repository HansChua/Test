/**
 * 
 */
package hc.downloader;

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
public class VolumeTest {

  @Test
  public void validateSettersAndGetters() {
    PojoClass data = PojoClassFactory.getPojoClass(Volume.class);

    Validator validator = ValidatorBuilder.create()
                                          .with(new SetterMustExistRule(), new GetterMustExistRule())
                                          .with(new SetterTester(), new GetterTester())
                                          .build();

    validator.validate(data);
  }

  @Test
  public void validateToString() {
    Volume volume = new Volume();
    String expected = "Volume [volumeSlug=null, volumeInfo=null, parts=null]";
    Assertions.assertEquals(expected, volume.toString());
  }

  @Test
  public void validateAddingOfParts() {
    Volume volume = new Volume("test");
    volume.addPart(new VolumePart(null, null));
    String expected = "Volume [volumeSlug=test, volumeInfo=null, parts=[VolumePart [partInfo=null, partData=null]]]";
    Assertions.assertEquals(expected, volume.toString());
  }

}
