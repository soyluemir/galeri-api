package com.emirhansoylu.service;

import com.emirhansoylu.dto.DtoCar;
import com.emirhansoylu.dto.DtoCarIU;

public interface ICarService {
	
	public DtoCar saveCar(DtoCarIU dtoCarIU);

}
