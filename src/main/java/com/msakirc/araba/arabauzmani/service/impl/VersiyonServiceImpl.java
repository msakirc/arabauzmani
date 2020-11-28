package com.msakirc.araba.arabauzmani.service.impl;

import com.msakirc.araba.arabauzmani.model.BaseEntity;
import com.msakirc.araba.arabauzmani.model.Comparison;
import com.msakirc.araba.arabauzmani.model.Versiyon;
import com.msakirc.araba.arabauzmani.model.Voteable;
import com.msakirc.araba.arabauzmani.repository.VersiyonRepository;
import com.msakirc.araba.arabauzmani.service.VersiyonService;
import com.msakirc.araba.arabauzmani.service.YilService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersiyonServiceImpl implements VersiyonService {
  
  private final VersiyonRepository versiyonRepository;
  
  private final YilService yilService;
  
  @Autowired
  public VersiyonServiceImpl ( VersiyonRepository versiyonRepository, YilService yilService ) {
    this.versiyonRepository = versiyonRepository;
    this.yilService = yilService;
  }
  
  @Override
  public Versiyon findById ( Integer id ) {
    return versiyonRepository.findById( id ).orElseThrow( RuntimeException::new );
  }
  
  @Override
  public List<Versiyon> findAll ( Integer yilId ) {
    return versiyonRepository.findByYilId( yilId );
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
  
    Versiyon versiyon = findById( id );
    return vote( field, versiyon, vote );
  }
  
  @Override
  public double vote ( Voteable field, BaseEntity entity, Integer vote ) {
    
    Versiyon versiyon = ( (Versiyon) entity );
    double newScore;
    
    switch ( field ) {
      case PERFORMANS:
        newScore = votePerformans( vote, versiyon );
        break;
  
      case DAYANIKLILIK:
        newScore = voteDayaniklilik( vote, versiyon );
        break;
  
      case FIYAT:
        newScore = voteFiyat( vote, versiyon );
        break;
  
      case KONFOR:
        newScore = voteKonfor( vote, versiyon );
        break;
  
      case ESTETIK:
        newScore = voteEstetik( vote, versiyon );
        break;
      
      case OVERALL:
        newScore = voteOverall( vote, versiyon );
        break;
      
      default:
        throw new IllegalStateException( "Unexpected value: " + field );
    }
    
    return newScore;
  }
  
  @Override
  public double voteEstetik ( Integer vote, BaseEntity versiyon ) {
    double newScore;
    versiyon.setEstetikVotes( versiyon.getEstetikVotes() + vote );
    versiyon.setEstetikScore( versiyon.getEstetikScore() + vote );
    newScore = versiyon.getEstetikScore() / versiyon.getEstetikVotes().doubleValue();
  
    versiyonRepository.save( ( (Versiyon) versiyon ) );
    yilService.voteEstetik( vote, ( (Versiyon) versiyon ).getYil() );
    return newScore;
  }
  
  @Override
  public double voteKonfor ( Integer vote, BaseEntity versiyon ) {
    double newScore;
    versiyon.setKonforVotes( versiyon.getKonforVotes() + 1 );
    versiyon.setKonforScore( versiyon.getKonforScore() + vote );
    newScore = versiyon.getKonforScore() / versiyon.getKonforVotes().doubleValue();
  
    versiyonRepository.save( ( (Versiyon) versiyon ) );
    yilService.voteKonfor( vote, ( (Versiyon) versiyon ).getYil() );
    return newScore;
  }
  
  @Override
  public double votePerformans ( Integer vote, BaseEntity versiyon ) {
    double newScore;
    versiyon.setPerformansVotes( versiyon.getPerformansVotes() + 1 );
    versiyon.setPerformansScore( versiyon.getPerformansScore() + vote );
    newScore = versiyon.getPerformansScore() / versiyon.getPerformansVotes().doubleValue();
  
    versiyonRepository.save( ( (Versiyon) versiyon ) );
    yilService.votePerformans( vote, ( (Versiyon) versiyon ).getYil() );
    return newScore;
  }
  
  @Override
  public double voteDayaniklilik ( Integer vote, BaseEntity versiyon ) {
    double newScore;
    versiyon.setDayaniklilikVotes( versiyon.getDayaniklilikVotes() + 1 );
    versiyon.setDayaniklilikScore( versiyon.getDayaniklilikScore() + vote );
    newScore = versiyon.getDayaniklilikScore() / versiyon.getDayaniklilikVotes().doubleValue();
    
    versiyonRepository.save( ( (Versiyon) versiyon ) );
    yilService.voteDayaniklilik( vote, ( (Versiyon) versiyon ).getYil() );
    return newScore;
  }
  
  @Override
  public double voteFiyat ( Integer vote, BaseEntity versiyon ) {
    double newScore;
    versiyon.setFiyatVotes( versiyon.getFiyatVotes() + 1 );
    versiyon.setFiyatScore( versiyon.getFiyatScore() + vote );
    newScore = versiyon.getFiyatScore() / versiyon.getFiyatVotes().doubleValue();
  
    versiyonRepository.save( ( (Versiyon) versiyon ) );
    yilService.voteFiyat( vote, ( (Versiyon) versiyon ).getYil() );
    return newScore;
  }
  
  @Override
  public double voteOverall ( Integer vote, BaseEntity versiyon ) {
    double newScore;
    versiyon.setOverallVotes( versiyon.getOverallVotes() + 1 );
    versiyon.setOverallScore( versiyon.getOverallScore() + vote );
    newScore = versiyon.getOverallScore() / versiyon.getOverallVotes().doubleValue();
    
    versiyonRepository.save( ( (Versiyon) versiyon ) );
    yilService.voteOverall( vote, ( (Versiyon) versiyon ).getYil() );
    return newScore;
  }
  
}
