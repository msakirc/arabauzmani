package com.msakirc.araba.arabauzmani.response;

public class BaseResponse {
  private Object data;
  private Meta meta;
  
  public Object getData () {
    return data;
  }
  
  public void setData ( Object data ) {
    this.data = data;
  }
  
  public Meta getMeta () {
    return meta;
  }
  
  public void setMeta ( Meta meta ) {
    this.meta = meta;
  }
  
  public BaseResponse ( Object data, Meta meta ) {
    this.data = data;
    this.meta = meta;
  }
  
  public BaseResponse ( Meta m ) {
    this.data = null;
    this.meta = m;
  }
}
