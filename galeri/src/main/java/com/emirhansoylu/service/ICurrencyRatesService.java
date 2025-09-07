package com.emirhansoylu.service;

import com.emirhansoylu.dto.CurrenyRatesResponse;

public interface ICurrencyRatesService {
	public CurrenyRatesResponse getCurrenyRates(String startDate, String endDate);
	

}
