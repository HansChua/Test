package hc.parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PartDataTest {

  PartData data;

  @Before
  public void setup() {
    data = new PartData();
  }

  @Test
  public final void testGetDataHTML() {
    data.setDataHTML("something");
    assertEquals("something", data.getDataHTML());
  }

  @Test
  public final void testSetDataHTML() {
    data.setDataHTML("something");
    assertEquals("something", data.getDataHTML());
  }

}
