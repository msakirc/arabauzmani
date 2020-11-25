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
  
  private String name;
  
  private String countryOfOrigin;
  
  private Integer servisVotes;
  
  private Double servisScore;
  
  private Integer yedekParcaVotes;
  
  private Double yedekParcaScore;
  
  private Integer saglamlikVotes;
  
  private Double saglamlikScore;
  
}
