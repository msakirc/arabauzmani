package com.msakirc.araba.arabauzmani.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Comparison {
  
  private List<BaseEntity> entities;
  
  private String result;
  
}
