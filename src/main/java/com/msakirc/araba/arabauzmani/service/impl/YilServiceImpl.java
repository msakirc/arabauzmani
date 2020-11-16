package com.msakirc.araba.arabauzmani.service.impl;

import com.msakirc.araba.arabauzmani.model.BaseEntity;
import com.msakirc.araba.arabauzmani.model.Comparison;
import com.msakirc.araba.arabauzmani.model.Versiyon;
import com.msakirc.araba.arabauzmani.model.Voteable;
import com.msakirc.araba.arabauzmani.model.Yil;
import com.msakirc.araba.arabauzmani.repository.YilRepository;
import com.msakirc.araba.arabauzmani.service.ModelService;
import com.msakirc.araba.arabauzmani.service.YilService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YilServiceImpl implements YilService {
  
  private final YilRepository yilRepository;
  private final ModelService modelService;
  
  @Autowired
  public YilServiceImpl ( YilRepository yilRepository, ModelService modelService ) {
    this.yilRepository = yilRepository;
    this.modelService = modelService;
  }
  
  @Override
  public Yil findById ( Integer id ) {
    return yilRepository.findById( id ).orElseThrow( RuntimeException::new );
  }
  
  @Override
  public List<Yil> findAll ( Integer modelId ) {
    return yilRepository.findByModelId( modelId );
  }
  @Override
  public Comparison compare ( List<Integer> ids ) {
    
    List<BaseEntity> models = ids.stream()
                                 .map( this::findById )
                                 .collect( Collectors.toList() );
    
    return new Comparison( models, "şimdilik boş" );
  }
  
  @Override
  public double vote ( Voteable field, Integer id, Integer vote ) {
  
    Yil yil = findById( id );
    return vote( field, yil, vote );
  }
  
  @Override
  public double vote ( Voteable field, BaseEntity entity, Integer vote ) {
    
    Yil yil = ( (Yil) entity );
    double newScore;
    
    switch ( field ) {
      case FP:
        newScore = voteFp( vote, yil );
        break;
      
      case KONFOR:
        newScore = voteKonfor( vote, yil );
        break;
      
      case ESTETIK:
        newScore = voteEstetik( vote, yil );
        break;
      
      case OVERALL:
        newScore = voteOverall( vote, yil );
        break;
      
      default:
        throw new IllegalStateException( "Unexpected value: " + field );
    }
    
    return newScore;
  }
  
  @Override
  public double voteEstetik ( Integer vote, BaseEntity yil ) {
    double newScore;
    yil.setEstetikVotes( yil.getEstetikVotes() + vote );
    yil.setEstetikScore( yil.getEstetikScore() + vote );
    newScore = yil.getEstetikScore().doubleValue() / yil.getEstetikVotes().doubleValue();
    
    yilRepository.save( ( (Yil) yil ) );
    modelService.voteEstetik( vote, ( (Yil) yil ).getModel() );
    return newScore;
  }
  
  @Override
  public double voteKonfor ( Integer vote, BaseEntity yil ) {
    double newScore;
    yil.setKonforVotes( yil.getKonforVotes() + 1 );
    yil.setKonforScore( yil.getKonforScore() + vote );
    newScore = yil.getKonforScore().doubleValue() / yil.getKonforVotes().doubleValue();
    
    yilRepository.save( ( (Yil) yil ) );
    modelService.voteKonfor( vote, ( (Yil) yil ).getModel() );
    return newScore;
  }
  
  @Override
  public double voteFp ( Integer vote, BaseEntity yil ) {
    double newScore;
    yil.setFpVotes( yil.getFpVotes() + 1 );
    yil.setFpScore( yil.getFpScore() + vote );
    newScore = yil.getFpScore().doubleValue() / yil.getFpVotes().doubleValue();
    
    yilRepository.save( ( (Yil) yil ) );
    modelService.voteFp( vote, ( (Yil) yil ).getModel() );
    return newScore;
  }
  
  @Override
  public double voteOverall ( Integer vote, BaseEntity yil ) {
    double newScore;
    yil.setOverallVotes( yil.getOverallVotes() + 1 );
    yil.setOverallScore( yil.getOverallScore() + vote );
    newScore = yil.getOverallScore().doubleValue() / yil.getOverallVotes().doubleValue();
    
    yilRepository.save( ( (Yil) yil ) );
    modelService.voteOverall( vote, ( (Yil) yil ).getModel() );
    return newScore;
  }
  
}
