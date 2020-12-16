package com.msakirc.araba.arabauzmani.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
public class BaseEntity {
  
  protected String id;
  
  protected Integer konforVotes;
  
  protected Double konforScore;
  
  protected Integer estetikVotes;
  
  protected Double estetikScore;
  
  protected Integer uzunOmurlulukVotes;
  
  protected Double uzunOmurlulukScore;
  
  protected Integer performansVotes;
  
  protected Double performansScore;
  
  protected Integer fiyatVotes;
  
  protected Double fiyatScore;
  
  protected Integer overallVotes;
  
  protected Double overallScore;
  
  protected String text;
  
}
