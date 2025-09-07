package com.emirhansoylu.controller;

import com.emirhansoylu.dto.DtoGallerist;
import com.emirhansoylu.dto.DtoGalleristIU;

public interface IRestGalleristController {
	
	public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);

}
