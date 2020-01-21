package com.waes.services;

import com.google.gson.JsonObject;
import com.waes.entities.WaesEntityJsonDiff;
import com.waes.entities.WaesEntityLeftJsons;
import com.waes.entities.WaesEntityRightJsons;

public interface WaesService {

  /**
   * Left json save
   *
   * @param entity
   */
  void saveLeftJson(WaesEntityLeftJsons entity);

  /**
   * Left json delete
   *
   * @param entity
   */
  void deleteLeftJson(WaesEntityLeftJsons entity);

  /**
   * Left json update
   *
   * @param entity
   */
  void updateLeftJson(WaesEntityLeftJsons entity);

  /**
   * Left json to be retrieved
   *
   * @param id
   */
  WaesEntityLeftJsons retrieveLeftJson(String id) throws Exception;

  /**
   * Right json save
   *
   * @param entity
   */
  void saveRightJson(WaesEntityRightJsons entity);

  /**
   * Right json delete
   *
   * @param entity
   */
  void deleteRightJson(WaesEntityRightJsons entity);

  /**
   * Right json update
   *
   * @param entity
   */
  void updateRightJson(WaesEntityRightJsons entity);

  /**
   * Left json to be retrieved
   *
   * @param id
   */
  WaesEntityRightJsons retrieveRightJson(String id) throws Exception;

  /**
   * Json Diff save
   *
   * @param entity
   */
  void saveJsonDiff(WaesEntityJsonDiff entity);

  /**
   * Json Diff delete
   *
   * @param entity
   */
  void deleteJsonDiff(WaesEntityJsonDiff entity);

  /**
   * Contains the logic to get the left and right jsons by id, converts the jsons into maps so that
   * the Maps.difference from Guava can analyze the two maps and check for json differences,
   * returning a JsonObject with the results.
   *
   * @param id
   * @return JsonObject
   * @throws Exception
   */
  JsonObject getDiff(String id) throws Exception;

  /**
   * Creates WaesEntityLeftJsons from a JsonObject
   * @param id
   * @param encoded
   * @return WaesEntityLeftJsons
   * @throws Exception
   */
  WaesEntityLeftJsons createLeftJsonEntityFromJsonObject(String id, String encoded)
      throws Exception;

  /**
   * Creates WaesEntityRightJsons from a JsonObject
   * @param id
   * @param encoded
   * @return WaesEntityRightJsons
   * @throws Exception
   */
  WaesEntityRightJsons createRightJsonEntityFromJsonObject(String id, String encoded)
      throws Exception;

  /**
   * Creates WaesEntityJsonDiff from a JsonObject
   * @param id
   * @param json
   * @return WaesEntityJsonDiff
   */
  WaesEntityJsonDiff createWaesEntityJsonDiffFromJsonObject(String id, JsonObject json);

  /**
   * Creates JsonObject from WaesEntityLeftJsons
   * @param leftJsonEntity
   * @return JsonObject
   * @throws Exception
   */
  JsonObject getLeftJsonObjectFromLeftJsonEntity(WaesEntityLeftJsons leftJsonEntity)
      throws Exception;

  /**
   * Creates JsonObject from WaesEntityRightJsons
   * @param rightJsonEntity
   * @return JsonObject
   * @throws Exception
   */
  JsonObject getRightJsonObjectFromRightJsonEntity(WaesEntityRightJsons rightJsonEntity)
      throws Exception;

  /**
   * Updates status after diff was made for id
   * @param id
   */
  void updateStatusAfterProcessed(String id);

  /**
   * Decodes the Base 64 String
   * @param encoded
   * @return String
   */
  String decodeBase64Json(String encoded);

  /**
   * Converts the Json String into s JsonObject
   * @param decoded
   * @return JsonObject
   */
  JsonObject stringToJsonObject(String decoded);

  /**
   * Checks if Json String is a valid Json
   * @param jsonInString
   * @return boolean
   */
  boolean isValidJson(String jsonInString);

}
