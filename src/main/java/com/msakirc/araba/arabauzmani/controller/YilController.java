package com.msakirc.araba.arabauzmani.controller;

import com.msakirc.araba.arabauzmani.model.Voteable;
import com.msakirc.araba.arabauzmani.response.BaseResponse;
import com.msakirc.araba.arabauzmani.service.YilService;
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
@RequestMapping( "/yil" )
public class YilController {
  
  private YilService yilService;
  
  @Autowired
  public YilController ( YilService yilService ) {
    this.yilService = yilService;
  }
  
  @GetMapping( Constants.FIND_ALL )
  public ResponseEntity<BaseResponse> all ( @RequestParam Integer modelId ) {
    return ResponseBuilderUtil.createResponse( yilService.findAll( modelId ) );
  }
  
  
  @GetMapping( Constants.DETAIL )
  public ResponseEntity<BaseResponse> detail ( @RequestParam Integer id ) {
    return ResponseBuilderUtil.createResponse( yilService.findById( id ) );
  }
  
  @GetMapping( Constants.COMPARE )
  public ResponseEntity<BaseResponse> compare ( @RequestParam List<Integer> ids ) {
    return ResponseBuilderUtil.createResponse( yilService.compare( ids ) );
  }
  
  @GetMapping( Constants.VOTE )
  public ResponseEntity<BaseResponse> vote ( @RequestParam Voteable field, @RequestParam Integer id, @RequestParam int vote ) {
    return ResponseBuilderUtil.createResponse( yilService.vote( field, id, vote ) );
  }
}
