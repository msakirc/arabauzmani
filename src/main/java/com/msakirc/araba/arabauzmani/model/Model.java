package com.msakirc.araba.arabauzmani.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( callSuper = true )
public class Model extends BaseEntity {
  
  protected String markaId;
  
  protected String name;
}
