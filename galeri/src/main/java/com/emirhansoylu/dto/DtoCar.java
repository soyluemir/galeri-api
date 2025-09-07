package com.emirhansoylu.dto;

import java.math.BigDecimal;

import com.emirhansoylu.enums.CarStatusType;
import com.emirhansoylu.enums.CurrencyType;

import lombok.Data;

@Data
public class DtoCar  extends  DtoBase{ //geriye dönerken
	
private String plaka;
	
	private String brand;
	
	private String model;
	
	private Integer productionYear; 
	
	private BigDecimal price;
	
	private CurrencyType currencyType;
	
	private BigDecimal damagePrice;
	
	private CarStatusType carStatusType;

}
