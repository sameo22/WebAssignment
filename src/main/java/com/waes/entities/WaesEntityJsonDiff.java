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

  @Column(name = "is_equal")
  private Boolean is_equal;

  @Column(name = "json_diffs")
  private String jsonDiffs;

  @Column(name = "date_saved")
  private LocalDateTime dateSaved;

  public String getIdJsondiff() {
    return idJsondiff;
  }

  public void setIdJsondiff(String idJsondiff) {
    this.idJsondiff = idJsondiff;
  }

  public Boolean getIs_equal() {
    return is_equal;
  }

  public void setIs_equal(Boolean is_equal) {
    this.is_equal = is_equal;
  }

  public String getJsonDiffs() {
    return jsonDiffs;
  }

  public void setJsonDiffs(String jsonDiffs) {
    this.jsonDiffs = jsonDiffs;
  }

  public LocalDateTime getDateSaved() {
    return dateSaved;
  }

  public void setDateSaved(LocalDateTime dateSaved) {
    this.dateSaved = dateSaved;
  }
}
