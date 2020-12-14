package com.msakirc.araba.arabauzmani.repository.firestore;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.msakirc.araba.arabauzmani.config.FirestoreConfig;
import com.msakirc.araba.arabauzmani.model.Model;
import com.msakirc.araba.arabauzmani.model.Yil;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class YilRepository {
  
  private final FirestoreConfig firestore;
  
  @Autowired
  public YilRepository ( FirestoreConfig firestore ) {
    this.firestore = firestore;
  }
  
  public void update ( String id, Map<String, Object> updates ) {
    DocumentReference yil = getCollection().document( id );
    ApiFuture<WriteResult> update = yil.update( updates );
    
    try {
      update.get();
    }
    catch ( InterruptedException | ExecutionException e ) {
      e.printStackTrace();
    }
  }
  
  public Optional<Yil> findById ( String id ) {
    try {
      DocumentSnapshot document = getCollection().document( id ).get().get();
      
      if ( document.exists() )
        return Optional.ofNullable( document.toObject( Yil.class ) );
    }
    catch ( InterruptedException | ExecutionException e ) {
      e.printStackTrace();
    }
    
    return Optional.empty();
  }
  
  public List<Yil> findByModelId ( String modelId ) {
    
    try {
      return getCollection().whereEqualTo( "modelId", modelId )
                            .get().get().getDocuments()
                            .stream()
                            .map( queryDocumentSnapshot -> queryDocumentSnapshot.toObject( Yil.class ) )
                            .collect( Collectors.toList() );
    }
    catch ( InterruptedException | ExecutionException e ) {
      e.printStackTrace();
    }
    
    return Collections.emptyList();
  }
  
  public CollectionReference getCollection () {
    return firestore.getDb().collection( "yil" );
  }
  
  
}
