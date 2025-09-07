package com.emirhansoylu.dto;

import java.math.BigDecimal;

import com.emirhansoylu.enums.CurrencyType;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAccount extends DtoBase { // 6 alan döner
	
	@NotNull
	private String accountNo;
	
	@NotNull
	private String iban;
	
	@NotNull
	private BigDecimal amount; //parasal işlemler hassasiyet sebebiyle bigdecimal olarak tutulur sektörde
	
	@NotNull
	private CurrencyType currencyType;

}
