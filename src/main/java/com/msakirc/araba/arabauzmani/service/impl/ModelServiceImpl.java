package com.msakirc.araba.arabauzmani.service.impl;

import com.msakirc.araba.arabauzmani.model.BaseEntity;
import com.msakirc.araba.arabauzmani.model.Comparison;
import com.msakirc.araba.arabauzmani.model.Model;
import com.msakirc.araba.arabauzmani.model.Voteable;
import com.msakirc.araba.arabauzmani.model.Yil;
import com.msakirc.araba.arabauzmani.repository.ModelRepository;
import com.msakirc.araba.arabauzmani.service.MarkaService;
import com.msakirc.araba.arabauzmani.service.ModelService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {
  
  private final ModelRepository modelRepository;
  private final MarkaService markaService;
  
  @Autowired
  public ModelServiceImpl ( ModelRepository modelRepository, MarkaService markaService ) {
    this.modelRepository = modelRepository;
    this.markaService = markaService;
  }
  
  @Override
  public Model findById ( Integer id ) {
    return modelRepository.findById( id ).orElseThrow( RuntimeException::new );
  }
  
  @Override
  public List<Model> findAll ( Integer markaId ) {
    return modelRepository.findByMarkaId( markaId );
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
    
    Model model = findById( id );
    return vote( field, model, vote );
  }
  
  @Override
  public double vote ( Voteable field, BaseEntity entity, Integer vote ) {
    
    Model model = ( (Model) entity );
    double newScore;
    
    switch ( field ) {
      case FP:
        newScore = voteFp( vote, model );
        break;
      
      case KONFOR:
        newScore = voteKonfor( vote, model );
        break;
      
      case ESTETIK:
        newScore = voteEstetik( vote, model );
        break;
      
      case OVERALL:
        newScore = voteOverall( vote, model );
        break;
      
      default:
        throw new IllegalStateException( "Unexpected value: " + field );
    }
    
    return newScore;
  }
  
  @Override
  public double voteEstetik ( Integer vote, BaseEntity model ) {
    double newScore;
    model.setEstetikVotes( model.getEstetikVotes() + vote );
    model.setEstetikScore( model.getEstetikScore() + vote );
    newScore = model.getEstetikScore().doubleValue() / model.getEstetikVotes().doubleValue();
    
    modelRepository.save( ( (Model) model ) );
    markaService.voteEstetik( vote, ( (Model) model ).getMarka() );
    return newScore;
  }
  
  @Override
  public double voteKonfor ( Integer vote, BaseEntity model ) {
    double newScore;
    model.setKonforVotes( model.getKonforVotes() + 1 );
    model.setKonforScore( model.getKonforScore() + vote );
    newScore = model.getKonforScore().doubleValue() / model.getKonforVotes().doubleValue();
    
    modelRepository.save( ( (Model) model ) );
    markaService.voteKonfor( vote, ( (Model) model ).getMarka() );
    return newScore;
  }
  
  @Override
  public double voteFp ( Integer vote, BaseEntity model ) {
    double newScore;
    model.setFpVotes( model.getFpVotes() + 1 );
    model.setFpScore( model.getFpScore() + vote );
    newScore = model.getFpScore().doubleValue() / model.getFpVotes().doubleValue();
    
    modelRepository.save( ( (Model) model ) );
    markaService.voteFp( vote, ( (Model) model ).getMarka() );
    return newScore;
  }
  
  @Override
  public double voteOverall ( Integer vote, BaseEntity model ) {
    double newScore;
    model.setOverallVotes( model.getOverallVotes() + 1 );
    model.setOverallScore( model.getOverallScore() + vote );
    newScore = model.getOverallScore().doubleValue() / model.getOverallVotes().doubleValue();
    
    modelRepository.save( ( (Model) model ) );
    markaService.voteOverall( vote, ( (Model) model ).getMarka() );
    return newScore;
  }
  
  
}
