package com.msakirc.araba.arabauzmani.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode( callSuper = true )
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
