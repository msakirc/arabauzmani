package com.msakirc.araba.arabauzmani.repository;

import com.msakirc.araba.arabauzmani.model.Marka;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkaRepository extends CrudRepository<Marka, Integer> {
  
}
