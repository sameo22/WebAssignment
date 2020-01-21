package com.waes.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "waes_left_jsons")
public class WaesEntityLeftJsons {

  @Id
  @Column(name = "id_left_json")
  private String idLeftJson;

  @Column(name = "left_json", length = 1024 * 1024 * 1024)
  private String leftJson;

  @Column(name = "status")
  private String status;

  @Column(name = "date_created")
  private LocalDateTime dateCreated;

  @Column(name = "date_updated")
  private LocalDateTime dateUpdated;

  @Column(name = "base_64")
  private String base64;

  public String getIdLeftJson() {
    return idLeftJson;
  }

  public void setIdLeftJson(String idLeftJson) {
    this.idLeftJson = idLeftJson;
  }

  public String getLeftJson() {
    return leftJson;
  }

  public void setLeftJson(String leftJson) {
    this.leftJson = leftJson;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDateTime getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(LocalDateTime dateCreated) {
    this.dateCreated = dateCreated;
  }

  public LocalDateTime getDateUpdated() {
    return dateUpdated;
  }

  public void setDateUpdated(LocalDateTime dateUpdated) {
    this.dateUpdated = dateUpdated;
  }

  public String getBase64() {
    return base64;
  }

  public void setBase64(String base64) {
    this.base64 = base64;
  }
}
