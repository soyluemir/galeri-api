package com.emirhansoylu.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
	
	private MessageType messageType; //mesaj türü
	
	private String offStatic; //değişkenimiz statik oalrak setlemek istediğimiz değer
	
	
	
	public String prepareErrorMessage() { //hata mesajını hazırlayan metodumuz
		
		StringBuilder builder = new StringBuilder(); //stringleri birleştirmek için
		builder.append(messageType.getMessage()); //yukarıdaki setlenmiş olarak gelen message typenin mesajını ver
		if (this.offStatic!=null) { //değer setlendiyse
			
			builder.append(":" + offStatic); //setlenmiş olarak eklenen offstatici de onun üzerine  ekle
			
		}
		return builder.toString();  //geriye de o builderi dön
		
	}

}
