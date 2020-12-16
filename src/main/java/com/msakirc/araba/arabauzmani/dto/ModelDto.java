package com.msakirc.araba.arabauzmani.dto;

import com.msakirc.araba.arabauzmani.model.Marka;
import com.msakirc.araba.arabauzmani.model.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode( callSuper = true )
@Data
public class ModelDto extends Model {
  
  private Marka marka;
  
  public ModelDto ( Model another ) {
    this.estetikScore = another.getEstetikScore();
    this.estetikVotes = another.getEstetikVotes();
    this.fiyatScore = another.getFiyatScore();
    this.fiyatVotes = another.getFiyatVotes();
    this.id = another.getId();
    this.konforScore = another.getKonforScore();
    this.konforVotes = another.getKonforVotes();
    this.name = another.getName();
    this.overallScore = another.getOverallScore();
    this.overallVotes = another.getOverallVotes();
    this.performansScore = another.getPerformansScore();
    this.performansVotes = another.getPerformansVotes();
    this.text = another.getText();
    this.uzunOmurlulukScore = another.getUzunOmurlulukScore();
    this.uzunOmurlulukVotes = another.getUzunOmurlulukVotes();
  }
}
