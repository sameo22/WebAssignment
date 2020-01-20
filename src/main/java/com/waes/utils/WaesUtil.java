package com.waes.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.waes.entities.WaesEntityJsonDiff;
import com.waes.entities.WaesEntityLeftJsons;
import com.waes.entities.WaesEntityRightJsons;
import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WaesUtil {

  protected static final Logger LOG = LogManager.getLogger(WaesUtil.class);

  /**
   * transforms a JsonObject into a WaesEntityLeftJsons entity
   */
  public static WaesEntityLeftJsons createLeftJsonEntityFromJsonObject(String id,
      JsonObject leftJson) {
    WaesEntityLeftJsons waesEntityLeftJsons = new WaesEntityLeftJsons();
    waesEntityLeftJsons.setIdLeftJson(id);
    waesEntityLeftJsons.setStatus(WaesStatusEnum.PROCESSED.name());
    waesEntityLeftJsons.setDateSaved(LocalDateTime.now());
    waesEntityLeftJsons.setLeftJson(leftJson.toString());

    return waesEntityLeftJsons;
  }

  /**
   * transforms a JsonObject into a WaesEntityRightJsons entity
   */
  public static WaesEntityRightJsons createRightJsonEntityFromJsonObject(String id,
      JsonObject rightJson) {
    WaesEntityRightJsons waesEntityRightJsons = new WaesEntityRightJsons();
    waesEntityRightJsons.setIdRightJson(id);
    waesEntityRightJsons.setStatus(WaesStatusEnum.PROCESSED.name());
    waesEntityRightJsons.setDateSaved(LocalDateTime.now());
    waesEntityRightJsons.setRighttJson(rightJson.toString());

    return waesEntityRightJsons;
  }

  /**
   * transforms a JsonObject into a WaesEntityJsonDiff entity
   */
  public static WaesEntityJsonDiff createWaesEntityJsonDiffFromJsonObject(String id,
      JsonObject rightJson) {
    WaesEntityJsonDiff waesEntityJsonDiff = new WaesEntityJsonDiff();
    waesEntityJsonDiff.setIdJsondiff(id);
    waesEntityJsonDiff.setDateSaved(LocalDateTime.now());

    return waesEntityJsonDiff;
  }


  /**
   * gets the JsonObject from the retrieved LeftEntity
   */
  public static JsonObject getLeftJsonObjectFromLeftJsonEntity(WaesEntityLeftJsons leftJsonEntity)
      throws Exception {

    if (leftJsonEntity != null && StringUtils.isNotBlank(leftJsonEntity.getLeftJson())) {
      JsonObject convertedObject = new Gson()
          .fromJson(leftJsonEntity.getLeftJson(), JsonObject.class);
      return convertedObject;
    } else {
      LOG.error(
          "Error in WaesUtil::getLeftJsonObjectFromLeftJsonEntity, the retrieved json cannot be null");
      throw new Exception("the retrieved json was null");
    }
  }

  /**
   * gets the JsonObject from the retrieved RightEntity
   */
  public static JsonObject getRightJsonObjectFromRightJsonEntity(
      WaesEntityRightJsons rightJsonEntity)
      throws Exception {

    if (rightJsonEntity != null && StringUtils.isNotBlank(rightJsonEntity.getRighttJson())) {
      JsonObject convertedObject = new Gson()
          .fromJson(rightJsonEntity.getRighttJson(), JsonObject.class);
      return convertedObject;
    } else {
      LOG.error(
          "Error in WaesUtil::getRightJsonObjectFromRightJsonEntity, the retrieved json cannot be null");
      throw new Exception("the retrieved json was null");
    }
  }
}
