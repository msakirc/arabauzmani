package com.msakirc.araba.arabauzmani.repository;

import com.msakirc.araba.arabauzmani.model.Marka;
import com.msakirc.araba.arabauzmani.model.Model;
import com.msakirc.araba.arabauzmani.model.Yil;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YilRepository extends CrudRepository<Yil, Integer> {
  
  List<Yil> findByModelId ( Integer modelId );
  
}
