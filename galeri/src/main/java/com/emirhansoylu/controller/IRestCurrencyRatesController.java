package com.emirhansoylu.controller;

import com.emirhansoylu.dto.CurrenyRatesResponse;

public interface IRestCurrencyRatesController {
	
	public RootEntity<CurrenyRatesResponse> getCurrencyRates(String startDate, String endDate);

}
