package com.emirhansoylu.service;

import com.emirhansoylu.dto.DtoSaledCar;
import com.emirhansoylu.dto.DtoSaledCarIU;

public interface ISaledCarService {
	
	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);

}
