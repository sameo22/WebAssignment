package com.waes;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = WaesApplicationTests.class)
@ActiveProfiles("development")
@ComponentScan(basePackages = {"com.q6cyber"})
@EnableAutoConfiguration(exclude = {JacksonAutoConfiguration.class})
class WaesApplicationTests {

  @Test
  void contextLoads() {
  }

}
