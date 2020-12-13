package com.msakirc.araba.arabauzmani.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LocalizationUtil {
  
  private Locale locale;
  private ResourceBundle messages;
  
  @PostConstruct
  private void updateLocale () {
    if ( Objects.equals( locale, LocaleContextHolder.getLocale() ) )
      return;
    
    locale = LocaleContextHolder.getLocale();
    messages = ResourceBundle.getBundle( "messages", locale );
  }
  
  public String translate ( String message ) {
    updateLocale();
    
    try {
      return messages.getString( message );
    }
    catch ( MissingResourceException e ) {
      return message;
    }
  }
  
}
