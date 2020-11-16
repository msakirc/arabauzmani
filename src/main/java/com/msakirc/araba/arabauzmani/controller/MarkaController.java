package com.msakirc.araba.arabauzmani.controller;

import com.msakirc.araba.arabauzmani.model.Voteable;
import com.msakirc.araba.arabauzmani.response.BaseResponse;
import com.msakirc.araba.arabauzmani.service.MarkaService;
import com.msakirc.araba.arabauzmani.util.Constants;
import com.msakirc.araba.arabauzmani.util.ResponseBuilderUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/marka" )
public class MarkaController {
  
  private MarkaService markaService;
  
  @Autowired
  public MarkaController ( MarkaService markaService ) {
    this.markaService = markaService;
  }
  
  @GetMapping( Constants.FIND_ALL )
  public ResponseEntity<BaseResponse> all () {
    return ResponseBuilderUtil.createResponse( markaService.findAll() );
  }
  
  
  @GetMapping( Constants.COMPARE )
  public ResponseEntity<BaseResponse> compare ( @RequestParam List<Integer> ids ) {
    return ResponseBuilderUtil.createResponse( markaService.compare( ids ) );
  }
  
  @GetMapping( Constants.VOTE )
  public ResponseEntity<BaseResponse> vote ( @RequestParam Voteable field, @RequestParam Integer id, @RequestParam int vote ) {
    return ResponseBuilderUtil.createResponse( markaService.vote( field, id, vote ) );
  }
  
}
