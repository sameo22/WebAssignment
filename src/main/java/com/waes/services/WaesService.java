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
   * Json Diff update
   *
   * @param entity
   */
  void updateJsonDiff(WaesEntityJsonDiff entity);

  /**
   * Logic for json comparison based in id
   *
   * @param id
   * @return
   */
  JsonObject getDiff(String id) throws Exception;

  WaesEntityLeftJsons createLeftJsonEntityFromJsonObject(String id, JsonObject leftJson);

  WaesEntityRightJsons createRightJsonEntityFromJsonObject(String id, JsonObject rightJson);

  WaesEntityJsonDiff createWaesEntityJsonDiffFromJsonObject(String id, JsonObject json);

  JsonObject getLeftJsonObjectFromLeftJsonEntity(WaesEntityLeftJsons leftJsonEntity)
      throws Exception;

  JsonObject getRightJsonObjectFromRightJsonEntity(WaesEntityRightJsons rightJsonEntity)
      throws Exception;

  public void updateStatusAfterProcessed(String id);

}
