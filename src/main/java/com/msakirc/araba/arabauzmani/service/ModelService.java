package com.msakirc.araba.arabauzmani.service;

import com.msakirc.araba.arabauzmani.model.Marka;
import com.msakirc.araba.arabauzmani.model.Model;
import java.util.List;

public interface ModelService extends BaseService {
  
  List<Model> findAll ( Integer markaId );
  
}
