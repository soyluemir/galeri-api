package com.emirhansoylu.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emirhansoylu.controller.IRestGalleristCarController;
import com.emirhansoylu.controller.RestBaseController;
import com.emirhansoylu.controller.RootEntity;
import com.emirhansoylu.dto.DtoGalleristCar;
import com.emirhansoylu.dto.DtoGalleristCarIU;
import com.emirhansoylu.service.IGalleristCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarControllerImpl  extends RestBaseController implements IRestGalleristCarController{
	
	@Autowired
	private IGalleristCarService galleristCarService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
		return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
	}

}
