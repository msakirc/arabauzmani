package com.msakirc.araba.arabauzmani.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( callSuper = true )
public class Yil extends BaseEntity {
  
  protected String markaId;
  
  protected String modelId;
  
  protected String name;
  
}
