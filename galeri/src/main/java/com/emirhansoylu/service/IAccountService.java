package com.emirhansoylu.service;

import com.emirhansoylu.dto.DtoAccount;
import com.emirhansoylu.dto.DtoAccountIU;

public interface IAccountService {
	
	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

}
