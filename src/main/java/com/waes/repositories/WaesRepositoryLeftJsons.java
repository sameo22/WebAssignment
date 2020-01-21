package com.waes.repositories;

import com.waes.entities.WaesEntityLeftJsons;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaesRepositoryLeftJsons extends CrudRepository<WaesEntityLeftJsons, String> {

  /**
   * Retrieves all data from waes_left_jsons by id
   * @param ID
   * @return WaesEntityLeftJsons
   */
  @Query(value = "select id_left_json, left_json, status, date_created, date_updated, base_64 from waes_left_jsons where id_left_json = ?1", nativeQuery = true)
  WaesEntityLeftJsons retrieveLeftJsonById(String ID);

}

