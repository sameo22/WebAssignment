package com.waes.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import com.waes.entities.WaesEntityRightJsons;
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
public class WaesRepositoryRightJsonsTest {

  @Autowired
  private WaesRepositoryRightJsons repository;

  /**
   * Test for WaesRepositoryRightJsons to retrieve by id
   */
  @Test
  public void testRetrieveRightJsonById()
  {
    WaesEntityRightJsons wesEntityRightJsons = repository.retrieveRightJsonById("test1");
    assertNotNull(wesEntityRightJsons);
  }

  /**
   * Test for WaesRepositoryRightJsons to save
   */
  @Test
  public void testSave()
  {

    WaesEntityRightJsons wesEntityRightJsons = new WaesEntityRightJsons();
    wesEntityRightJsons.setIdRightJson("testRepo1");
    wesEntityRightJsons.setBase64("eyANCiAgICJuYW1lIjoidGVzdCIsDQogICAiamF2YSI6dHJ1ZQ0KfQ==");

    repository.save(wesEntityRightJsons);

    WaesEntityRightJsons wesEntityRightJsonsRetrieved = repository.retrieveRightJsonById("testRepo1");
    assertNotNull(wesEntityRightJsonsRetrieved);
  }


  /**
   * Test for WaesRepositoryRightJsons to update
   */
  @Test
  public void testUpdate()
  {

    WaesEntityRightJsons wesEntityRightJsons = new WaesEntityRightJsons();
    wesEntityRightJsons.setIdRightJson("testRepo2");
    wesEntityRightJsons.setBase64("eyCiAgICJuYW1lIjoidGVzdCIsDQogICAiamF2YSI6dHJ1ZQ0KfQ==");

    repository.save(wesEntityRightJsons);

    WaesEntityRightJsons wesEntityRightJsonsRetrieved = repository.retrieveRightJsonById("testRepo2");
    assertNotNull(wesEntityRightJsonsRetrieved);
    assertEquals("eyCiAgICJuYW1lIjoidGVzdCIsDQogICAiamF2YSI6dHJ1ZQ0KfQ==", wesEntityRightJsonsRetrieved.getBase64());
  }


  /**
   * Test for WaesRepositoryRightJsons to delete
   */
  @Test
  public void testDelete()
  {
    WaesEntityRightJsons wesEntityRightJsons = new WaesEntityRightJsons();
    wesEntityRightJsons.setIdRightJson("testRepo3");
    wesEntityRightJsons.setBase64("eyCiAgICJuYW1lIjoidGVzdCIsDQogICAiamF2YSI6dHJ1ZQ0KfQ==");

    repository.save(wesEntityRightJsons);
    repository.delete(wesEntityRightJsons);

    WaesEntityRightJsons wesEntityRightJsonsRetrieved = repository.retrieveRightJsonById("testRepo3");
    assertNull(wesEntityRightJsonsRetrieved);
  }

}
