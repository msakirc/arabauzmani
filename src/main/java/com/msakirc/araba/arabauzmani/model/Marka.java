package com.msakirc.araba.arabauzmani.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode( callSuper = true )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@AllArgsConstructor
@NoArgsConstructor
public class Marka extends BaseEntity {
  
  protected String name;
  
  protected String countryOfOrigin;
  
  protected Integer servisVotes;
  
  protected Double servisScore;
  
  protected Integer yedekParcaVotes;
  
  protected Double yedekParcaScore;
  
  protected Integer malzemeKalitesiVotes;
  
  protected Double malzemeKalitesiScore;
  
  protected Integer guvenlikVotes;
  
  protected Double guvenlikScore;
  
}
