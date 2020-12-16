package com.msakirc.araba.arabauzmani.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( callSuper = true )
public class Versiyon extends BaseEntity {
  
  protected String markaId;
  
  protected String modelId;
  
  protected String yilId;
  
  protected String name;
  
  
}
