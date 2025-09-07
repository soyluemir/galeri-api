package com.emirhansoylu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoUser extends DtoBase { //id ve createtime yukarıdan geliyor
	
	private String username;
	
	private String password;
	
	

}
