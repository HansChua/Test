/**
 * 
 */
package hc.downloader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import com.jparams.verifier.tostring.preset.ApacheToStringBuilderPreset;
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
    ToStringVerifier.forClass(Volume.class)
                    .withClassName(NameStyle.SIMPLE_NAME)
                    .withPreset(new ApacheToStringBuilderPreset(ApacheToStringBuilderPreset.Style.SHORT_PREFIX_STYLE))
                    .verify();
  }

  @Test
  public void validateAddingOfParts() {
    Volume volume = new Volume("test");
    VolumePart part = new VolumePart(null, null);
    volume.addPart(part);

    Assertions.assertEquals(part, volume.getParts().get(0));
  }

}
