package com.emirhansoylu.service;

import com.emirhansoylu.dto.DtoCustomer;
import com.emirhansoylu.dto.DtoCustomerIU;

public interface ICustomerService {
	
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);

}
