package com.emirhansoylu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {  //accestoken ve refreshtoken döneceğim için tanımlıyorum
	
	private String accesToken;
	
	private String refreshToken;

}
