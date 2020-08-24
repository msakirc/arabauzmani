package com.msakirc.araba.arabauzmani.model;

import com.msakirc.araba.arabauzmani.Attribute;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Marka {
  
  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private Integer id;
  
  private String name;
  
  private Attribute servisAgi;
  
  private Attribute yedekParca;
  
  private Attribute uzunOmurluluk;
  
  private Attribute yakisiklilik;
  
  private Attribute overall;
  
}
