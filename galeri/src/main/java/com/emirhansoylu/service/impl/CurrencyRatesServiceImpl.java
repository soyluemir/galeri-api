package com.emirhansoylu.service.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.emirhansoylu.dto.CurrenyRatesResponse;
import com.emirhansoylu.exception.BaseException;
import com.emirhansoylu.exception.ErrorMessage;
import com.emirhansoylu.exception.MessageType;
import com.emirhansoylu.service.ICurrencyRatesService;

@Service
public class CurrencyRatesServiceImpl  implements ICurrencyRatesService{

	@Override
	public CurrenyRatesResponse getCurrenyRates(String startDate, String endDate) {
		
		String rootURL= "https://evds2.tcmb.gov.tr/service/evds/";
		String series= "TP.DK.USD.A";
		String type= "json";
		
		String endpoint= rootURL +"series="+series+"&startDate"+startDate+"&endDate"+endDate+"&type"+type;
		
		HttpHeaders  httpHeaders = new HttpHeaders();
		httpHeaders.set("key", "XsBxAxzaVo"); //api key
		
		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
		RestTemplate   restTemplate = new RestTemplate();
		
		
		try {
			ResponseEntity<CurrenyRatesResponse> response =	
					restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<CurrenyRatesResponse>() {
				});
			if (response.getStatusCode().is2xxSuccessful()) {
				 return 	response.getBody();
			}
		
			
		} catch (Exception e) {
			new BaseException(new ErrorMessage(MessageType.CURRENY_RATES_IS_OCCURED,e.getMessage() ));

		}
		return null;
			
		}
	
	

}
