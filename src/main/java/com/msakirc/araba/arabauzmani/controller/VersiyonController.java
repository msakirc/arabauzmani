package com.msakirc.araba.arabauzmani.controller;

import com.msakirc.araba.arabauzmani.model.Voteable;
import com.msakirc.araba.arabauzmani.response.BaseResponse;
import com.msakirc.araba.arabauzmani.service.VersiyonService;
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
@RequestMapping( "/versiyon" )
public class VersiyonController {
  
  private VersiyonService versiyonService;
  
  @Autowired
  public VersiyonController ( VersiyonService versiyonService ) {
    this.versiyonService = versiyonService;
  }
  
  @GetMapping( Constants.FIND_ALL )
  public ResponseEntity<BaseResponse> all ( @RequestParam String yilId ) {
    return ResponseBuilderUtil.createResponse( versiyonService.findAll( yilId ) );
  }
  
  @GetMapping( Constants.DETAIL )
  public ResponseEntity<BaseResponse> detail ( @RequestParam String id ) {
    return ResponseBuilderUtil.createResponse( versiyonService.findById( id ) );
  }
  
  @GetMapping( Constants.COMPARE )
  public ResponseEntity<BaseResponse> compare ( @RequestParam List<String> ids ) {
    return ResponseBuilderUtil.createResponse( versiyonService.compare( ids ) );
  }
  
  @GetMapping( Constants.VOTE )
  public ResponseEntity<BaseResponse> vote ( @RequestParam Voteable field, @RequestParam String id, @RequestParam int vote ) {
    return ResponseBuilderUtil.createResponse( versiyonService.vote( field, id, vote ) );
  }
  
}
