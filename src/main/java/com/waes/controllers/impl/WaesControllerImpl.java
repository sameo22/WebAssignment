package com.waes.controllers.impl;

import com.google.gson.JsonObject;
import com.waes.controllers.WaesController;
import com.waes.entities.WaesEntityLeftJsons;
import com.waes.entities.WaesEntityRightJsons;
import com.waes.services.WaesService;
import com.waes.utils.WaesUtil;
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
  public void saveJsonLeft(String id, JsonObject jsonObject) {
    try {

      WaesEntityLeftJsons entity = WaesUtil.createLeftJsonEntityFromJsonObject(id, jsonObject);
      service.saveLeftJson(entity);
      LOG.info("WAES Controller saved the left JsonObject correctly with id: {}", id);

    } catch (Exception e) {
      LOG.error("Error in WaesControllerImpl::getJsonLeft, Json id: {}", id, e);
    }
  }

  @Override
  public void saveJsonRight(String id, JsonObject jsonObject) {

    try {
      WaesEntityRightJsons entity = WaesUtil.createRightJsonEntityFromJsonObject(id, jsonObject);
      service.saveRightJson(entity);
      LOG.info("WAES Controller saved the right JsonObject correctly with id: {}", id);

    } catch (Exception e) {
      LOG.error("Error in WaesControllerImpl::getJsonRight, Json id: {}", id, e);
    }
  }

  @Override
  public JsonObject getJsonDiff(String id) {
    try {

      LOG.info("WAES Controller getting the JsonObject with the differences");
      //TODO: The return is not an json object, create a RESPONSE for this based in a json object, return if equal in content?, if equal size? if equal size provide info in where the diffs are
      JsonObject jsonObject = service.getDiff(id);
      System.out.println(jsonObject);

    } catch (Exception e) {
      LOG.error("Error in WaesControllerImpl::getJsonDiff", e);
    }
    return null;
  }
}
