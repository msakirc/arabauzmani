package com.msakirc.araba.arabauzmani.repository;

import com.msakirc.araba.arabauzmani.model.Marka;
import com.msakirc.araba.arabauzmani.model.Model;
import com.msakirc.araba.arabauzmani.model.Versiyon;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersiyonRepository extends CrudRepository<Versiyon, Integer> {
  
  List<Versiyon> findByYilId ( Integer yilId );
  
}
