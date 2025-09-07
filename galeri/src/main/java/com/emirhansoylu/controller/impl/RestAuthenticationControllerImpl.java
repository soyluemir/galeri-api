package com.emirhansoylu.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emirhansoylu.controller.IRestAuthenticationController;
import com.emirhansoylu.controller.RestBaseController;
import com.emirhansoylu.controller.RootEntity;
import com.emirhansoylu.dto.AuthRequest;
import com.emirhansoylu.dto.AuthResponse;
import com.emirhansoylu.dto.DtoUser;
import com.emirhansoylu.dto.RefreshTokenRequest;
import com.emirhansoylu.service.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
public class RestAuthenticationControllerImpl extends RestBaseController  implements IRestAuthenticationController{

	@Autowired
	private IAuthenticationService authenticationService;
	
	@PostMapping("/register") //security configte izin verdiğimiz registerle aynı isim olmalı
	@Override
	public RootEntity<DtoUser> register( @Valid @RequestBody AuthRequest input) {
		return ok(authenticationService.register(input)); //bana dto user dönüyo bunu ok metoduna veriyorum oradaki T dtouser oluyor
	}                                                     // o da rootentitydeki ok metoduna giriyor ve böylece dtoUSERLİ ROOTentity dönüyoruz
    
	@PostMapping("/authenticate")
	@Override
	public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest input) {
		return  ok(authenticationService.authenticate(input));
	}

	@PostMapping("/refreshToken")
	@Override
	public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest input) {
		return ok(authenticationService.refreshToken(input));
	}

	
}
