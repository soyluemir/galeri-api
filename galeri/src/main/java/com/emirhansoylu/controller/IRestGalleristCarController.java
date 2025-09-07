package com.emirhansoylu.controller;

import com.emirhansoylu.dto.DtoGalleristCar;
import com.emirhansoylu.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
