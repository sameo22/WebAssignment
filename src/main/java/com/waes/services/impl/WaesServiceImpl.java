package com.waes.services.impl;

import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.waes.entities.WaesEntityJsonDiff;
import com.waes.entities.WaesEntityLeftJsons;
import com.waes.entities.WaesEntityRightJsons;
import com.waes.repositories.WaesRepositoryJsonDiff;
import com.waes.repositories.WaesRepositoryLeftJsons;
import com.waes.repositories.WaesRepositoryRightJsons;
import com.waes.services.WaesService;
import com.waes.enums.WaesStatusEnum;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WaesServiceImpl implements WaesService {

  protected static final Logger LOG = LogManager.getLogger(WaesServiceImpl.class);

  @Autowired
  protected WaesRepositoryLeftJsons waesRepositoryLeftJsons;

  @Autowired
  protected WaesRepositoryRightJsons waesRepositoryRightJsons;

  @Autowired
  protected WaesRepositoryJsonDiff waesRepositoryJsonDiff;

  /**
   * Calls repository to save left json
   *
   * @param entity
   */
  @Override
  public void saveLeftJson(WaesEntityLeftJsons entity) {
    waesRepositoryLeftJsons.save(entity);
  }

  /**
   * Calls repository to delete left json
   *
   * @param entity
   */
  @Override
  public void deleteLeftJson(WaesEntityLeftJsons entity) {
    waesRepositoryLeftJsons.delete(entity);
  }

  /**
   * Calls repository to update left json
   *
   * @param entity
   */
  @Override
  public void updateLeftJson(WaesEntityLeftJsons entity) {
    waesRepositoryLeftJsons.save(entity);
  }

  /**
   * Calls repository to retrieve left json by id
   *
   * @param id
   * @return WaesEntityLeftJsons
   * @throws Exception
   */
  @Override
  public WaesEntityLeftJsons retrieveLeftJson(String id) throws Exception {
    Optional<WaesEntityLeftJsons> waesEntityLeftJsons = waesRepositoryLeftJsons.findById(id);
    if (waesEntityLeftJsons.isPresent()) {
      return waesEntityLeftJsons.get();
    } else {
      throw new Exception("Unable to get object by id");
    }
  }

  /**
   * Calls repository to save right json
   *
   * @param entity
   */
  @Override
  public void saveRightJson(WaesEntityRightJsons entity) {
    waesRepositoryRightJsons.save(entity);
  }

  /**
   * Calls repository to delete right json
   *
   * @param entity
   */
  @Override
  public void deleteRightJson(WaesEntityRightJsons entity) {
    waesRepositoryRightJsons.delete(entity);
  }

  /**
   * Calls repository to update right json
   *
   * @param entity
   */
  @Override
  public void updateRightJson(WaesEntityRightJsons entity) {
    waesRepositoryRightJsons.save(entity);
  }

  /**
   * Calls repository to retrieve right json by id
   *
   * @param id
   * @return WaesEntityRightJsons
   * @throws Exception
   */
  @Override
  public WaesEntityRightJsons retrieveRightJson(String id) throws Exception {
    Optional<WaesEntityRightJsons> waesEntityRightJsons = waesRepositoryRightJsons.findById(id);
    if (waesEntityRightJsons.isPresent()) {
      return waesEntityRightJsons.get();
    } else {
      throw new Exception("Unable to get object by id");
    }
  }

  /**
   * Calls repository to save json diff results
   *
   * @param entity
   */
  @Override
  public void saveJsonDiff(WaesEntityJsonDiff entity) {
    waesRepositoryJsonDiff.save(entity);
  }

  /**
   * Calls repository to delete json diff results
   *
   * @param entity
   */
  @Override
  public void deleteJsonDiff(WaesEntityJsonDiff entity) {
    waesRepositoryJsonDiff.delete(entity);
  }

  /**
   * Contains the logic to get the left and right jsons by id, converts the jsons into maps so that
   * the Maps.difference from Guava can analyze the two maps and check for json differences,
   * returning a JsonObject with the results.
   *
   * @param id
   * @return JsonObject
   * @throws Exception
   */
  @Override
  public JsonObject getDiff(String id) throws Exception {

    if (StringUtils.isBlank(id)) {
      throw new Exception("Id cannot be empty or null");
    }

    WaesEntityLeftJsons waesEntityLeftJsons = waesRepositoryLeftJsons.retrieveLeftJsonById(id);
    WaesEntityRightJsons waesEntityRightJsons = waesRepositoryRightJsons.retrieveRightJsonById(id);

    if (waesEntityLeftJsons == null || waesEntityRightJsons == null) {
      throw new Exception("Retrieved entities cannot be null");
    }

    JsonObject leftJson = getLeftJsonObjectFromLeftJsonEntity(waesEntityLeftJsons);
    JsonObject rightJson = getRightJsonObjectFromRightJsonEntity(waesEntityRightJsons);

    Gson gson = new Gson();
    Type mapType = new TypeToken<Map<String, Object>>() {
    }.getType();
    Map<String, Object> leftJsonMap = gson.fromJson(leftJson, mapType);
    Map<String, Object> rightJsonMap = gson.fromJson(rightJson, mapType);

    System.out.println(Maps.difference(leftJsonMap, rightJsonMap));
    MapDifference<String, Object> differenceMap = Maps.difference(leftJsonMap, rightJsonMap);

    JsonObject jsonObject = new JsonObject();
    if ((leftJson.size() == rightJson.size())) {
      jsonObject.add("is_equal_size", new JsonPrimitive(true));
    } else {
      jsonObject.add("is_equal_size", new JsonPrimitive(false));
    }
    jsonObject.add("is_equal_content", new JsonPrimitive(differenceMap.areEqual()));

    //TODO: Manage Diffs Here
    Map<String, ValueDifference<Object>> different = differenceMap.entriesDiffering();
    Map<String, Object> common = differenceMap.entriesInCommon();

    return jsonObject;
  }

  /**
   * Creates WaesEntityLeftJsons from a JsonObject
   * @param id
   * @param encoded
   * @return WaesEntityLeftJsons
   * @throws Exception
   */
  @Override
  public WaesEntityLeftJsons createLeftJsonEntityFromJsonObject(String id, String encoded)
      throws Exception {

    String decodedJson = decodeBase64Json(encoded);

    if (isValidJson(decodedJson)) {

      WaesEntityLeftJsons waesEntityLeftJsons = new WaesEntityLeftJsons();
      waesEntityLeftJsons.setIdLeftJson(id);
      waesEntityLeftJsons.setStatus(WaesStatusEnum.NOT_PROCESSED.name());
      waesEntityLeftJsons.setDateCreated(LocalDateTime.now());
      waesEntityLeftJsons.setLeftJson(decodedJson);
      waesEntityLeftJsons.setBase64(encoded);
      return waesEntityLeftJsons;

    } else {
      throw new Exception("Decoded String is not a valid Json");
    }

  }

  /**
   * Creates WaesEntityRightJsons from a JsonObject
   * @param id
   * @param encoded
   * @return WaesEntityRightJsons
   * @throws Exception
   */
  @Override
  public WaesEntityRightJsons createRightJsonEntityFromJsonObject(String id, String encoded)
      throws Exception {

    String decodedJson = decodeBase64Json(encoded);

    if (isValidJson(decodedJson)) {
      WaesEntityRightJsons waesEntityRightJsons = new WaesEntityRightJsons();
      waesEntityRightJsons.setIdRightJson(id);
      waesEntityRightJsons.setStatus(WaesStatusEnum.NOT_PROCESSED.name());
      waesEntityRightJsons.setDateCreated(LocalDateTime.now());
      waesEntityRightJsons.setRighttJson(decodedJson);
      waesEntityRightJsons.setBase64(encoded);

      return waesEntityRightJsons;
    } else {
      throw new Exception("Decoded String is not a valid Json");
    }
  }

  /**
   * Creates WaesEntityJsonDiff from a JsonObject
   * @param id
   * @param json
   * @return WaesEntityJsonDiff
   */
  @Override
  public WaesEntityJsonDiff createWaesEntityJsonDiffFromJsonObject(String id, JsonObject json) {
    Gson gson = new Gson();
    WaesEntityJsonDiff waesEntityJsonDiff = new WaesEntityJsonDiff();
    waesEntityJsonDiff.setIdJsondiff(id);
    waesEntityJsonDiff.setDateCreated(LocalDateTime.now());
    waesEntityJsonDiff.setJsonDiffs(gson.toJson(json));

    return waesEntityJsonDiff;
  }

  /**
   * Creates JsonObject from WaesEntityLeftJsons
   * @param leftJsonEntity
   * @return JsonObject
   * @throws Exception
   */
  @Override
  public JsonObject getLeftJsonObjectFromLeftJsonEntity(WaesEntityLeftJsons leftJsonEntity)
      throws Exception {
    if (leftJsonEntity != null && StringUtils.isNotBlank(leftJsonEntity.getLeftJson())) {
      return stringToJsonObject(leftJsonEntity.getLeftJson());
    } else {
      LOG.error(
          "Error in WaesUtil::getLeftJsonObjectFromLeftJsonEntity, the retrieved json cannot be null");
      throw new Exception("the retrieved json was null");
    }
  }

  /**
   * Creates JsonObject from WaesEntityRightJsons
   * @param rightJsonEntity
   * @return JsonObject
   * @throws Exception
   */
  @Override
  public JsonObject getRightJsonObjectFromRightJsonEntity(WaesEntityRightJsons rightJsonEntity)
      throws Exception {

    if (rightJsonEntity != null && StringUtils.isNotBlank(rightJsonEntity.getRighttJson())) {
      return stringToJsonObject(rightJsonEntity.getRighttJson());
    } else {
      LOG.error(
          "Error in WaesUtil::getRightJsonObjectFromRightJsonEntity, the retrieved json cannot be null");
      throw new Exception("the retrieved json was null");
    }
  }

  /**
   * Updates status after diff was made for id
   * @param id
   */
  @Override
  public void updateStatusAfterProcessed(String id) {

    WaesEntityLeftJsons waesEntityLeftJsons = waesRepositoryLeftJsons.retrieveLeftJsonById(id);
    WaesEntityRightJsons waesEntityRightJsons = waesRepositoryRightJsons.retrieveRightJsonById(id);

    waesEntityLeftJsons.setStatus(WaesStatusEnum.PROCESSED.name());
    waesEntityLeftJsons.setDateUpdated(LocalDateTime.now());

    waesEntityRightJsons.setStatus(WaesStatusEnum.PROCESSED.name());
    waesEntityRightJsons.setDateUpdated(LocalDateTime.now());

    updateLeftJson(waesEntityLeftJsons);
    updateRightJson(waesEntityRightJsons);
  }

  /**
   * Decodes the Base 64 String
   * @param encoded
   * @return String
   */
  @Override
  public String decodeBase64Json(String encoded) {
    byte[] decodedBytes = java.util.Base64.getDecoder().decode(encoded);
    return new String(decodedBytes);

  }

  /**
   * Converts the Json String into s JsonObject
   * @param decoded
   * @return JsonObject
   */
  @Override
  public JsonObject stringToJsonObject(String decoded) {
    JsonObject json = new Gson().fromJson(decoded, JsonObject.class);
    return json;
  }

  /**
   * Checks if Json String is a valid Json
   * @param jsonInString
   * @return boolean
   */
  @Override
  public boolean isValidJson(String jsonInString) {
    try {
      Gson gson = new Gson();
      gson.fromJson(jsonInString, Object.class);
      return true;
    } catch (com.google.gson.JsonSyntaxException ex) {
      return false;
    }
  }
}
