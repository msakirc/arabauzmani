package com.msakirc.araba.arabauzmani.repository.firestore;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.msakirc.araba.arabauzmani.config.FirestoreConfig;
import com.msakirc.araba.arabauzmani.model.Model;
import com.msakirc.araba.arabauzmani.model.Versiyon;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VersiyonRepository {
  
  private final FirestoreConfig firestore;
  
  @Autowired
  public VersiyonRepository ( FirestoreConfig firestore ) {
    this.firestore = firestore;
  }
  
  public void update ( String id, Map<String, Object> updates ) {
    DocumentReference versiyon = getCollection().document( id );
    ApiFuture<WriteResult> update = versiyon.update( updates );
    
    try {
      update.get();
    }
    catch ( InterruptedException | ExecutionException e ) {
      e.printStackTrace();
    }
  }
  
  public Optional<Versiyon> findById ( String id ) {
    try {
      DocumentSnapshot document = getCollection().document( id ).get().get();
      
      if ( document.exists() )
        return Optional.ofNullable( document.toObject( Versiyon.class ) );
    }
    catch ( InterruptedException | ExecutionException e ) {
      e.printStackTrace();
    }
    
    return Optional.empty();
  }
  
  public List<Versiyon> findByYilId ( String yilId ) {
    
    try {
      return getCollection().whereEqualTo( "yilId", yilId )
                            .get().get().getDocuments()
                            .stream()
                            .map( queryDocumentSnapshot -> queryDocumentSnapshot.toObject( Versiyon.class ) )
                            .collect( Collectors.toList() );
    }
    catch ( InterruptedException | ExecutionException e ) {
      e.printStackTrace();
    }
    
    return Collections.emptyList();
  }
  
  public CollectionReference getCollection () {
    return firestore.getDb().collection( "versiyon" );
  }
  
  
}
