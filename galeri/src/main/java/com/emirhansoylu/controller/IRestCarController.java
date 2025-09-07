package com.emirhansoylu.controller;

import com.emirhansoylu.dto.DtoCar;
import com.emirhansoylu.dto.DtoCarIU;

public interface IRestCarController {
	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);

}
