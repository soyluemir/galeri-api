package com.emirhansoylu.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emirhansoylu.controller.IRestAddressController;
import com.emirhansoylu.controller.RestBaseController;
import com.emirhansoylu.controller.RootEntity;
import com.emirhansoylu.dto.DtoAddress;
import com.emirhansoylu.dto.DtoAddressIU;
import com.emirhansoylu.service.IAddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/address")
public class RestAdressControllerImpl extends RestBaseController implements IRestAddressController {
	
	@Autowired
	private IAddressService addressService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoAddress> savedAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
		
		return ok(addressService.saveAddress(dtoAddressIU));
	}

}
