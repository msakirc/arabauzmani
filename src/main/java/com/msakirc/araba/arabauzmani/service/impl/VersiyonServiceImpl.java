package com.msakirc.araba.arabauzmani.service.impl;

import com.msakirc.araba.arabauzmani.model.BaseEntity;
import com.msakirc.araba.arabauzmani.model.Comparison;
import com.msakirc.araba.arabauzmani.model.Model;
import com.msakirc.araba.arabauzmani.model.Versiyon;
import com.msakirc.araba.arabauzmani.model.Voteable;
import com.msakirc.araba.arabauzmani.repository.firestore.VersiyonRepository;
import com.msakirc.araba.arabauzmani.service.VersiyonService;
import com.msakirc.araba.arabauzmani.service.YilService;
import com.msakirc.araba.arabauzmani.util.EntityConverter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class VersiyonServiceImpl implements VersiyonService {
  
  private final VersiyonRepository versiyonRepository;
  private final YilService yilService;
  private final EntityConverter entityConverter;
  
  @Autowired
  public VersiyonServiceImpl ( VersiyonRepository versiyonRepository, YilService yilService, @Lazy EntityConverter entityConverter ) {
    this.versiyonRepository = versiyonRepository;
    this.yilService = yilService;
    this.entityConverter = entityConverter;
  }
  
  @Override
  public Versiyon findById ( String id ) {
    return versiyonRepository.findById( id ).orElseThrow( RuntimeException::new );
  }
  
  @Override
  public List<Versiyon> findAll ( String yilId ) {
    return versiyonRepository.findByYilId( yilId );
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
  
    Versiyon versiyon = findById( id );
    return vote( field, versiyon, vote );
  }
  
  @Override
  public double vote ( Voteable field, BaseEntity entity, Integer vote ) {
    
    Versiyon versiyon = ( (Versiyon) entity );
    double newScore;
  
    Map<String, Object> updates = new HashMap<>();
    updates.put( "id", versiyon.getId() );
    
    switch ( field ) {
      case PERFORMANS:
        newScore = votePerformans( vote, updates, versiyon );
        break;
  
      case UZUN_OMURLULUK:
        newScore = voteUzunOmurluluk( vote, updates, versiyon );
        break;
  
      case FIYAT:
        newScore = voteFiyat( vote, updates, versiyon );
        break;
  
      case KONFOR:
        newScore = voteKonfor( vote, updates, versiyon );
        break;
  
      case ESTETIK:
        newScore = voteEstetik( vote, updates, versiyon );
        break;
      
      case OVERALL:
        newScore = voteOverall( vote, updates, versiyon );
        break;
      
      default:
        throw new IllegalStateException( "Unexpected value: " + field );
    }
    
    return newScore;
  }
  
  @Override
  public double voteEstetik ( Integer vote, Map<String, Object> updates, BaseEntity versiyon ) {
    double newScore;
    versiyon.setEstetikVotes( versiyon.getEstetikVotes() + vote );
    versiyon.setEstetikScore( versiyon.getEstetikScore() + vote );
    newScore = versiyon.getEstetikScore() / versiyon.getEstetikVotes().doubleValue();
  
    updates.put( "estetikVotes", versiyon.getEstetikVotes() );
    updates.put( "estetikScore", versiyon.getEstetikScore() );
  
    // versiyonRepository.save( ( (Versiyon) versiyon ) );
    BaseEntity yil = yilService.findById( ( (Versiyon) versiyon ).getYilId() );
    yilService.voteEstetik( vote, updates, yil );
    return newScore;
  }
  
  @Override
  public double voteKonfor ( Integer vote, Map<String, Object> updates, BaseEntity versiyon ) {
    double newScore;
    versiyon.setKonforVotes( versiyon.getKonforVotes() + 1 );
    versiyon.setKonforScore( versiyon.getKonforScore() + vote );
    newScore = versiyon.getKonforScore() / versiyon.getKonforVotes().doubleValue();
  
    updates.put( "konforVotes", versiyon.getKonforVotes() );
    updates.put( "konforScore", versiyon.getKonforScore() );
  
    // versiyonRepository.save( ( (Versiyon) versiyon ) );
    BaseEntity yil = yilService.findById( ( (Versiyon) versiyon ).getYilId() );
    yilService.voteKonfor( vote, updates, yil );
    return newScore;
  }
  
  @Override
  public double voteFiyat ( Integer vote, Map<String, Object> updates, BaseEntity versiyon ) {
    double newScore;
    versiyon.setFiyatVotes( versiyon.getFiyatVotes() + 1 );
    versiyon.setFiyatScore( versiyon.getFiyatScore() + vote );
    newScore = versiyon.getFiyatScore() / versiyon.getFiyatVotes().doubleValue();
  
    updates.put( "fiyatVotes", versiyon.getFiyatVotes() );
    updates.put( "fiyatScore", versiyon.getFiyatScore() );
  
    // versiyonRepository.save( ( (Versiyon) versiyon ) );
    BaseEntity yil = yilService.findById( ( (Versiyon) versiyon ).getYilId() );
    yilService.voteFiyat( vote, updates, yil );
    return newScore;
  }
  
  @Override
  public double votePerformans ( Integer vote, Map<String, Object> updates, BaseEntity versiyon ) {
    double newScore;
    versiyon.setPerformansVotes( versiyon.getPerformansVotes() + 1 );
    versiyon.setPerformansScore( versiyon.getPerformansScore() + vote );
    newScore = versiyon.getPerformansScore() / versiyon.getPerformansVotes().doubleValue();
  
    updates.put( "performansVotes", versiyon.getPerformansVotes() );
    updates.put( "performansScore", versiyon.getPerformansScore() );
  
    // versiyonRepository.save( ( (Versiyon) versiyon ) );
    BaseEntity yil = yilService.findById( ( (Versiyon) versiyon ).getYilId() );
    yilService.votePerformans( vote, updates, yil );
    return newScore;
  }
  
  @Override
  public double voteUzunOmurluluk ( Integer vote, Map<String, Object> updates, BaseEntity versiyon ) {
    double newScore;
    versiyon.setUzunOmurlulukVotes( versiyon.getUzunOmurlulukVotes() + 1 );
    versiyon.setUzunOmurlulukScore( versiyon.getUzunOmurlulukScore() + vote );
    newScore = versiyon.getUzunOmurlulukScore() / versiyon.getUzunOmurlulukVotes().doubleValue();
  
    updates.put( "uzunOmurlulukVotes", versiyon.getUzunOmurlulukVotes() );
    updates.put( "uzunOmurlulukScore", versiyon.getUzunOmurlulukScore() );
  
    // versiyonRepository.save( ( (Versiyon) versiyon ) );
    BaseEntity yil = yilService.findById( ( (Versiyon) versiyon ).getYilId() );
    yilService.voteUzunOmurluluk( vote, updates, yil );
    return newScore;
  }
  
  @Override
  public double voteOverall ( Integer vote, Map<String, Object> updates, BaseEntity versiyon ) {
    double newScore;
    versiyon.setOverallVotes( versiyon.getOverallVotes() + 1 );
    versiyon.setOverallScore( versiyon.getOverallScore() + vote );
    newScore = versiyon.getOverallScore() / versiyon.getOverallVotes().doubleValue();
  
    updates.put( "overallVotes", versiyon.getOverallVotes() );
    updates.put( "overallScore", versiyon.getOverallScore() );
  
    // versiyonRepository.save( ( (Versiyon) versiyon ) );
    BaseEntity yil = yilService.findById( ( (Versiyon) versiyon ).getYilId() );
    yilService.voteOverall( vote, updates, yil );
    return newScore;
  }
  
}
