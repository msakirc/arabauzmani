package com.msakirc.araba.arabauzmani.service.impl;

import com.msakirc.araba.arabauzmani.model.BaseEntity;
import com.msakirc.araba.arabauzmani.model.Comparison;
import com.msakirc.araba.arabauzmani.model.Voteable;
import com.msakirc.araba.arabauzmani.model.Yil;
import com.msakirc.araba.arabauzmani.repository.firestore.YilRepository;
import com.msakirc.araba.arabauzmani.service.ModelService;
import com.msakirc.araba.arabauzmani.service.YilService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  public Yil findById ( String id ) {
    return yilRepository.findById( id ).orElseThrow( RuntimeException::new );
  }
  
  @Override
  public List<Yil> findAll ( String modelId ) {
    return yilRepository.findByModelId( modelId );
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
    
    Yil yil = findById( id );
    return vote( field, yil, vote );
  }
  
  @Override
  public double vote ( Voteable field, BaseEntity entity, Integer vote ) {
    
    Yil yil = ( (Yil) entity );
    double newScore;
  
    Map<String, Object> updates = new HashMap<>();
    updates.put( "id", yil.getId() );
    
    switch ( field ) {
      case PERFORMANS:
        newScore = votePerformans( vote, updates, yil );
        break;
      
      case UZUN_OMURLULUK:
        newScore = voteUzunOmurluluk( vote, updates, yil );
        break;
      
      case FIYAT:
        newScore = voteFiyat( vote, updates, yil );
        break;
      
      case KONFOR:
        newScore = voteKonfor( vote, updates, yil );
        break;
      
      case ESTETIK:
        newScore = voteEstetik( vote, updates, yil );
        break;
      
      case OVERALL:
        newScore = voteOverall( vote, updates, yil );
        break;
      
      default:
        throw new IllegalStateException( "Unexpected value: " + field );
    }
    
    return newScore;
  }
  
  @Override
  public double voteEstetik ( Integer vote, Map<String, Object> updates, BaseEntity yil ) {
    double newScore;
    yil.setEstetikVotes( yil.getEstetikVotes() + vote );
    yil.setEstetikScore( yil.getEstetikScore() + vote );
    newScore = yil.getEstetikScore() / yil.getEstetikVotes().doubleValue();
    
    updates.put( "estetikVotes", yil.getEstetikVotes() );
    updates.put( "estetikScore", yil.getEstetikScore() );
  
    // yilRepository.save( ( (Yil) yil ) );
    modelService.voteEstetik( vote, updates, ( (Yil) yil ).getModel() );
    return newScore;
  }
  
  @Override
  public double voteKonfor ( Integer vote, Map<String, Object> updates, BaseEntity yil ) {
    double newScore;
    yil.setKonforVotes( yil.getKonforVotes() + 1 );
    yil.setKonforScore( yil.getKonforScore() + vote );
    newScore = yil.getKonforScore() / yil.getKonforVotes().doubleValue();
  
    updates.put( "konforVotes", yil.getKonforVotes() );
    updates.put( "konforScore", yil.getKonforScore() );
  
    // yilRepository.save( ( (Yil) yil ) );
    modelService.voteKonfor( vote, updates, ( (Yil) yil ).getModel() );
    return newScore;
  }
  
  @Override
  public double voteFiyat ( Integer vote, Map<String, Object> updates, BaseEntity yil ) {
    double newScore;
    yil.setFiyatVotes( yil.getFiyatVotes() + 1 );
    yil.setFiyatScore( yil.getFiyatScore() + vote );
    newScore = yil.getFiyatScore() / yil.getFiyatVotes().doubleValue();
  
    updates.put( "fiyatVotes", yil.getFiyatVotes() );
    updates.put( "fiyatScore", yil.getFiyatScore() );
  
    // yilRepository.save( ( (Yil) yil ) );
    modelService.voteFiyat( vote, updates, ( (Yil) yil ).getModel() );
    return newScore;
  }
  
  @Override
  public double votePerformans ( Integer vote, Map<String, Object> updates, BaseEntity yil ) {
    double newScore;
    yil.setPerformansVotes( yil.getPerformansVotes() + 1 );
    yil.setPerformansScore( yil.getPerformansScore() + vote );
    newScore = yil.getPerformansScore() / yil.getPerformansVotes().doubleValue();
  
    updates.put( "performansVotes", yil.getPerformansVotes() );
    updates.put( "performansScore", yil.getPerformansScore() );
  
    // yilRepository.save( ( (Yil) yil ) );
    modelService.votePerformans( vote, updates, ( (Yil) yil ).getModel() );
    return newScore;
  }
  
  @Override
  public double voteUzunOmurluluk ( Integer vote, Map<String, Object> updates, BaseEntity yil ) {
    double newScore;
    yil.setUzunOmurlulukVotes( yil.getUzunOmurlulukVotes() + 1 );
    yil.setUzunOmurlulukScore( yil.getUzunOmurlulukScore() + vote );
    newScore = yil.getUzunOmurlulukScore() / yil.getUzunOmurlulukVotes().doubleValue();
  
    updates.put( "uzunOmurlulukVotes", yil.getUzunOmurlulukVotes() );
    updates.put( "uzunOmurlulukScore", yil.getUzunOmurlulukScore() );
  
    // yilRepository.save( ( (Yil) yil ) );
    modelService.voteUzunOmurluluk( vote, updates, ( (Yil) yil ).getModel() );
    return newScore;
  }
  
  @Override
  public double voteOverall ( Integer vote, Map<String, Object> updates, BaseEntity yil ) {
    double newScore;
    yil.setOverallVotes( yil.getOverallVotes() + 1 );
    yil.setOverallScore( yil.getOverallScore() + vote );
    newScore = yil.getOverallScore() / yil.getOverallVotes().doubleValue();
  
    updates.put( "overallVotes", yil.getOverallVotes() );
    updates.put( "overallScore", yil.getOverallScore() );
  
    // yilRepository.save( ( (Yil) yil ) );
    modelService.voteOverall( vote, updates, ( (Yil) yil ).getModel() );
    return newScore;
  }
  
}
