package com.emirhansoylu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoGallerist extends DtoBase { //dışarıya verirkenki dto
	
	
	private String firstName;
	
	private String lastName;
	

	private DtoAddress address;

}
