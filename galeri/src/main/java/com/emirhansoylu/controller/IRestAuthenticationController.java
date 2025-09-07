package com.emirhansoylu.controller;

import com.emirhansoylu.dto.AuthRequest;
import com.emirhansoylu.dto.AuthResponse;
import com.emirhansoylu.dto.DtoUser;
import com.emirhansoylu.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {
	
	public RootEntity<DtoUser> register(AuthRequest input);
	

	public RootEntity<AuthResponse> authenticate(AuthRequest input);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
