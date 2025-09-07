package com.emirhansoylu.exception;

import java.security.PrivateKey;

import lombok.Getter;

@Getter
public enum MessageType {
	
	NO_RECORD_EXIST("1004","Kayıt bulunamadı"),
	TOKEN_IS_EXPIRED("1005","token expired oldu"),
	USERNAME_NOT_FOUND("1006","Username bulunamadı"),
	USERNAME_OR_PASSWORD_INVALID("1007","Kullanıcı adı veya şifre hatalı"),
	REFRESH_TOKEN_NOT_FOUND("1008","Refresh Token bulunamadı"),
	CURRENY_RATES_IS_OCCURED("1010","Döviz kuru alınamadı"),
	REFRESH_TOKEN_IS_EXPIRED("1009","Refreshtokenin süresi bitmiştir"),
	CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1011","Müşterinin parası yeterli değildir"),
	CAR_STATUS_IS_ALREADY_SALED("1012", "Araba satılmış göründüğü için satılamaz"),
	GENERAL_EXCEPTION("9999","Genel bir hata oluştu");
	
	private String code;  // setleyeceğimiz değerler için değişkenler
	
	private String message;
	
	MessageType(String code, String message) {
		this.code = code;  //yukarıdaki code'a parametre olarak gelen code'yi atıyor
		this.message = message; //yukarıdaki messageye parametre olarak gelen messageyi atar
		
		
	}
	}
