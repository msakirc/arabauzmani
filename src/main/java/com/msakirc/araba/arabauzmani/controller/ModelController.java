package com.msakirc.araba.arabauzmani.controller;

import com.msakirc.araba.arabauzmani.model.BaseEntity;
import com.msakirc.araba.arabauzmani.model.Model;
import com.msakirc.araba.arabauzmani.model.Voteable;
import com.msakirc.araba.arabauzmani.response.BaseResponse;
import com.msakirc.araba.arabauzmani.service.ModelService;
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
@RequestMapping( "/model" )
public class ModelController {
  
  private final ModelService modelService;
  private final EntityConverter entityConverter;
  
  @Autowired
  public ModelController ( ModelService modelService, EntityConverter entityConverter ) {
    this.modelService = modelService;
    this.entityConverter = entityConverter;
  }
  
  @GetMapping( Constants.FIND_ALL )
  public ResponseEntity<BaseResponse> all ( @RequestParam String markaId ) {
    return ResponseBuilderUtil.createResponse( modelService.findAll( markaId ) );
  }
  
  
  @GetMapping( Constants.DETAIL )
  public ResponseEntity<BaseResponse> detail ( @RequestParam String id ) {
    BaseEntity model = modelService.findById( id );
    Model modelDto = entityConverter.convertModelDto( (Model) model );
    return ResponseBuilderUtil.createResponse( modelDto );
  }
  
  @GetMapping( Constants.COMPARE )
  public ResponseEntity<BaseResponse> compare ( @RequestParam List<String> ids ) {
    return ResponseBuilderUtil.createResponse( modelService.compare( ids ) );
  }
  
  @GetMapping( Constants.VOTE )
  public ResponseEntity<BaseResponse> vote ( @RequestParam Voteable field, @RequestParam String id, @RequestParam int vote ) {
    return ResponseBuilderUtil.createResponse( modelService.vote( field, id, vote ) );
  }
}
