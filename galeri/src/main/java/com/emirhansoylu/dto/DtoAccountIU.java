package com.emirhansoylu.dto;

import java.math.BigDecimal;

import com.emirhansoylu.enums.CurrencyType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAccountIU { //4 alan döner extends yok bunda
	
	@NotNull
	private String accountNo;
	
	@NotNull
	private String iban;
	
	@NotNull
	private BigDecimal amount; //parasal işlemler hassasiyet sebebiyle bigdecimal olarak tutulur sektörde
	
	@NotNull
	private CurrencyType currencyType;

}
