package com.msakirc.araba.arabauzmani.dto;

import com.msakirc.araba.arabauzmani.model.Marka;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode( callSuper = true )
@Data
public class MarkaDto extends Marka {
  
  public MarkaDto ( Marka another ) {
    this.countryOfOrigin = another.getCountryOfOrigin();
    this.estetikScore = another.getEstetikScore();
    this.estetikVotes = another.getEstetikVotes();
    this.fiyatScore = another.getFiyatScore();
    this.fiyatVotes = another.getFiyatVotes();
    this.guvenlikScore = another.getGuvenlikScore();
    this.guvenlikVotes = another.getGuvenlikVotes();
    this.id = another.getId();
    this.konforScore = another.getKonforScore();
    this.konforVotes = another.getKonforVotes();
    this.malzemeKalitesiScore = another.getMalzemeKalitesiScore();
    this.malzemeKalitesiVotes = another.getMalzemeKalitesiVotes();
    this.name = another.getName();
    this.overallScore = another.getOverallScore();
    this.overallVotes = another.getOverallVotes();
    this.performansScore = another.getPerformansScore();
    this.performansVotes = another.getPerformansVotes();
    this.servisScore = another.getServisScore();
    this.servisVotes = another.getServisVotes();
    this.text = another.getText();
    this.uzunOmurlulukScore = another.getUzunOmurlulukScore();
    this.uzunOmurlulukVotes = another.getUzunOmurlulukVotes();
    this.yedekParcaScore = another.getYedekParcaScore();
    this.yedekParcaVotes = another.getYedekParcaVotes();
  }
}
