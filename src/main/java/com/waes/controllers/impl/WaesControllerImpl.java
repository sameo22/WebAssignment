package com.waes.controllers.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.waes.controllers.WaesController;
import com.waes.entities.WaesEntityJsonDiff;
import com.waes.entities.WaesEntityLeftJsons;
import com.waes.entities.WaesEntityRightJsons;
import com.waes.services.WaesService;
import java.time.LocalDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/diff")
public class WaesControllerImpl implements WaesController {

  protected static final Logger LOG = LogManager.getLogger(WaesControllerImpl.class);

  @Autowired
  protected WaesService service;

  /**
   * Save left json
   * @param id         is the id to be saved in MySql
   * @param encoded    is a encoded json to be saved
   * @return String
   */
  @Override
  public String saveJsonLeft(String id, String encoded) {
    try {
      WaesEntityLeftJsons waesEntityLeftJsons = service.createLeftJsonEntityFromJsonObject(id, encoded);
      service.saveLeftJson(waesEntityLeftJsons);
      LOG.info("WAES Controller saved the left JsonObject correctly with id: {}", id);
      return "WAES Controller saved the left JsonObject correctly with id: " + id;
    } catch (Exception e) {
      LOG.error("Error in WaesControllerImpl::saveJsonLeft, Json id: {}", id, e);
      return "Error saving the left JsonObject with id: " + id + e;
    }
  }

  /**
   * Save right json
   * @param id         is the id to be saved in MySql
   * @param encoded    is a encoded json to be saved
   * @return String
   */
  @Override
  public String saveJsonRight(String id, String encoded) {

    try {
      WaesEntityRightJsons entity = service.createRightJsonEntityFromJsonObject(id, encoded);
      service.saveRightJson(entity);
      LOG.info("WAES Controller saved the right JsonObject correctly with id: {}", id);
      return "WAES Controller saved the right JsonObject correctly with id: " + id;
    } catch (Exception e) {
      LOG.error("Error in WaesControllerImpl::saveJsonRight, Json id: {}", id, e);
      return "Error saving the right JsonObject with id: " + id + e;
    }
  }

  /**
   * Calls the method to check differences in jsons by id
   * @param id is the id as in MySql
   * @return JsonObject
   */
  @Override
  public JsonObject getJsonDiff(String id) {
    try {

      LOG.info("WAES Controller getting the JsonObject with the differences");
      JsonObject jsonObject = service.getDiff(id);
      WaesEntityJsonDiff waesEntityJsonDiff = service.createWaesEntityJsonDiffFromJsonObject(id, jsonObject);
      service.saveJsonDiff(waesEntityJsonDiff);
      service.updateStatusAfterProcessed(id);

      LOG.info("Diff Json was saved with id: {}", id);
      return jsonObject;

    } catch (Exception e) {
      LOG.error("Error in WaesControllerImpl::getJsonDiff", e);
    }
    return null;
  }

  /**
   * Deletes left json by id
   * @param id         is the id to be deleted in MySql
   * @return String
   */
  @Override
  public String deleteJsonLeft(String id) {
    try {
      WaesEntityLeftJsons waesEntityLeftJsons = service.retrieveLeftJson(id);
      service.deleteLeftJson(waesEntityLeftJsons);
      LOG.info("WAES Controller successfully deleted the Left JsonObject with id: {}", id);
      return "WAES Controller deleted the left JsonObject correctly with id: " + id;
    } catch (Exception e) {
      LOG.error("Error in WaesControllerImpl::deleteJsonLeft", e);
      return "Error deleting the left JsonObject correctly with id: " + id + e;
    }
  }

  /**
   * Deletes right json by id
   * @param id         is the id to be deleted in MySql
   * @return String
   */
  @Override
  public String deleteJsonRight(String id) {
    try {
      WaesEntityRightJsons waesEntityRightJsons = service.retrieveRightJson(id);
      service.deleteRightJson(waesEntityRightJsons);
      LOG.info("WAES Controller successfully deleted the Right JsonObject with id: {}", id);
      return "WAES Controller deleted the right JsonObject correctly with id: " + id;
    } catch (Exception e) {
      LOG.error("Error in WaesControllerImpl::deleteJsonRight", e);
      return "Error deleting the right JsonObject correctly with id: " + id + e;
    }
  }

  /**
   * Updates left json
   * @param id         is the id to be updated in MySql
   * @param encoded    is a encoded json to be updated
   * @return String
   */
  @Override
  public String updateJsonLeft(String id, String encoded) {
    try {
      WaesEntityLeftJsons waesEntityLeftJsons = service.createLeftJsonEntityFromJsonObject(id, encoded);
      waesEntityLeftJsons.setDateUpdated(LocalDateTime.now());
      service.updateLeftJson(waesEntityLeftJsons);
      LOG.info("WAES Controller successfully updated the Left JsonObject with id: {}", id);
      return "WAES Controller updated the left JsonObject correctly with id: " + id;
    } catch (Exception e) {
      LOG.error("Error in WaesControllerImpl::updateJsonLeft", e);
      return "Error updating the left JsonObject correctly with id: " + id + e;
    }
  }

  /**
   * Updates right json
   * @param id         is the id to be updated in MySql
   * @param encoded    is a encoded json to be updated
   * @return String
   */
  @Override
  public String updateJsonRight(String id, String encoded) {
    try {
      WaesEntityRightJsons entity = service.createRightJsonEntityFromJsonObject(id, encoded);
      entity.setDateUpdated(LocalDateTime.now());
      service.updateRightJson(entity);
      LOG.info("WAES Controller successfully updated the Right JsonObject with id: {}", id);
      return "WAES Controller updated the right JsonObject correctly with id: " + id;
    } catch (Exception e) {
      LOG.error("Error in WaesControllerImpl::updateJsonRight", e);
      return "Error updating the right JsonObject correctly with id: " + id + e;
    }
  }

  /**
   * Returns the whole WaesEntityLeftJsons from id
   * @param id is the id as in MySql
   * @return WaesEntityLeftJsons
   * @throws Exception
   */
  @Override
  public WaesEntityLeftJsons getLeftJsonEntityWithId(String id) throws Exception {
    WaesEntityLeftJsons waesEntityLeftJsons = service.retrieveLeftJson(id);
    return waesEntityLeftJsons;
  }

  /**
   * Returns the whole WaesEntityRightJsons from id
   * @param id is the id as in MySql
   * @return WaesEntityRightJsons
   * @throws Exception
   */
  @Override
  public WaesEntityRightJsons getRightJsonEntityWithId(String id) throws Exception {
    WaesEntityRightJsons waesEntityRightJsons = service.retrieveRightJson(id);
    return waesEntityRightJsons;
  }

  /**
   * Returns the decoded valid json left from id
   * @param id is the id as in MySql
   * @return JsonObject
   * @throws Exception
   */
  @Override
  public JsonObject getLeftJsonWithId(String id) throws Exception {
    WaesEntityLeftJsons waesEntityLeftJsons = service.retrieveLeftJson(id);
    return new Gson().fromJson(waesEntityLeftJsons.getLeftJson(), JsonObject.class);
  }

  /**
   * Returns the decoded valid json right from id
   * @param id is the id as in MySql
   * @return JsonObject
   * @throws Exception
   */
  @Override
  public JsonObject getRightJsonWithId(String id) throws Exception {
    WaesEntityRightJsons waesEntityRightJsons = service.retrieveRightJson(id);
    return new Gson().fromJson(waesEntityRightJsons.getRighttJson(), JsonObject.class);
  }
}
