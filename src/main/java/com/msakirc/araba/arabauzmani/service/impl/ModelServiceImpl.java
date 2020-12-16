package com.msakirc.araba.arabauzmani.service.impl;

import com.msakirc.araba.arabauzmani.model.BaseEntity;
import com.msakirc.araba.arabauzmani.model.Comparison;
import com.msakirc.araba.arabauzmani.model.Model;
import com.msakirc.araba.arabauzmani.model.Voteable;
import com.msakirc.araba.arabauzmani.repository.firestore.ModelRepository;
import com.msakirc.araba.arabauzmani.service.MarkaService;
import com.msakirc.araba.arabauzmani.service.ModelService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  public Model findById ( String id ) {
    return modelRepository.findById( id ).orElseThrow( RuntimeException::new );
  }
  
  @Override
  public List<Model> findAll ( String markaId ) {
    return modelRepository.findByMarkaId( markaId );
  }
  
  @Override
  public Comparison compare ( List<String> ids ) {
    
    List<BaseEntity> models = ids.stream()
                                 .map( this::findById )
                                 .collect( Collectors.toList() );
    
    return new Comparison( models, "şimdilik boş" );
  }
  
  @Override
  public double vote ( Voteable field, String id, Integer vote ) {
    
    Model model = findById( id );
    return vote( field, model, vote );
  }
  
  @Override
  public double vote ( Voteable field, BaseEntity entity, Integer vote ) {
    
    Model model = ( (Model) entity );
    double newScore;
  
    Map<String, Object> updates = new HashMap<>();
    updates.put( "id", model.getId() );
    
    switch ( field ) {
      case PERFORMANS:
        newScore = votePerformans( vote, updates, model );
        break;
      
      case UZUN_OMURLULUK:
        newScore = voteUzunOmurluluk( vote, updates, model );
        break;
      
      case FIYAT:
        newScore = voteFiyat( vote, updates, model );
        break;
      
      case KONFOR:
        newScore = voteKonfor( vote, updates, model );
        break;
      
      case ESTETIK:
        newScore = voteEstetik( vote, updates, model );
        break;
      
      case OVERALL:
        newScore = voteOverall( vote, updates, model );
        break;
      
      default:
        throw new IllegalStateException( "Unexpected value: " + field );
    }
    
    return newScore;
  }
  
  @Override
  public double voteEstetik ( Integer vote, Map<String, Object> updates, BaseEntity model ) {
    double newScore;
    model.setEstetikVotes( model.getEstetikVotes() + vote );
    model.setEstetikScore( model.getEstetikScore() + vote );
    newScore = model.getEstetikScore() / model.getEstetikVotes().doubleValue();
    
    updates.put( "estetikVotes", model.getEstetikVotes() );
    updates.put( "estetikScore", model.getEstetikScore() );
  
    // modelRepository.save( ( (Model) model ) );
    BaseEntity marka = markaService.findById( ( (Model) model ).getMarkaId() );
    markaService.voteEstetik( vote, updates, marka );
    return newScore;
  }
  
  @Override
  public double voteKonfor ( Integer vote, Map<String, Object> updates, BaseEntity model ) {
    double newScore;
    model.setKonforVotes( model.getKonforVotes() + 1 );
    model.setKonforScore( model.getKonforScore() + vote );
    newScore = model.getKonforScore() / model.getKonforVotes().doubleValue();
  
    updates.put( "konforVotes", model.getKonforVotes() );
    updates.put( "konforScore", model.getKonforScore() );
  
    // modelRepository.save( ( (Model) model ) );
    BaseEntity marka = markaService.findById( ( (Model) model ).getMarkaId() );
    markaService.voteKonfor( vote, updates, marka );
    return newScore;
  }
  
  @Override
  public double voteFiyat ( Integer vote, Map<String, Object> updates, BaseEntity model ) {
    double newScore;
    model.setFiyatVotes( model.getFiyatVotes() + 1 );
    model.setFiyatScore( model.getFiyatScore() + vote );
    newScore = model.getFiyatScore() / model.getFiyatVotes().doubleValue();
  
    updates.put( "fiyatVotes", model.getFiyatVotes() );
    updates.put( "fiyatScore", model.getFiyatScore() );
  
    // modelRepository.save( ( (Model) model ) );
    BaseEntity marka = markaService.findById( ( (Model) model ).getMarkaId() );
    markaService.voteFiyat( vote, updates, marka );
    return newScore;
  }
  
  @Override
  public double votePerformans ( Integer vote, Map<String, Object> updates, BaseEntity model ) {
    double newScore;
    model.setPerformansVotes( model.getPerformansVotes() + 1 );
    model.setPerformansScore( model.getPerformansScore() + vote );
    newScore = model.getPerformansScore() / model.getPerformansVotes().doubleValue();
  
    updates.put( "performansVotes", model.getPerformansVotes() );
    updates.put( "performansScore", model.getPerformansScore() );
  
    // modelRepository.save( ( (Model) model ) );
    BaseEntity marka = markaService.findById( ( (Model) model ).getMarkaId() );
    markaService.votePerformans( vote, updates, marka );
    return newScore;
  }
  
  @Override
  public double voteUzunOmurluluk ( Integer vote, Map<String, Object> updates, BaseEntity model ) {
    double newScore;
    model.setUzunOmurlulukVotes( model.getUzunOmurlulukVotes() + 1 );
    model.setUzunOmurlulukScore( model.getUzunOmurlulukScore() + vote );
    newScore = model.getUzunOmurlulukScore() / model.getUzunOmurlulukVotes().doubleValue();
  
    updates.put( "uzunOmurlulukVotes", model.getUzunOmurlulukVotes() );
    updates.put( "uzunOmurlulukScore", model.getUzunOmurlulukScore() );
  
    // modelRepository.save( ( (Model) model ) );
    BaseEntity marka = markaService.findById( ( (Model) model ).getMarkaId() );
    markaService.voteUzunOmurluluk( vote, updates, marka );
    return newScore;
  }
  
  @Override
  public double voteOverall ( Integer vote, Map<String, Object> updates, BaseEntity model ) {
    double newScore;
    model.setOverallVotes( model.getOverallVotes() + 1 );
    model.setOverallScore( model.getOverallScore() + vote );
    newScore = model.getOverallScore() / model.getOverallVotes().doubleValue();
  
    updates.put( "overallVotes", model.getOverallVotes() );
    updates.put( "overallScore", model.getOverallScore() );
  
    // modelRepository.save( ( (Model) model ) );
    BaseEntity marka = markaService.findById( ( (Model) model ).getMarkaId() );
    markaService.voteOverall( vote, updates, marka );
    return newScore;
  }
  
  
}
