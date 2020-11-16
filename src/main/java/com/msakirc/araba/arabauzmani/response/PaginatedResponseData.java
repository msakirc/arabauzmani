package com.msakirc.araba.arabauzmani.response;

import com.msakirc.araba.arabauzmani.model.BaseEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponseData {
  
  private int count;
  
  private List<? extends BaseEntity> results;
}
