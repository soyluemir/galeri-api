package com.emirhansoylu.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.emirhansoylu.exception.BaseException;
import com.emirhansoylu.exception.ErrorMessage;
import com.emirhansoylu.exception.MessageType;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{ //her istek geldiğinde buraya düşsün

	@Autowired
	private JWTService jwtService; //ihtiyaçımız var
	
	@Autowired
	private UserDetailsService userDetailsService;  //ihtiyaçımız var
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization"); //authorization içindeki headeri çek
		if(header==null) { //token yoksa aşağı gir ve işlemden çık
			filterChain.doFilter(request, response);
			return;
		}
		String token;
		String username;		
		token = header.substring(7); //eğer token varsa tokeni al
		try {
			username = jwtService.getUsernameByToken(token);  //şu tokeni vererek çözmeye çalış usernameyi alabildiysem
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) { //contexe user setlenmediyse 
				UserDetails userDetails = userDetailsService.loadUserByUsername(username); //veritabanında bu user var mı 
				if(userDetails!=null && jwtService.isTokenValid(token)) { //kullanıcıvarsa ve token geçerliyse 
					UsernamePasswordAuthenticationToken authenticationToken = new 
					UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities()); // securitycontex holdere işaretliyorum				
					authenticationToken.setDetails(userDetails);				
					SecurityContextHolder.getContext().setAuthentication(authenticationToken); //contexte token var adam artık controllere erişebilir
				}
			}
		}
		catch(ExpiredJwtException ex) {
			throw new BaseException(new ErrorMessage(MessageType.TOKEN_IS_EXPIRED, ex.getMessage()));
		}
		catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
		}
		filterChain.doFilter(request, response);
	}

}