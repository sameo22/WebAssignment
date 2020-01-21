package com.waes.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import com.waes.entities.WaesEntityJsonDiff;
import com.waes.entities.WaesEntityLeftJsons;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("development")
@EnableAutoConfiguration(exclude = {JacksonAutoConfiguration.class})
@AutoConfigureTestDatabase(replace = NONE)
@DataJpaTest
public class WaesRepositoryJsonDiffTest {

  @Autowired
  private WaesRepositoryJsonDiff repository;

  /**
   * Test for WaesRepositoryJsonDiff to retrieve by id
   */
  @Test
  public void testRetrieveLeftJsonById() {
    WaesEntityJsonDiff waesEntityJsonDiff = repository.retrieveJsonDiffById("test1");
    assertNotNull(waesEntityJsonDiff);
  }

  /**
   * Test for WaesRepositoryJsonDiff to save
   */
  @Test
  public void testSave() {

    WaesEntityJsonDiff waesEntityJsonDiff = new WaesEntityJsonDiff();
    waesEntityJsonDiff.setIdJsondiff("testRepo1");
    waesEntityJsonDiff.setJsonDiffs("{\n"
        + "    \"is_equal_size\": true,\n"
        + "    \"is_equal_content\": true\n"
        + "}");

    repository.save(waesEntityJsonDiff);

    WaesEntityJsonDiff waesEntityJsonDiffRetrieved = repository.retrieveJsonDiffById("testRepo1");
    assertNotNull(waesEntityJsonDiffRetrieved);
  }


  /**
   * Test for WaesRepositoryJsonDiff to update
   */
  @Test
  public void testUpdate() {

    WaesEntityJsonDiff waesEntityJsonDiff = new WaesEntityJsonDiff();
    waesEntityJsonDiff.setIdJsondiff("testRepo2");
    waesEntityJsonDiff.setJsonDiffs("{\n"
        + "    \"is_equal_size\": true,\n"
        + "    \"is_equal_content\": true\n"
        + "}");
    repository.save(waesEntityJsonDiff);

    WaesEntityJsonDiff waesEntityJsonDiffRetrieved = repository.retrieveJsonDiffById("testRepo2");
    assertNotNull(waesEntityJsonDiffRetrieved);
    assertEquals("{\n"
        + "    \"is_equal_size\": true,\n"
        + "    \"is_equal_content\": true\n"
        + "}", waesEntityJsonDiffRetrieved.getJsonDiffs());
  }


  /**
   * Test for WaesRepositoryJsonDiff to delete
   */
  @Test
  public void testDelete() {
    WaesEntityJsonDiff waesEntityJsonDiff = new WaesEntityJsonDiff();
    waesEntityJsonDiff.setIdJsondiff("testRepo3");
    waesEntityJsonDiff.setJsonDiffs("{\n"
        + "    \"is_equal_size\": true,\n"
        + "    \"is_equal_content\": true\n"
        + "}");
    repository.save(waesEntityJsonDiff);
    repository.delete(waesEntityJsonDiff);

    WaesEntityJsonDiff waesEntityJsonDiffRetrieved = repository.retrieveJsonDiffById("testRepo3");
    assertNull(waesEntityJsonDiffRetrieved);
  }

}
