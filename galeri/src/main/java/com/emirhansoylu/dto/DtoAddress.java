package com.emirhansoylu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAddress extends DtoBase { //id ve createtime
	
	
	private String city;
	
	private String district;
	
	
	private String neighborhood;

	private String street;
	

}
