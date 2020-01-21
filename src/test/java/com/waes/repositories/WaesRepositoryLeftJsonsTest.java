package com.waes.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

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
public class WaesRepositoryLeftJsonsTest {

  @Autowired
  private WaesRepositoryLeftJsons repository;

  /**
   * Test for WaesRepositoryLeftJsons to retrieve by id
   */
  @Test
  public void testRetrieveLeftJsonById()
  {
    WaesEntityLeftJsons waesEntityLeftJsons = repository.retrieveLeftJsonById("test1");
    assertNotNull(waesEntityLeftJsons);
  }

  /**
   * Test for WaesRepositoryLeftJsons to save
   */
  @Test
  public void testSave()
  {

    WaesEntityLeftJsons waesEntityLeftJsons = new WaesEntityLeftJsons();
    waesEntityLeftJsons.setIdLeftJson("testRepo1");
    waesEntityLeftJsons.setBase64("eyANCiAgICJuYW1lIjoidGVzdCIsDQogICAiamF2YSI6dHJ1ZQ0KfQ==");

    repository.save(waesEntityLeftJsons);

    WaesEntityLeftJsons waesEntityLeftJsonsRetrieved = repository.retrieveLeftJsonById("testRepo1");
    assertNotNull(waesEntityLeftJsonsRetrieved);
  }


  /**
   * Test for WaesRepositoryLeftJsons to update
   */
  @Test
  public void testUpdate()
  {

    WaesEntityLeftJsons waesEntityLeftJsons = new WaesEntityLeftJsons();
    waesEntityLeftJsons.setIdLeftJson("testRepo2");
    waesEntityLeftJsons.setBase64("eyCiAgICJuYW1lIjoidGVzdCIsDQogICAiamF2YSI6dHJ1ZQ0KfQ==");

    repository.save(waesEntityLeftJsons);

    WaesEntityLeftJsons waesEntityLeftJsonsRetrieved = repository.retrieveLeftJsonById("testRepo2");
    assertNotNull(waesEntityLeftJsonsRetrieved);
    assertEquals("eyCiAgICJuYW1lIjoidGVzdCIsDQogICAiamF2YSI6dHJ1ZQ0KfQ==", waesEntityLeftJsonsRetrieved.getBase64());
  }


  /**
   * Test for WaesRepositoryLeftJsons to delete
   */
  @Test
  public void testDelete()
  {
    WaesEntityLeftJsons waesEntityLeftJsons = new WaesEntityLeftJsons();
    waesEntityLeftJsons.setIdLeftJson("testRepo3");
    waesEntityLeftJsons.setBase64("eyCiAgICJuYW1lIjoidGVzdCIsDQogICAiamF2YSI6dHJ1ZQ0KfQ==");

    repository.save(waesEntityLeftJsons);
    repository.delete(waesEntityLeftJsons);

    WaesEntityLeftJsons waesEntityLeftJsonsRetrieved = repository.retrieveLeftJsonById("testRepo3");
    assertNull(waesEntityLeftJsonsRetrieved);
  }

}
