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
   * @param id         is the id to be saved in MySql
   * @param jsonObject is a JsonObject to be saved
   */
  @PostMapping("{ID}/left")
  String saveJsonLeft(@PathVariable("ID") String id,
      @RequestBody JsonObject jsonObject);

  /**
   * @param id         is the id to be saved in MySql
   * @param jsonObject is a JsonObject to be saved
   */
  @PostMapping("{ID}/right")
  String saveJsonRight(@PathVariable("ID") String id,
      @RequestBody JsonObject jsonObject);

  /**
   * @param id is the id as in MySql
   * @return a JsonObject containing the diff results
   */
  @GetMapping("{ID}")
  @ResponseBody
  JsonObject getJsonDiff(
      @PathVariable("ID") String id);

  /**
   * @param id         is the id to be deleted in MySql
   */
  @PostMapping("delete/{ID}/left")
  String deleteJsonLeft(@PathVariable("ID") String id);

  /**
   * @param id         is the id to be deleted in MySql
   */
  @PostMapping("delete/{ID}/right")
  String deleteJsonRight(@PathVariable("ID") String id);

  /**
   * @param id         is the id to be updated in MySql
   * @param jsonObject is a JsonObject to be updated with
   */
  @PostMapping("update/{ID}/left")
  String updateJsonLeft(@PathVariable("ID") String id,
      @RequestBody JsonObject jsonObject);

  /**
   * @param id         is the id to be updated in MySql
   * @param jsonObject is a JsonObject to be updated with
   */
  @PostMapping("update/{ID}/right")
  String updateJsonRight(@PathVariable("ID") String id,
      @RequestBody JsonObject jsonObject);

  /**
   * @param id is the id as in MySql
   * @return WaesEntityLeftJsons the whole entity saved containing the saved json
   */
  @GetMapping("retrieveEntity/{ID}/left")
  @ResponseBody
  WaesEntityLeftJsons getLeftJsonEntityWithId(
      @PathVariable("ID") String id) throws Exception;

  /**
   * @param id is the id as in MySql
   * @return WaesEntityRightJsons the whole entity saved containing the saved json
   */
  @GetMapping("retrieveEntity/{ID}/right")
  @ResponseBody
  WaesEntityRightJsons getRightJsonEntityWithId(
      @PathVariable("ID") String id) throws Exception;

  /**
   * @param id is the id as in MySql
   * @return JsonObject the whole entity saved containing the saved json
   */
  @GetMapping("retrieveJson/{ID}/left")
  @ResponseBody
  JsonObject getLeftJsonWithId(
      @PathVariable("ID") String id) throws Exception;

  /**
   * @param id is the id as in MySql
   * @return the whole entity saved containing the saved json
   */
  @GetMapping("retrieveJson/{ID}/right")
  @ResponseBody
  JsonObject getRightJsonWithId(
      @PathVariable("ID") String id) throws Exception;

}
