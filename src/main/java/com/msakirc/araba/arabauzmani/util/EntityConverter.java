package com.msakirc.araba.arabauzmani.util;

import com.msakirc.araba.arabauzmani.dto.MarkaDto;
import com.msakirc.araba.arabauzmani.dto.ModelDto;
import com.msakirc.araba.arabauzmani.dto.VersiyonDto;
import com.msakirc.araba.arabauzmani.dto.YilDto;
import com.msakirc.araba.arabauzmani.model.BaseEntity;
import com.msakirc.araba.arabauzmani.model.Marka;
import com.msakirc.araba.arabauzmani.model.Model;
import com.msakirc.araba.arabauzmani.model.Versiyon;
import com.msakirc.araba.arabauzmani.model.Yil;
import com.msakirc.araba.arabauzmani.service.MarkaService;
import com.msakirc.araba.arabauzmani.service.ModelService;
import com.msakirc.araba.arabauzmani.service.VersiyonService;
import com.msakirc.araba.arabauzmani.service.YilService;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityConverter {
  
  private final MarkaService markaService;
  private final ModelService modelService;
  private final YilService yilService;
  private final VersiyonService versiyonService;
  
  @Autowired
  public EntityConverter ( MarkaService markaService, ModelService modelService, YilService yilService, VersiyonService versiyonService ) {
    this.markaService = markaService;
    this.modelService = modelService;
    this.yilService = yilService;
    this.versiyonService = versiyonService;
  }
  
  public MarkaDto convertMarkaDto ( Marka marka ) {
    return new MarkaDto( marka );
  }
  
  public ModelDto convertModelDto ( Model model ) {
    ModelDto modelDto = new ModelDto( model );
    modelDto.setMarka( (Marka) markaService.findById( model.getMarkaId() ) );
    return modelDto;
  }
  
  public VersiyonDto convertVersiyonDto ( Versiyon versiyon ) {
    VersiyonDto versiyonDto = new VersiyonDto( versiyon );
    versiyonDto.setMarka( (Marka) markaService.findById( versiyon.getMarkaId() ) );
    versiyonDto.setModel( (Model) modelService.findById( versiyon.getModelId() ) );
    versiyonDto.setYil( (Yil) yilService.findById( versiyon.getYilId() ) );
    return versiyonDto;
  }
  
  public YilDto convertYilDto ( Yil yil ) {
    YilDto yilDto = new YilDto( yil );
    yilDto.setMarka( (Marka) markaService.findById( yil.getMarkaId() ) );
    yilDto.setModel( (Model) modelService.findById( yil.getModelId() ) );
    return yilDto;
  }
}
