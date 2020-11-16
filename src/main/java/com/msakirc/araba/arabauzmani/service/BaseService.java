package com.msakirc.araba.arabauzmani.service;

import com.msakirc.araba.arabauzmani.model.BaseEntity;
import com.msakirc.araba.arabauzmani.model.Comparison;
import com.msakirc.araba.arabauzmani.model.Versiyon;
import com.msakirc.araba.arabauzmani.model.Voteable;
import java.util.List;

public interface BaseService {
  
  BaseEntity findById ( Integer id );
  
  Comparison compare ( List<Integer> ids );
  
  double vote ( Voteable field, Integer id, Integer vote );
  
  double vote ( Voteable field, BaseEntity entity, Integer vote );
  
  double voteFp ( Integer vote, BaseEntity versiyon );
  
  double voteKonfor ( Integer vote, BaseEntity versiyon );
  
  double voteEstetik ( Integer vote, BaseEntity versiyon );
  
  double voteOverall ( Integer vote, BaseEntity versiyon );
}
