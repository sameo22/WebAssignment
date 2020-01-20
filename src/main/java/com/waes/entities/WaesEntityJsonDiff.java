package com.waes.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "waes_json_diffs")
public class WaesEntityJsonDiff {

  @Id
  @Column(name = "id_json_diff")
  private String idJsondiff;

  @Column(name = "json_diffs", length = 1024 * 1024 * 1024)
  private String jsonDiffs;

  @Column(name = "date_created")
  private LocalDateTime dateCreated;

  public String getIdJsondiff() {
    return idJsondiff;
  }

  public void setIdJsondiff(String idJsondiff) {
    this.idJsondiff = idJsondiff;
  }

  public String getJsonDiffs() {
    return jsonDiffs;
  }

  public void setJsonDiffs(String jsonDiffs) {
    this.jsonDiffs = jsonDiffs;
  }

  public LocalDateTime getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(LocalDateTime dateCreated) {
    this.dateCreated = dateCreated;
  }

}
