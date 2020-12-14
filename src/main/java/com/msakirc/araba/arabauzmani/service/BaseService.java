package com.msakirc.araba.arabauzmani.service;

import com.msakirc.araba.arabauzmani.model.BaseEntity;
import com.msakirc.araba.arabauzmani.model.Comparison;
import com.msakirc.araba.arabauzmani.model.Voteable;
import java.util.List;
import java.util.Map;

public interface BaseService {
  
  BaseEntity findById ( String id );
  
  Comparison compare ( List<String> ids );
  
  double vote ( Voteable field, String id, Integer vote );
  
  double vote ( Voteable field, BaseEntity entity, Integer vote );
  
  double  votePerformans ( Integer vote, Map<String, Object> updates, BaseEntity marka );
  
  double voteKonfor ( Integer vote, Map<String, Object> updates, BaseEntity marka );
  
  double voteEstetik ( Integer vote, Map<String, Object> updates, BaseEntity marka );
  
  double voteFiyat ( Integer vote, Map<String, Object> updates, BaseEntity marka );
  
  double voteUzunOmurluluk ( Integer vote, Map<String, Object> updates, BaseEntity marka );
  
  double voteOverall ( Integer vote, Map<String, Object> updates, BaseEntity marka );
}
