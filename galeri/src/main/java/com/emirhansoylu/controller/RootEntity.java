package com.emirhansoylu.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = Include.NON_NULL) //null değerleri döndürme
public class RootEntity<T> { //controllerden aynı formatta cevap dönmek için

	private Integer status;
	
	private T payload;
	
	private String errorMessage;
	
	public static <T> RootEntity<T> ok(T payload){ // T tipinde dönecek ok adlı fonksiyon T tipinde bir data gelecek sana
		//static yaptım class ismiyle dışarıdan erişilebilsin
		RootEntity<T> rootEntity = new RootEntity<>();
		rootEntity.setStatus(200); 
		rootEntity.setPayload(payload); 
		rootEntity.setErrorMessage(null);
		return rootEntity;
		
	}
	 public static <T> RootEntity<T> error(String errorMessage){ // dışarıdan t tipigelecek error olursa burayı çağırır
		 RootEntity<T> rootEntity = new RootEntity<>();
		 rootEntity.setStatus(500); //hata mesajı
		 rootEntity.setPayload(null); //başarısız oldu
		 rootEntity.setErrorMessage(errorMessage);
		 return rootEntity;
		 
	 }
	
	
	
	
	
	
	
	
	
	
	
	
}
