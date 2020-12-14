package com.msakirc.araba.arabauzmani.service;

import com.msakirc.araba.arabauzmani.model.Model;
import com.msakirc.araba.arabauzmani.model.Yil;
import java.util.List;

public interface YilService extends BaseService {
  
  List<Yil> findAll ( String modelId );
  
}
