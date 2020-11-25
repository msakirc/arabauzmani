package com.msakirc.araba.arabauzmani.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {
  
  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private Integer id;
  
  private Integer konforVotes;
  
  private Double konforScore;
  
  private Integer estetikVotes;
  
  private Double estetikScore;
  
  private Integer fpVotes;
  
  private Double fpScore;
  
  private Integer overallVotes;
  
  private Double overallScore;
  
  @Column( name = "text" )
  private String text;
  
}
