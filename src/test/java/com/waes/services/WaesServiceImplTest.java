package com.waes.services;

import com.waes.services.impl.WaesServiceImpl;
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
  private WaesServiceImpl waesService;

  @Test
  public void testSaveLeftJson(){
    //waesService.saveLeftJson();
  }

}
