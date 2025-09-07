package com.emirhansoylu.controller;

import com.emirhansoylu.dto.DtoAddress;
import com.emirhansoylu.dto.DtoAddressIU;

public interface IRestAddressController {
	
	public RootEntity<DtoAddress> savedAddress(DtoAddressIU dtoAddressIU);

}
