package com.emirhansoylu.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoCustomer  extends DtoBase{
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String tckn;
		
	@NotNull
	private Date birthOfDate;
	
	@NotNull
	private DtoAddress address;
	
	@NotNull
	private DtoAccount account;  //kişiye dto account dönüyorum dolu dolu

}
