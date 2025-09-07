package com.emirhansoylu.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emirhansoylu.controller.IRestSaledCarController;
import com.emirhansoylu.controller.RestBaseController;
import com.emirhansoylu.controller.RootEntity;
import com.emirhansoylu.dto.DtoSaledCar;
import com.emirhansoylu.dto.DtoSaledCarIU;
import com.emirhansoylu.service.ISaledCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api")
public class RestSaledCarControllerImpl extends RestBaseController implements IRestSaledCarController {
	
	@Autowired
	private ISaledCarService saledCarService;

	@Override
	public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSaledCarIU dtoSaledCarIU) {
		return ok(saledCarService.buyCar(dtoSaledCarIU));
	}
	

}
