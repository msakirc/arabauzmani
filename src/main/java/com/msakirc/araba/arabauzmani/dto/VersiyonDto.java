package com.msakirc.araba.arabauzmani.dto;

import com.msakirc.araba.arabauzmani.model.Marka;
import com.msakirc.araba.arabauzmani.model.Model;
import com.msakirc.araba.arabauzmani.model.Versiyon;
import com.msakirc.araba.arabauzmani.model.Yil;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode( callSuper = true )
@Data
public class VersiyonDto extends Versiyon {
  
  private Yil yil;
  
  private Model model;
  
  private Marka marka;
  
  public VersiyonDto ( Versiyon another ) {
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
