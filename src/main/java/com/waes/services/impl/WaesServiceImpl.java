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

  @Override
  public void saveLeftJson(WaesEntityLeftJsons entity) {
    waesRepositoryLeftJsons.save(entity);
  }

  @Override
  public void deleteLeftJson(WaesEntityLeftJsons entity) {
    waesRepositoryLeftJsons.delete(entity);
  }

  @Override
  public void updateLeftJson(WaesEntityLeftJsons entity) {
    waesRepositoryLeftJsons.save(entity);
  }

  @Override
  public WaesEntityLeftJsons retrieveLeftJson(String id) throws Exception {
    Optional<WaesEntityLeftJsons> waesEntityLeftJsons = waesRepositoryLeftJsons.findById(id);
    if(waesEntityLeftJsons.isPresent()){
      return waesEntityLeftJsons.get();
    }else{
      throw new Exception("Unable to get objet by id");
    }
  }

  @Override
  public void saveRightJson(WaesEntityRightJsons entity) {
    waesRepositoryRightJsons.save(entity);
  }

  @Override
  public void deleteRightJson(WaesEntityRightJsons entity) {
    waesRepositoryRightJsons.delete(entity);
  }

  @Override
  public void updateRightJson(WaesEntityRightJsons entity) {
    waesRepositoryRightJsons.save(entity);
  }

  @Override
  public WaesEntityRightJsons retrieveRightJson(String id) throws Exception {
    Optional<WaesEntityRightJsons> waesEntityRightJsons = waesRepositoryRightJsons.findById(id);
    if(waesEntityRightJsons.isPresent()){
      return waesEntityRightJsons.get();
    }else{
      throw new Exception("Unable to get object by id");
    }
  }

  @Override
  public void saveJsonDiff(WaesEntityJsonDiff entity) {
    waesRepositoryJsonDiff.save(entity);
  }

  @Override
  public void deleteJsonDiff(WaesEntityJsonDiff entity) {
    waesRepositoryJsonDiff.delete(entity);
  }

  @Override
  public void updateJsonDiff(WaesEntityJsonDiff entity) {
    waesRepositoryJsonDiff.save(entity);
  }

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
    Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
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

  @Override
  public WaesEntityLeftJsons createLeftJsonEntityFromJsonObject(String id, JsonObject leftJson) {
    WaesEntityLeftJsons waesEntityLeftJsons = new WaesEntityLeftJsons();
    waesEntityLeftJsons.setIdLeftJson(id);
    waesEntityLeftJsons.setStatus(WaesStatusEnum.NOT_PROCESSED.name());
    waesEntityLeftJsons.setDateCreated(LocalDateTime.now());
    waesEntityLeftJsons.setLeftJson(leftJson.toString());

    return waesEntityLeftJsons;
  }

  @Override
  public WaesEntityRightJsons createRightJsonEntityFromJsonObject(String id, JsonObject rightJson) {
    WaesEntityRightJsons waesEntityRightJsons = new WaesEntityRightJsons();
    waesEntityRightJsons.setIdRightJson(id);
    waesEntityRightJsons.setStatus(WaesStatusEnum.NOT_PROCESSED.name());
    waesEntityRightJsons.setDateCreated(LocalDateTime.now());
    waesEntityRightJsons.setRighttJson(rightJson.toString());

    return waesEntityRightJsons;
  }

  @Override
  public WaesEntityJsonDiff createWaesEntityJsonDiffFromJsonObject(String id, JsonObject json) {
    Gson gson = new Gson();
    WaesEntityJsonDiff waesEntityJsonDiff = new WaesEntityJsonDiff();
    waesEntityJsonDiff.setIdJsondiff(id);
    waesEntityJsonDiff.setDateCreated(LocalDateTime.now());
    waesEntityJsonDiff.setJsonDiffs(gson.toJson(json));

    return waesEntityJsonDiff;
  }

  @Override
  public JsonObject getLeftJsonObjectFromLeftJsonEntity(WaesEntityLeftJsons leftJsonEntity)
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

  @Override
  public JsonObject getRightJsonObjectFromRightJsonEntity(WaesEntityRightJsons rightJsonEntity)
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

  @Override
  public void updateStatusAfterProcessed(String id){

    WaesEntityLeftJsons waesEntityLeftJsons = waesRepositoryLeftJsons.retrieveLeftJsonById(id);
    WaesEntityRightJsons waesEntityRightJsons = waesRepositoryRightJsons.retrieveRightJsonById(id);

    waesEntityLeftJsons.setStatus(WaesStatusEnum.PROCESSED.name());
    waesEntityLeftJsons.setDateUpdated(LocalDateTime.now());

    waesEntityRightJsons.setStatus(WaesStatusEnum.PROCESSED.name());
    waesEntityRightJsons.setDateUpdated(LocalDateTime.now());

    updateLeftJson(waesEntityLeftJsons);
    updateRightJson(waesEntityRightJsons);
  }
}
