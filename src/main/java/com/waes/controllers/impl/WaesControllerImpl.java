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

  @Override
  public String saveJsonLeft(String id, JsonObject jsonObject) {
    try {
      WaesEntityLeftJsons entity = service.createLeftJsonEntityFromJsonObject(id, jsonObject);
      service.saveLeftJson(entity);
      LOG.info("WAES Controller saved the left JsonObject correctly with id: {}", id);
      return "WAES Controller saved the left JsonObject correctly with id: " + id;
    } catch (Exception e) {
      LOG.error("Error in WaesControllerImpl::saveJsonLeft, Json id: {}", id, e);
      return "Error saving the left JsonObject with id: " + id;
    }
  }

  @Override
  public String saveJsonRight(String id, JsonObject jsonObject) {

    try {
      WaesEntityRightJsons entity = service.createRightJsonEntityFromJsonObject(id, jsonObject);
      service.saveRightJson(entity);
      LOG.info("WAES Controller saved the right JsonObject correctly with id: {}", id);
      return "WAES Controller saved the right JsonObject correctly with id: " + id;
    } catch (Exception e) {
      LOG.error("Error in WaesControllerImpl::saveJsonRight, Json id: {}", id, e);
      return "Error saving the right JsonObject with id: " + id;
    }
  }

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

  @Override
  public String deleteJsonLeft(String id) {
    try {
      WaesEntityLeftJsons waesEntityLeftJsons = service.retrieveLeftJson(id);
      service.deleteLeftJson(waesEntityLeftJsons);
      LOG.info("WAES Controller successfully deleted the Left JsonObject with id: {}", id);
      return "WAES Controller deleted the left JsonObject correctly with id: " + id;
    } catch (Exception e) {
      LOG.error("Error in WaesControllerImpl::deleteJsonLeft", e);
      return "Error deleting the left JsonObject correctly with id: " + id;
    }
  }

  @Override
  public String deleteJsonRight(String id) {
    try {
      WaesEntityRightJsons waesEntityRightJsons = service.retrieveRightJson(id);
      service.deleteRightJson(waesEntityRightJsons);
      LOG.info("WAES Controller successfully deleted the Right JsonObject with id: {}", id);
      return "WAES Controller deleted the right JsonObject correctly with id: " + id;
    } catch (Exception e) {
      LOG.error("Error in WaesControllerImpl::deleteJsonRight", e);
      return "Error deleting the right JsonObject correctly with id: " + id;
    }
  }

  @Override
  public String updateJsonLeft(String id, JsonObject jsonObject) {
    try {
      WaesEntityLeftJsons entity = service.createLeftJsonEntityFromJsonObject(id, jsonObject);
      entity.setDateUpdated(LocalDateTime.now());
      service.updateLeftJson(entity);
      LOG.info("WAES Controller successfully updated the Left JsonObject with id: {}", id);
      return "WAES Controller updated the left JsonObject correctly with id: " + id;
    } catch (Exception e) {
      LOG.error("Error in WaesControllerImpl::updateJsonLeft", e);
      return "Error updating the left JsonObject correctly with id: " + id;
    }
  }

  @Override
  public String updateJsonRight(String id, JsonObject jsonObject) {
    try {
      WaesEntityRightJsons entity = service.createRightJsonEntityFromJsonObject(id, jsonObject);
      entity.setDateUpdated(LocalDateTime.now());
      service.updateRightJson(entity);
      LOG.info("WAES Controller successfully updated the Right JsonObject with id: {}", id);
      return "WAES Controller updated the right JsonObject correctly with id: " + id;
    } catch (Exception e) {
      LOG.error("Error in WaesControllerImpl::updateJsonRight", e);
      return "Error updating the right JsonObject correctly with id: " + id;
    }
  }

  @Override
  public WaesEntityLeftJsons getLeftJsonEntityWithId(String id) throws Exception {
    WaesEntityLeftJsons waesEntityLeftJsons = service.retrieveLeftJson(id);
    return waesEntityLeftJsons;
  }

  @Override
  public WaesEntityRightJsons getRightJsonEntityWithId(String id) throws Exception {
    WaesEntityRightJsons waesEntityRightJsons = service.retrieveRightJson(id);
    return waesEntityRightJsons;
  }

  @Override
  public JsonObject getLeftJsonWithId(String id) throws Exception {
    WaesEntityLeftJsons waesEntityLeftJsons = service.retrieveLeftJson(id);
    return new Gson().fromJson(waesEntityLeftJsons.getLeftJson(), JsonObject.class);
  }

  @Override
  public JsonObject getRightJsonWithId(String id) throws Exception {
    WaesEntityRightJsons waesEntityRightJsons = service.retrieveRightJson(id);
    return new Gson().fromJson(waesEntityRightJsons.getRighttJson(), JsonObject.class);
  }
}
