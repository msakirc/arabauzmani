package com.msakirc.araba.arabauzmani.service.impl;

import com.msakirc.araba.arabauzmani.model.BaseEntity;
import com.msakirc.araba.arabauzmani.model.Comparison;
import com.msakirc.araba.arabauzmani.model.Marka;
import com.msakirc.araba.arabauzmani.model.Voteable;
import com.msakirc.araba.arabauzmani.repository.firestore.MarkaRepository;
import com.msakirc.araba.arabauzmani.service.MarkaService;
import com.msakirc.araba.arabauzmani.util.EntityConverter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkaServiceImpl implements MarkaService {
  
  private final MarkaRepository markaRepository;
  
  @Autowired
  public MarkaServiceImpl ( MarkaRepository markaRepository ) {
    this.markaRepository = markaRepository;
  }
  
  @Override
  public List<Marka> findAll () {
    return markaRepository.findAll();
  }
  
  @Override
  public Marka findById ( String id ) {
    return markaRepository.findById( id ).orElseThrow( RuntimeException::new );
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
    
    Marka marka = findById( id );
    return vote( field, marka, vote );
  }
  
  @Override
  public double vote ( Voteable field, BaseEntity entity, Integer vote ) {
    
    Marka marka = ( (Marka) entity );
    double newScore;
  
    Map<String, Object> updates = new HashMap<>();
    updates.put( "id", marka.getId() );
    
    switch ( field ) {
      case PERFORMANS:
        newScore = votePerformans( vote, updates, marka );
        break;
      
      case UZUN_OMURLULUK:
        newScore = voteUzunOmurluluk( vote, updates, marka );
        break;
      
      case FIYAT:
        newScore = voteFiyat( vote, updates, marka );
        break;
      
      case KONFOR:
        newScore = voteKonfor( vote, updates, marka );
        break;
      
      case ESTETIK:
        newScore = voteEstetik( vote, updates, marka );
        break;
      
      case OVERALL:
        newScore = voteOverall( vote, updates, marka );
        break;
      
      case SERVIS:
        marka.setServisVotes( marka.getServisVotes() + 1 );
        marka.setServisScore( marka.getServisScore() + vote );
        newScore = marka.getServisScore() / marka.getServisVotes().doubleValue();
        
        updates.put( "servisVotes", marka.getServisVotes() );
        updates.put( "servisScore", marka.getServisScore() );
  
        // markaRepository.save( marka );
        break;
      
      case YEDEK_PARCA:
        marka.setYedekParcaVotes( marka.getYedekParcaVotes() + 1 );
        marka.setYedekParcaScore( marka.getYedekParcaScore() + vote );
        newScore = marka.getYedekParcaScore() / marka.getYedekParcaVotes().doubleValue();
  
        updates.put( "yedekParcaVotes", marka.getYedekParcaVotes() );
        updates.put( "yedekParcaScore", marka.getYedekParcaScore() );
  
        // markaRepository.save( marka );
        break;
      
      case MALZEME_KALITESI:
        marka.setMalzemeKalitesiVotes( marka.getMalzemeKalitesiVotes() + 1 );
        marka.setMalzemeKalitesiScore( marka.getMalzemeKalitesiScore() + vote );
        newScore = marka.getMalzemeKalitesiScore() / marka.getMalzemeKalitesiVotes().doubleValue();
  
        updates.put( "malzemeKalitesiVotes", marka.getMalzemeKalitesiVotes() );
        updates.put( "malzemeKalitesiScore", marka.getMalzemeKalitesiScore() );
  
        // markaRepository.save( marka );
        break;
      
      case GUVENLIK:
        marka.setGuvenlikVotes( marka.getGuvenlikVotes() + 1 );
        marka.setGuvenlikScore( marka.getGuvenlikScore() + vote );
        newScore = marka.getGuvenlikScore() / marka.getGuvenlikVotes().doubleValue();
  
        updates.put( "guvenlikVotes", marka.getGuvenlikVotes() );
        updates.put( "guvenlikScore", marka.getGuvenlikScore() );
  
        // markaRepository.save( marka );
        break;
      
      default:
        throw new IllegalStateException( "Unexpected value: " + field );
    }
    
    markaRepository.update( marka.getId(), updates);
    
    return newScore;
  }
  
  @Override
  public double voteEstetik ( Integer vote, Map<String, Object> updates, BaseEntity marka ) {
    double newScore;
    marka.setEstetikVotes( marka.getEstetikVotes() + vote );
    marka.setEstetikScore( marka.getEstetikScore() + vote );
    newScore = marka.getEstetikScore() / marka.getEstetikVotes().doubleValue();
  
    updates.put( "estetikVotes", marka.getEstetikVotes() );
    updates.put( "estetikScore", marka.getEstetikScore() );
    
    // markaRepository.save( ( (Marka) marka ) );
    return newScore;
  }
  
  @Override
  public double voteKonfor ( Integer vote, Map<String, Object> updates, BaseEntity marka ) {
    double newScore;
    marka.setKonforVotes( marka.getKonforVotes() + 1 );
    marka.setKonforScore( marka.getKonforScore() + vote );
    newScore = marka.getKonforScore() / marka.getKonforVotes().doubleValue();
    
    updates.put( "konforVotes", marka.getKonforVotes() );
    updates.put( "konforScore", marka.getKonforScore() );
  
    // markaRepository.save( ( (Marka) marka ) );
    return newScore;
  }
  
  @Override
  public double voteFiyat ( Integer vote, Map<String, Object> updates, BaseEntity marka ) {
    double newScore;
    marka.setFiyatVotes( marka.getFiyatVotes() + 1 );
    marka.setFiyatScore( marka.getFiyatScore() + vote );
    newScore = marka.getFiyatScore() / marka.getFiyatVotes().doubleValue();
  
    updates.put( "fiyatVotes", marka.getFiyatVotes() );
    updates.put( "fiyatScore", marka.getFiyatScore() );
  
    // markaRepository.save( ( (Marka) marka ) );
    return newScore;
  }
  
  @Override
  public double votePerformans ( Integer vote, Map<String, Object> updates, BaseEntity marka ) {
    double newScore;
    marka.setPerformansVotes( marka.getPerformansVotes() + 1 );
    marka.setPerformansScore( marka.getPerformansScore() + vote );
    newScore = marka.getPerformansScore() / marka.getPerformansVotes().doubleValue();
  
    updates.put( "performansVotes", marka.getPerformansVotes() );
    updates.put( "performansScore", marka.getPerformansScore() );
  
    // markaRepository.save( ( (Marka) marka ) );
    return newScore;
  }
  
  @Override
  public double voteUzunOmurluluk ( Integer vote, Map<String, Object> updates, BaseEntity marka ) {
    double newScore;
    marka.setUzunOmurlulukVotes( marka.getUzunOmurlulukVotes() + 1 );
    marka.setUzunOmurlulukScore( marka.getUzunOmurlulukScore() + vote );
    newScore = marka.getUzunOmurlulukScore() / marka.getUzunOmurlulukVotes().doubleValue();
  
    updates.put( "uzunOmurlulukVotes", marka.getUzunOmurlulukVotes() );
    updates.put( "uzunOmurlulukScore", marka.getUzunOmurlulukScore() );
  
    // markaRepository.save( ( (Marka) marka ) );
    return newScore;
  }
  
  @Override
  public double voteOverall ( Integer vote, Map<String, Object> updates, BaseEntity marka ) {
    double newScore;
    marka.setOverallVotes( marka.getOverallVotes() + 1 );
    marka.setOverallScore( marka.getOverallScore() + vote );
    newScore = marka.getOverallScore() / marka.getOverallVotes().doubleValue();
  
    updates.put( "overallVotes", marka.getOverallVotes() );
    updates.put( "overallScore", marka.getOverallScore() );
  
    // markaRepository.save( ( (Marka) marka ) );
    return newScore;
  }
  
}
