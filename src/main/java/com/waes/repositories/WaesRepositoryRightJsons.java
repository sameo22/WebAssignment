package com.waes.repositories;

import com.waes.entities.WaesEntityRightJsons;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaesRepositoryRightJsons extends CrudRepository<WaesEntityRightJsons, String> {

  /**
   * Retrieves all data from waes_right_jsons by id
   * @param ID
   * @return WaesEntityRightJsons
   */
  @Query(value = "select id_right_json, right_json, status, date_created, date_updated, base_64 from waes_right_jsons where id_right_json = ?1", nativeQuery = true)
  WaesEntityRightJsons retrieveRightJsonById(String ID);

}

