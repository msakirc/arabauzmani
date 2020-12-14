package com.msakirc.araba.arabauzmani.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirestoreConfig {
  
  private final Firestore db;
  
  @Autowired
  public FirestoreConfig () throws IOException {
    
    FirestoreOptions firestoreOptions =
        FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId( "araba-uzmani" )
                        .setCredentials( GoogleCredentials.getApplicationDefault() )
                        .build();
    
    this.db = firestoreOptions.getService();
  }
  
  public Firestore getDb () {
    return db;
  }
}
