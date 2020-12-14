package com.msakirc.araba.arabauzmani.service;

import com.msakirc.araba.arabauzmani.model.Model;
import com.msakirc.araba.arabauzmani.model.Versiyon;
import java.util.List;

public interface VersiyonService extends BaseService {
  
  List<Versiyon> findAll ( String yilId );
}
