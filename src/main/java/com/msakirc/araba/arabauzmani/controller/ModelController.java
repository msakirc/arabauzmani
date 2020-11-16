package com.msakirc.araba.arabauzmani.controller;

import com.msakirc.araba.arabauzmani.model.Voteable;
import com.msakirc.araba.arabauzmani.response.BaseResponse;
import com.msakirc.araba.arabauzmani.service.ModelService;
import com.msakirc.araba.arabauzmani.util.Constants;
import com.msakirc.araba.arabauzmani.util.ResponseBuilderUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/model" )
public class ModelController {
  
  private ModelService modelService;
  
  @Autowired
  public ModelController ( ModelService modelService ) {
    this.modelService = modelService;
  }
  
  @GetMapping( Constants.FIND_ALL )
  public ResponseEntity<BaseResponse> all ( @RequestParam Integer markaId ) {
    return ResponseBuilderUtil.createResponse( modelService.findAll( markaId ) );
  }
  
  
  @GetMapping( Constants.COMPARE )
  public ResponseEntity<BaseResponse> compare ( @RequestParam List<Integer> ids ) {
    return ResponseBuilderUtil.createResponse( modelService.compare( ids ) );
  }
  
  @GetMapping( Constants.VOTE )
  public ResponseEntity<BaseResponse> vote ( @RequestParam Voteable field, @RequestParam Integer id, @RequestParam int vote ) {
    return ResponseBuilderUtil.createResponse( modelService.vote( field, id, vote ) );
  }
}
