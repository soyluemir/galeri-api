package com.emirhansoylu.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emirhansoylu.controller.IRestCustomerController;
import com.emirhansoylu.controller.RestBaseController;
import com.emirhansoylu.controller.RootEntity;
import com.emirhansoylu.dto.DtoCustomer;
import com.emirhansoylu.dto.DtoCustomerIU;
import com.emirhansoylu.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
		
		return ok(customerService.saveCustomer(dtoCustomerIU));
	}

}
