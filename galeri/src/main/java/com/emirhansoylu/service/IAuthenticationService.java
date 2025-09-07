package com.emirhansoylu.service;

import com.emirhansoylu.dto.AuthRequest;
import com.emirhansoylu.dto.AuthResponse;
import com.emirhansoylu.dto.DtoUser;
import com.emirhansoylu.dto.RefreshTokenRequest;


public interface IAuthenticationService {
	public DtoUser register(AuthRequest input); // request alacaksın girdi yani
	
	public AuthResponse authenticate(AuthRequest input);
	
	public AuthResponse refreshToken(RefreshTokenRequest input);
	
   

}
