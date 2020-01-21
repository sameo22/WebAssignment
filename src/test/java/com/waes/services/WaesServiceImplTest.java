package com.waes.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = WaesServiceImplTest.class)
@ActiveProfiles("development")
@ComponentScan(basePackages = {"com.q6cyber"})
@EnableAutoConfiguration(exclude = {JacksonAutoConfiguration.class})
public class WaesServiceImplTest {

  @Autowired
  private WaesService waesService;

  @Test
  public void testIsValidJsonTrue(){
    String json = "{ \n"
        + "   \"name\":\"test\",\n"
        + "   \"java\":true\n"
        + "}";
    boolean result = waesService.isValidJson(json);
    assertTrue(result);
  }

  @Test
  public void testIsValidJsonFalse(){
    String json = "{ \n"
        + "   \"name\":\"test\",\n"
        + "   \"java\"";
    boolean result = waesService.isValidJson(json);
    assertFalse(result);
  }

}
