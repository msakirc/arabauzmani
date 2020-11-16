package com.msakirc.araba.arabauzmani.util;

import static java.util.Objects.isNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.msakirc.araba.arabauzmani.response.BaseResponse;
import com.msakirc.araba.arabauzmani.response.Meta;

@Component
public class ResponseBuilderUtil {
  
  private static LocalizationUtil localizationUtil;
  
  @Autowired
  public ResponseBuilderUtil ( LocalizationUtil localizationUtil ) {
    ResponseBuilderUtil.localizationUtil = localizationUtil;
  }
  
  //TODO improve, populate variations
  
  public static ResponseEntity<BaseResponse> createResponse ( Object data ) {
    if ( isNull( data ) )
      return createFailResponse();
    
    return createOkResponse( data );
  }
  
  public static ResponseEntity<BaseResponse> createOkResponse ( Object data ) {
    Meta m = new Meta();
    m.setStatus( HttpStatus.OK );
    m.setResponse( "ok." );
    BaseResponse baseResponse = new BaseResponse( data, m );
    return new ResponseEntity<>( baseResponse, HttpStatus.OK );
  }
  
  public static ResponseEntity<BaseResponse> createOkResponse ( Object data, String message ) {
    Meta m = new Meta();
    m.setStatus( HttpStatus.OK );
    m.setResponse( localizationUtil.translate( message ) );
    BaseResponse baseResponse = new BaseResponse( data, m );
    return new ResponseEntity<>( baseResponse, HttpStatus.OK );
  }
  
  public static ResponseEntity<BaseResponse> createFailResponse () {
    Meta m = new Meta();
    m.setStatus( HttpStatus.OK );
    m.setResponse( "sistemde geçici bir hata oluştu." );
    BaseResponse baseResponse = new BaseResponse( m );
    return new ResponseEntity<>( baseResponse, HttpStatus.INTERNAL_SERVER_ERROR );
  }
  
  public static ResponseEntity<Object> createExceptionResponse ( Meta meta ) {
    BaseResponse baseResponse = new BaseResponse( meta );
    return new ResponseEntity<>( baseResponse, meta.getStatus() );
  }
}
