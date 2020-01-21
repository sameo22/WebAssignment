package com.waes.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("development")
@EnableAutoConfiguration(exclude = {JacksonAutoConfiguration.class})
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class WaesControllerImplTest {

  @LocalServerPort
  private static int port = 8090;
  static TestRestTemplate restTemplate = new TestRestTemplate();
  static HttpHeaders headers = new HttpHeaders();

  @Test
  @Order(1)
  public void testSaveLeftJson() {

    headers.setContentType(MediaType.TEXT_PLAIN);
    String encodedJson = "eyANCiAgICJuYW1lIjoidGVzdCIsDQogICAiamF2YSI6dHJ1ZQ0KfQ==";

    HttpEntity<String> entity = new HttpEntity<String>(encodedJson, headers);
    ResponseEntity<String> response = restTemplate.exchange(
        "http://localhost:" + port + "/v1/diff/test10/left", HttpMethod.POST, entity, String.class);
    assertEquals("WAES Controller saved the left JsonObject correctly with id: test10",
        response.getBody());

  }

  @Test
  @Order(2)
  public void testRetrieveLeftJson() throws Exception {

    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    ResponseEntity<String> response = restTemplate
        .exchange("http://localhost:" + port + "/v1/diff/retrieveJson/test10/left", HttpMethod.GET,
            entity, String.class);
    String expected = "{ \"name\": \"test\", \"java\": true }";
    JSONAssert.assertEquals(expected, response.getBody(), false);
  }

  @Test
  @Order(3)
  public void testSaveRightJson() {

    headers.setContentType(MediaType.TEXT_PLAIN);
    String encodedJson = "eyANCiAgICJuYW1lIjoidGVzdCIsDQogICAiamF2YSI6dHJ1ZQ0KfQ==";

    HttpEntity<String> entity = new HttpEntity<String>(encodedJson, headers);
    ResponseEntity<String> response = restTemplate.exchange(
        "http://localhost:" + port + "/v1/diff/test10/right", HttpMethod.POST, entity, String.class);
    assertEquals("WAES Controller saved the right JsonObject correctly with id: test10",
        response.getBody());

  }

  @Test
  @Order(4)
  public void testRetrieveRightJson() throws Exception {

    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    ResponseEntity<String> response = restTemplate
        .exchange("http://localhost:" + port + "/v1/diff/retrieveJson/test10/right", HttpMethod.GET,
            entity, String.class);
    String expected = "{ \"name\": \"test\", \"java\": true }";
    JSONAssert.assertEquals(expected, response.getBody(), false);
  }


  @AfterClass
  public static void testDeleteLeftJson() {
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    ResponseEntity<String> responseLeft = restTemplate.exchange(
        "http://localhost:" + port +"/v1/diff/delete/test10/left", HttpMethod.POST, entity, String.class);
    String expectedLeft = "WAES Controller deleted the left JsonObject correctly with id: test10";
    assertEquals(expectedLeft, responseLeft.getBody());

    ResponseEntity<String> responseRight = restTemplate.exchange(
        "http://localhost:" + port +"/v1/diff/delete/test10/right", HttpMethod.POST, entity, String.class);
    String expectedRight = "WAES Controller deleted the right JsonObject correctly with id: test10";
    assertEquals(expectedRight, responseRight.getBody());

  }
}



