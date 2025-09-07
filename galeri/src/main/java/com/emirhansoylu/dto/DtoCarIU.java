package com.emirhansoylu.dto;

import java.math.BigDecimal;

import com.emirhansoylu.enums.CarStatusType;
import com.emirhansoylu.enums.CurrencyType;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoCarIU { //içeri alırken
	

	@NotNull
	private String plaka;
	
	@NotNull
	private String brand;
	
	@NotNull
	private String model;
	
	@NotNull
	private Integer productionYear; 
	
	@NotNull
	private BigDecimal price;
	
	@NotNull
	private CurrencyType currencyType;
	
	@NotNull
	private BigDecimal damagePrice;
	
	@NotNull
	private CarStatusType carStatusType;

}
