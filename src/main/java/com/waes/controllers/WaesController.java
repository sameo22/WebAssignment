package com.waes.controllers;

import com.google.gson.JsonObject;
import com.waes.entities.WaesEntityLeftJsons;
import com.waes.entities.WaesEntityRightJsons;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public interface WaesController {

  /**
   * Save left json
   * @param id         is the id to be saved in MySql
   * @param encoded    is a encoded json to be saved
   * @return String
   */
  @PostMapping("{ID}/left")
  String saveJsonLeft(@PathVariable("ID") String id,
      @RequestBody String encoded);

  /**
   * Save right json
   * @param id         is the id to be saved in MySql
   * @param encoded    is a encoded json to be saved
   * @return String
   */
  @PostMapping("{ID}/right")
  String saveJsonRight(@PathVariable("ID") String id,
      @RequestBody String encoded);

  /**
   * Calls the method to check differences in jsons by id
   * @param id is the id as in MySql
   * @return JsonObject
   */
  @GetMapping("{ID}")
  @ResponseBody
  JsonObject getJsonDiff(
      @PathVariable("ID") String id);

  /**
   * Deletes left json by id
   * @param id         is the id to be deleted in MySql
   * @return String
   */
  @PostMapping("delete/{ID}/left")
  String deleteJsonLeft(@PathVariable("ID") String id);

  /**
   * Deletes right json by id
   * @param id         is the id to be deleted in MySql
   * @return String
   */
  @PostMapping("delete/{ID}/right")
  String deleteJsonRight(@PathVariable("ID") String id);

  /**
   * Updates left json
   * @param id         is the id to be updated in MySql
   * @param encoded    is a encoded json to be updated
   * @return String
   */
  @PostMapping("update/{ID}/left")
  String updateJsonLeft(@PathVariable("ID") String id,
      @RequestBody String encoded);

  /**
   * Updates right json
   * @param id         is the id to be updated in MySql
   * @param encoded    is a encoded json to be updated
   * @return String
   */
  @PostMapping("update/{ID}/right")
  String updateJsonRight(@PathVariable("ID") String id,
      @RequestBody String encoded);

  /**
   * Returns the whole WaesEntityLeftJsons from id
   * @param id is the id as in MySql
   * @return WaesEntityLeftJsons
   * @throws Exception
   */
  @GetMapping("retrieveEntity/{ID}/left")
  @ResponseBody
  WaesEntityLeftJsons getLeftJsonEntityWithId(
      @PathVariable("ID") String id) throws Exception;

  /**
   * Returns the whole WaesEntityRightJsons from id
   * @param id is the id as in MySql
   * @return WaesEntityRightJsons
   * @throws Exception
   */
  @GetMapping("retrieveEntity/{ID}/right")
  @ResponseBody
  WaesEntityRightJsons getRightJsonEntityWithId(
      @PathVariable("ID") String id) throws Exception;

  /**
   * Returns the decoded valid json left from id
   * @param id is the id as in MySql
   * @return JsonObject
   * @throws Exception
   */
  @GetMapping("retrieveJson/{ID}/left")
  @ResponseBody
  JsonObject getLeftJsonWithId(
      @PathVariable("ID") String id) throws Exception;

  /**
   * Returns the decoded valid json right from id
   * @param id is the id as in MySql
   * @return JsonObject
   * @throws Exception
   */
  @GetMapping("retrieveJson/{ID}/right")
  @ResponseBody
  JsonObject getRightJsonWithId(
      @PathVariable("ID") String id) throws Exception;

}
