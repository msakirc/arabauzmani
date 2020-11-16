package com.msakirc.araba.arabauzmani.service;

import com.msakirc.araba.arabauzmani.model.BaseEntity;
import com.msakirc.araba.arabauzmani.model.Comparison;
import com.msakirc.araba.arabauzmani.model.Marka;
import com.msakirc.araba.arabauzmani.model.Voteable;
import java.util.List;

public interface MarkaService extends BaseService {
  
  List<Marka> findAll ();
  
}
