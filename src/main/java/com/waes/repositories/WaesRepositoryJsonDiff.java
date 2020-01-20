package com.waes.repositories;

import com.waes.entities.WaesEntityJsonDiff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaesRepositoryJsonDiff extends CrudRepository<WaesEntityJsonDiff, String> {

  @Query(value = "select id_json_diff, json_diffs, date_created from waes_json_diffs where id_json_diff = ?1", nativeQuery = true)
  WaesEntityJsonDiff retrieveJsonDiffById(String ID);

}

