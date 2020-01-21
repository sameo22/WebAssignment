package com.waes.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "waes_right_jsons")
public class WaesEntityRightJsons {

  @Id
  @Column(name = "id_right_json")
  private String idRightJson;

  @Column(name = "right_json", length = 1024 * 1024 * 1024)
  private String righttJson;

  @Column(name = "status")
  private String status;

  @Column(name = "date_created")
  private LocalDateTime dateCreated;

  @Column(name = "date_updated")
  private LocalDateTime dateUpdated;

  @Column(name = "base_64")
  private String base64;

  public String getIdRightJson() {
    return idRightJson;
  }

  public void setIdRightJson(String idRightJson) {
    this.idRightJson = idRightJson;
  }

  public String getRighttJson() {
    return righttJson;
  }

  public void setRighttJson(String righttJson) {
    this.righttJson = righttJson;
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
