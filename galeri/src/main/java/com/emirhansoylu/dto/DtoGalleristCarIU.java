package com.emirhansoylu.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGalleristCarIU {
	
	@NotNull
	private Long galleristId;  //dışarıdan gelen idler
	
	@NotNull
	private Long carId;

}
