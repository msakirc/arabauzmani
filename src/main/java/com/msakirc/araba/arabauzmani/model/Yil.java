package com.msakirc.araba.arabauzmani.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode( callSuper = true )
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
public class Yil extends BaseEntity {
  
  @OneToOne( cascade = CascadeType.MERGE )
  @JoinColumn( name = "marka_id", referencedColumnName = "id")
  private Marka marka;
  
  @OneToOne( cascade = CascadeType.MERGE )
  @JoinColumn( name = "model_id", referencedColumnName = "id")
  private Model model;
  
  private String name;
  
}
