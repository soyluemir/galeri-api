package com.emirhansoylu.handler;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exception<E> {
	
	private String path; //hangi adreste hata aldık
	
	private Date createTime; //ne zaman hata alındı 
	
	private String hostName; //hangi adreste hata alındı.
	
	private E message; // e tipinde mesaj alacaksın generic //hata mesajı

}
