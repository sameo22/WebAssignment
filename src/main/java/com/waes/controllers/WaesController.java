package com.waes.controllers;

import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public interface WaesController {

  /**
   * @param id         is the id as in MySql
   * @param jsonObject is a JsonObject to be diffed
   */
  @PostMapping("{ID}/left")
  void saveJsonLeft(@PathVariable("ID") String id,
      @RequestBody JsonObject jsonObject);

  /**
   * @param id         is the id as in MySql
   * @param jsonObject is a JsonObject to be diffed
   */
  @PostMapping("{ID}/right")
  void saveJsonRight(@PathVariable("ID") String id,
      @RequestBody JsonObject jsonObject);

  /**
   * @param id is the id as in MySql
   * @return a JsonObject containing the diff results
   */
  @GetMapping("{ID}")
  @ResponseBody
  JsonObject getJsonDiff(
      @PathVariable("ID") String id);


}
