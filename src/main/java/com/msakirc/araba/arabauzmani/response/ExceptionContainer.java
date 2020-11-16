package com.msakirc.araba.arabauzmani.response;

import java.util.List;
import lombok.Data;

@Data
public class ExceptionContainer {
  
  private String message;
  
  private List<String> validationErrors;
  
  private String stackTrace;
  
}
