package com.emirhansoylu.controller;

import com.emirhansoylu.dto.DtoSaledCar;
import com.emirhansoylu.dto.DtoSaledCarIU;

public interface IRestSaledCarController {
	
	public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);

}
