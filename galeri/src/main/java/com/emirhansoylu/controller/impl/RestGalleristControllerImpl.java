package com.emirhansoylu.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emirhansoylu.controller.IRestGalleristController;
import com.emirhansoylu.controller.RestBaseController;
import com.emirhansoylu.controller.RootEntity;
import com.emirhansoylu.dto.DtoGallerist;
import com.emirhansoylu.dto.DtoGalleristIU;
import com.emirhansoylu.service.IGalleristService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristControllerImpl extends RestBaseController implements IRestGalleristController {
	
	@Autowired
	private IGalleristService galleristService;
	

	@PostMapping("/save")
	@Override
	public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
		return ok(galleristService.saveGallerist(dtoGalleristIU));
	}

}
