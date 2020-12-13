package com.msakirc.araba.arabauzmani.repository;

import com.msakirc.araba.arabauzmani.model.Model;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends CrudRepository<Model, Integer> {
  
  List<Model> findByMarkaId ( Integer markaId );
  
}
