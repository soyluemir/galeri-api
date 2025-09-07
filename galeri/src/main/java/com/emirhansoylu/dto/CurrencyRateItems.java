package com.emirhansoylu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CurrencyRateItems {
	
	@JsonProperty("Tarih") //dışarıdan tarih gelecek string olan date aktar
	private String date;
	
	@JsonProperty("TP_DK_USD_A")
	private String usd;

}
