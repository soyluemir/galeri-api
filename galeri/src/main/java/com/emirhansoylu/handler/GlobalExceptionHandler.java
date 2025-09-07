package com.emirhansoylu.handler;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.emirhansoylu.exception.BaseException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler (value = {BaseException.class}) //exceptinumuzun tipi verdik böyle exception fırlatırsak sen yakala
	public ResponseEntity<ApiError<?>> handleBaseException(BaseException ex, WebRequest request) { //ve exceptionu parametre olarak geçiyoruz
	return	 ResponseEntity.badRequest().body(createApiError(ex.getMessage(), request));
		//en son yazdığım kısım. ? ise ne döneceğim belli değil demek
	}
	
	@ExceptionHandler (value = {MethodArgumentNotValidException.class})//bunu da yakalamamız lazım. springvalidaiton exceptionu yakalamak içinn
	public ResponseEntity<ApiError<Map<String, List<String>>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
		 //firstname 10 karakter olamaz gibi hatalar
		Map<String, List<String>> map = new HashMap<>(); //bir tane fieldin bir çok hatası olabilir o yüzden map
		for (ObjectError objectError : ex.getBindingResult().getAllErrors()) { //tüm hatalar üzerinde dönüyruz
			String fieldname =((FieldError)objectError).getField(); // hangi field için spring validationun verdiği hatayı buluyoruz			
			if(map.containsKey(fieldname)) { //daha önceden bu field için hata var mı varsa aşağı gir 
				map.put(fieldname, addValue(map.get(fieldname), objectError.getDefaultMessage()));			
			}else { // hata yoksa  buraya gir yeni arraylist oluştur ve gelen getdefaultmessageyi ekle
				map.put(fieldname, addValue(new ArrayList<>(), objectError.getDefaultMessage()));	
			}
		}			
		return ResponseEntity.badRequest().body(createApiError(map, request));	//mesaj yerine map verdik
	}
	private List<String> addValue(List<String> list, String newValue){
		list.add(newValue);  //benden liste ve newvalue alsın ve listeye yeni gelen değeri eklesin ve listi dönsün
		return list;
	}
	
	private String getHostName() {
		try {
			 return Inet4Address.getLocalHost().getHostName();  //hostnameyi almak için sadece
		} catch (UnknownHostException e) {
			
			e.printStackTrace(); 
		}
		return null;  //değer alamazsak boş döndür
	}
	
	public <E> ApiError<E> createApiError(E message, WebRequest request){
		
		ApiError<E> apiError = new ApiError<>();
		apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()); //500 hatası
		
		Exception<E> exception = new Exception<>();
		exception.setPath(request.getDescription(false).substring(4)); //istek attığımız url adrrsini verir
		exception.setCreateTime(new Date()); //bu tarihte hata oluştu
		exception.setMessage(message);  //parametre olarak gelen mesaj
		exception.setHostName(getHostName());
		
		apiError.setException(exception);
		
		 return apiError;
		
		
	}
}
