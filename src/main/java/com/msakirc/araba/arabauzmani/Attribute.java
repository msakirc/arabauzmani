package com.msakirc.araba.arabauzmani;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Attribute {
  
  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private String id;
  
  AttributeType attributeType;
  
}
