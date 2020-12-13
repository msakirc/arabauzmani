package com.msakirc.araba.arabauzmani;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

// @EntityScan( "com.msakirc.araba.arabauzmani.model" )
@SpringBootApplication
public class ArabauzmaniApplication {
  
  public static void main ( String[] args ) {
    SpringApplication.run( ArabauzmaniApplication.class, args );
  }
  
}
