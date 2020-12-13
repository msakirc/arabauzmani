package com.msakirc.araba.arabauzmani.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class Meta {
  
  private HttpStatus status;
  private String response;
  private ExceptionContainer exception;
  
  public Meta ( HttpStatus status, String response ) {
    this.status = status;
    this.response = response;
  }
}
