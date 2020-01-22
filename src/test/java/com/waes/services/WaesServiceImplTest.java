package com.waes.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.google.common.collect.MapDifference;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.waes.entities.WaesEntityJsonDiff;
import com.waes.entities.WaesEntityLeftJsons;
import com.waes.entities.WaesEntityRightJsons;
import com.waes.repositories.WaesRepositoryJsonDiff;
import com.waes.repositories.WaesRepositoryLeftJsons;
import com.waes.repositories.WaesRepositoryRightJsons;
import com.waes.services.impl.WaesServiceImpl;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("development")
@EnableAutoConfiguration(exclude = {JacksonAutoConfiguration.class})
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class WaesServiceImplTest {

  public WaesServiceImplTest() {
  }

  @MockBean
  private WaesRepositoryLeftJsons waesRepositoryLeftJsons;
  @MockBean
  private WaesRepositoryRightJsons waesRepositoryRightJsons;
  @MockBean
  private WaesRepositoryJsonDiff waesRepositoryJsonDiff;

  @Autowired
  private WaesServiceImpl waesService;

  @BeforeEach
  void setUp() {
    waesService = new WaesServiceImpl(waesRepositoryLeftJsons, waesRepositoryRightJsons,
        waesRepositoryJsonDiff);
  }

  @AfterEach
  void tearDown() {
    clearInvocations(waesRepositoryLeftJsons);
    clearInvocations(waesRepositoryRightJsons);
    clearInvocations(waesRepositoryJsonDiff);
  }

  @Test
  public void testIsValidJson() {
    String json = "{ \n"
        + "   \"name\":\"test\",\n"
        + "   \"java\":true\n"
        + "}";
    boolean result = waesService.isValidJson(json);
    assertTrue(result);
  }

  @Test
  public void testIsValidJsonNegative() {
    String json = "{ \n"
        + "   \"name\":\"test\",\n"
        + "   \"java\":true\n";
    boolean result = waesService.isValidJson(json);
    assertFalse(result);
  }

  @Test
  public void testDecodeBase64Json() {
    String encoded = "ew0KICAgIm9uZSI6ICJ0d28iLA0KICAgImtleSI6ICJlbXB0eV92YWx1ZSINCn0=";
    String result = waesService.decodeBase64Json(encoded);
    String expected = "{\r\n"
        + "   \"one\": \"two\",\r\n"
        + "   \"key\": \"empty_value\"\r\n"
        + "}";
    assertEquals(expected, result);
  }

  @Test
  public void testStringToJsonObject() {
    String test = "{\r\n"
        + "   \"one\": \"two\",\r\n"
        + "   \"key\": \"empty_value\"\r\n"
        + "}";

    JsonObject result = waesService.stringToJsonObject(test);
    assertNotNull(result.get("one"));
    assertNotNull(result.get("key"));
  }

  @Test
  public void testJsonToMapDifference() {
    String test = "{\r\n"
        + "   \"one\": \"two\",\r\n"
        + "   \"key\": \"empty_value\"\r\n"
        + "}";

    String test1 = "{\r\n"
        + "   \"one\": \"tree\",\r\n"
        + "   \"key\": \"empty_value\"\r\n"
        + "}";

    JsonObject jsonObject1 = new Gson().fromJson(test, JsonObject.class);
    JsonObject jsonObject2 = new Gson().fromJson(test1, JsonObject.class);

    MapDifference<String, Object> result = waesService
        .jsonToMapDifference(jsonObject1, jsonObject2);
    assertNotNull(result);
  }

  @Test
  public void testGetOffsetsFromDifferencesInValues() {
    String test = "{\r\n"
        + "   \"one\": \"two\",\r\n"
        + "   \"key\": \"empty_value\"\r\n"
        + "}";

    String test1 = "{\r\n"
        + "   \"one\": \"tree\",\r\n"
        + "   \"key\": \"empty_value\"\r\n"
        + "}";

    JsonObject jsonObject1 = new Gson().fromJson(test, JsonObject.class);
    JsonObject jsonObject2 = new Gson().fromJson(test1, JsonObject.class);

    MapDifference<String, Object> objectMapDifference = waesService
        .jsonToMapDifference(jsonObject1, jsonObject2);
    assertNotNull(objectMapDifference);

    JsonArray jsonValues = waesService
        .getOffsetsFromDifferencesInValues(objectMapDifference, jsonObject1);
    assertNotNull(jsonValues);
    assertEquals(2, jsonValues.get(0).getAsInt());
  }

  @Test
  public void testGetOffsetsFromDifferencesOnlyInLeftJson() {
    String test = "{\r\n"
        + "   \"one\": \"two\",\r\n"
        + "   \"two\": \"empty_value\"\r\n"
        + "}";

    String test1 = "{\r\n"
        + "   \"one\": \"two\",\r\n"
        + "   \"three\": \"empty_value\"\r\n"
        + "}";

    JsonObject jsonObject1 = new Gson().fromJson(test, JsonObject.class);
    JsonObject jsonObject2 = new Gson().fromJson(test1, JsonObject.class);

    MapDifference<String, Object> objectMapDifference = waesService
        .jsonToMapDifference(jsonObject1, jsonObject2);
    assertNotNull(objectMapDifference);

    JsonArray jsonValues = waesService
        .getOffsetsFromDifferencesOnlyInLeftJson(objectMapDifference, jsonObject1);
    assertNotNull(jsonValues);
    assertEquals(8, jsonValues.get(0).getAsInt());
  }


  @Test
  public void testGetOffsetsFromDifferencesOnlyInRightJson() {
    String test = "{\r\n"
        + "   \"one\": \"two\",\r\n"
        + "   \"two\": \"empty_value\"\r\n"
        + "}";

    String test1 = "{\r\n"
        + "   \"one\": \"two\",\r\n"
        + "   \"three\": \"empty_value\"\r\n"
        + "}";

    JsonObject jsonObject1 = new Gson().fromJson(test, JsonObject.class);
    JsonObject jsonObject2 = new Gson().fromJson(test1, JsonObject.class);

    MapDifference<String, Object> objectMapDifference = waesService
        .jsonToMapDifference(jsonObject1, jsonObject2);
    assertNotNull(objectMapDifference);

    JsonArray jsonValues = waesService
        .getOffsetsFromDifferencesOnlyInRightJson(objectMapDifference, jsonObject2);
    assertNotNull(jsonValues);
    assertEquals(14, jsonValues.get(0).getAsInt());
  }

  @Test
  public void testSaveLeftJson() {
    WaesEntityLeftJsons waesEntityLeftJsons = new WaesEntityLeftJsons();
    waesEntityLeftJsons.setLeftJson("test");
    waesEntityLeftJsons
        .setBase64("ew0KICAgIm9uZSI6ICJ0d28iLA0KICAgImtleSI6ICJlbXB0eV92YWx1ZSINCn0=");
    waesEntityLeftJsons.setDateCreated(LocalDateTime.now());

    waesService.saveLeftJson(waesEntityLeftJsons);

    verify(waesRepositoryLeftJsons, times(1)).save(any(WaesEntityLeftJsons.class));
  }

  @Test
  public void testSaveRightJson() {
    WaesEntityRightJsons waesEntityRightJsons = new WaesEntityRightJsons();
    waesEntityRightJsons.setIdRightJson("test");
    waesEntityRightJsons
        .setBase64("ew0KICAgIm9uZSI6ICJ0d28iLA0KICAgImtleSI6ICJlbXB0eV92YWx1ZSINCn0=");
    waesEntityRightJsons.setDateCreated(LocalDateTime.now());

    waesService.saveRightJson(waesEntityRightJsons);
    verify(waesRepositoryRightJsons, times(1)).save(any(WaesEntityRightJsons.class));
  }

  @Test
  public void testSaveJsonDiff() {

    WaesEntityJsonDiff waesEntityJsonDiff = new WaesEntityJsonDiff();
    waesEntityJsonDiff.setIdJsondiff("test");
    waesEntityJsonDiff.setJsonDiffs("{\n"
        + "    \"is_equal_content\": false,\n"
        + "    \"is_equal_size\": false\n"
        + "}");
    waesEntityJsonDiff.setDateCreated(LocalDateTime.now());

    waesService.saveJsonDiff(waesEntityJsonDiff);
    verify(waesRepositoryJsonDiff, times(1)).save(any(WaesEntityJsonDiff.class));
  }

}
