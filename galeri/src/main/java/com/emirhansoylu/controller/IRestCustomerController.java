package com.emirhansoylu.controller;

import com.emirhansoylu.dto.DtoCustomer;
import com.emirhansoylu.dto.DtoCustomerIU;

public interface IRestCustomerController {
	
	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);

}
