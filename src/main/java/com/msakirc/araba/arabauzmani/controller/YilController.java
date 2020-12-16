package com.msakirc.araba.arabauzmani.controller;

import com.msakirc.araba.arabauzmani.dto.YilDto;
import com.msakirc.araba.arabauzmani.model.BaseEntity;
import com.msakirc.araba.arabauzmani.model.Voteable;
import com.msakirc.araba.arabauzmani.model.Yil;
import com.msakirc.araba.arabauzmani.response.BaseResponse;
import com.msakirc.araba.arabauzmani.service.YilService;
import com.msakirc.araba.arabauzmani.util.Constants;
import com.msakirc.araba.arabauzmani.util.EntityConverter;
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
  
  private final YilService yilService;
  private final EntityConverter entityConverter;
  
  @Autowired
  public YilController ( YilService yilService, EntityConverter entityConverter ) {
    this.yilService = yilService;
    this.entityConverter = entityConverter;
  }
  
  @GetMapping( Constants.FIND_ALL )
  public ResponseEntity<BaseResponse> all ( @RequestParam String modelId ) {
    return ResponseBuilderUtil.createResponse( yilService.findAll( modelId ) );
  }
  
  
  @GetMapping( Constants.DETAIL )
  public ResponseEntity<BaseResponse> detail ( @RequestParam String id ) {
    BaseEntity yil = yilService.findById( id );
    YilDto yilDto = entityConverter.convertYilDto( (Yil) yil );
    return ResponseBuilderUtil.createResponse( yilDto );
  }
  
  @GetMapping( Constants.COMPARE )
  public ResponseEntity<BaseResponse> compare ( @RequestParam List<String> ids ) {
    return ResponseBuilderUtil.createResponse( yilService.compare( ids ) );
  }
  
  @GetMapping( Constants.VOTE )
  public ResponseEntity<BaseResponse> vote ( @RequestParam Voteable field, @RequestParam String id, @RequestParam int vote ) {
    return ResponseBuilderUtil.createResponse( yilService.vote( field, id, vote ) );
  }
}
