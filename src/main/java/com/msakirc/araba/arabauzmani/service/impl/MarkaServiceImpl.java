package com.msakirc.araba.arabauzmani.service.impl;

import com.msakirc.araba.arabauzmani.model.BaseEntity;
import com.msakirc.araba.arabauzmani.model.Comparison;
import com.msakirc.araba.arabauzmani.model.Marka;
import com.msakirc.araba.arabauzmani.model.Voteable;
import com.msakirc.araba.arabauzmani.repository.MarkaRepository;
import com.msakirc.araba.arabauzmani.service.MarkaService;
import java.util.ArrayList;
import java.util.List;
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
    List<Marka> result = new ArrayList<>();
    markaRepository.findAll().forEach( result::add );
    return result;
  }
  
  @Override
  public Marka findById ( Integer id ) {
    return markaRepository.findById( id ).orElseThrow( RuntimeException::new );
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
    
    Marka marka = findById( id );
    return vote( field, marka, vote );
  }
  
  @Override
  public double vote ( Voteable field, BaseEntity entity, Integer vote ) {
    
    Marka marka = ( (Marka) entity );
    double newScore;
    
    switch ( field ) {
      case PERFORMANS:
        newScore = votePerformans( vote, marka );
        break;
      
      case UZUN_OMURLULUK:
        newScore = voteUzunOmurluluk( vote, marka );
        break;
      
      case FIYAT:
        newScore = voteFiyat( vote, marka );
        break;
      
      case KONFOR:
        newScore = voteKonfor( vote, marka );
        break;
      
      case ESTETIK:
        newScore = voteEstetik( vote, marka );
        break;
      
      case OVERALL:
        newScore = voteOverall( vote, marka );
        break;
      
      case SERVIS:
        marka.setServisVotes( marka.getServisVotes() + 1 );
        marka.setServisScore( marka.getServisScore() + vote );
        newScore = marka.getServisScore() / marka.getServisVotes().doubleValue();
        markaRepository.save( marka );
        break;
      
      case YEDEK_PARCA:
        marka.setYedekParcaVotes( marka.getYedekParcaVotes() + 1 );
        marka.setYedekParcaScore( marka.getYedekParcaScore() + vote );
        newScore = marka.getYedekParcaScore() / marka.getYedekParcaVotes().doubleValue();
        markaRepository.save( marka );
        break;
      
      case MALZEME_KALITESI:
        marka.setMalzemeKalitesiVotes( marka.getMalzemeKalitesiVotes() + 1 );
        marka.setMalzemeKalitesiScore( marka.getMalzemeKalitesiScore() + vote );
        newScore = marka.getMalzemeKalitesiScore() / marka.getMalzemeKalitesiVotes().doubleValue();
        markaRepository.save( marka );
        break;
      
      case GUVENLIK:
        marka.setGuvenlikVotes( marka.getGuvenlikVotes() + 1 );
        marka.setGuvenlikScore( marka.getGuvenlikScore() + vote );
        newScore = marka.getGuvenlikScore() / marka.getGuvenlikVotes().doubleValue();
        markaRepository.save( marka );
        break;
      
      default:
        throw new IllegalStateException( "Unexpected value: " + field );
    }
    
    return newScore;
  }
  
  @Override
  public double voteEstetik ( Integer vote, BaseEntity marka ) {
    double newScore;
    marka.setEstetikVotes( marka.getEstetikVotes() + vote );
    marka.setEstetikScore( marka.getEstetikScore() + vote );
    newScore = marka.getEstetikScore() / marka.getEstetikVotes().doubleValue();
    
    markaRepository.save( ( (Marka) marka ) );
    return newScore;
  }
  
  @Override
  public double voteKonfor ( Integer vote, BaseEntity marka ) {
    double newScore;
    marka.setKonforVotes( marka.getKonforVotes() + 1 );
    marka.setKonforScore( marka.getKonforScore() + vote );
    newScore = marka.getKonforScore() / marka.getKonforVotes().doubleValue();
    
    markaRepository.save( ( (Marka) marka ) );
    return newScore;
  }
  
  @Override
  public double voteFiyat ( Integer vote, BaseEntity marka ) {
    double newScore;
    marka.setFiyatVotes( marka.getFiyatVotes() + 1 );
    marka.setFiyatScore( marka.getFiyatScore() + vote );
    newScore = marka.getFiyatScore() / marka.getFiyatVotes().doubleValue();
    
    markaRepository.save( ( (Marka) marka ) );
    return newScore;
  }
  
  @Override
  public double votePerformans ( Integer vote, BaseEntity marka ) {
    double newScore;
    marka.setPerformansVotes( marka.getPerformansVotes() + 1 );
    marka.setPerformansScore( marka.getPerformansScore() + vote );
    newScore = marka.getPerformansScore() / marka.getPerformansVotes().doubleValue();
    
    markaRepository.save( ( (Marka) marka ) );
    return newScore;
  }
  
  @Override
  public double voteUzunOmurluluk ( Integer vote, BaseEntity marka ) {
    double newScore;
    marka.setUzunOmurlulukVotes( marka.getUzunOmurlulukVotes() + 1 );
    marka.setUzunOmurlulukScore( marka.getUzunOmurlulukScore() + vote );
    newScore = marka.getUzunOmurlulukScore() / marka.getUzunOmurlulukVotes().doubleValue();
    
    markaRepository.save( ( (Marka) marka ) );
    return newScore;
  }
  
  @Override
  public double voteOverall ( Integer vote, BaseEntity marka ) {
    double newScore;
    marka.setOverallVotes( marka.getOverallVotes() + 1 );
    marka.setOverallScore( marka.getOverallScore() + vote );
    newScore = marka.getOverallScore() / marka.getOverallVotes().doubleValue();
    
    markaRepository.save( ( (Marka) marka ) );
    return newScore;
  }
  
}
