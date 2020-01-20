package com.waes.services.impl;

import com.google.gson.JsonObject;
import com.waes.entities.WaesEntityJsonDiff;
import com.waes.entities.WaesEntityLeftJsons;
import com.waes.entities.WaesEntityRightJsons;
import com.waes.repositories.WaesRepositoryJsonDiff;
import com.waes.repositories.WaesRepositoryLeftJsons;
import com.waes.repositories.WaesRepositoryRightJsons;
import com.waes.services.WaesService;
import com.waes.utils.WaesUtil;
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

    JsonObject leftJson = WaesUtil.getLeftJsonObjectFromLeftJsonEntity(waesEntityLeftJsons);
    JsonObject rightJson = WaesUtil.getRightJsonObjectFromRightJsonEntity(waesEntityRightJsons);

    return leftJson;
  }
}
