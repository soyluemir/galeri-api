package com.emirhansoylu.controller;

import com.emirhansoylu.dto.DtoAccount;
import com.emirhansoylu.dto.DtoAccountIU;

public interface IRestAccountController {
	
	public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU	);

}
