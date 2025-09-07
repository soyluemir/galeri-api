package com.emirhansoylu.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {	
	public static final String SECRET_KEY = "IOShUTH1x3VSxfNMHKMPjPVqnKlTGYOt2Fv5ZOMocoM=";	
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
		 .setSubject(userDetails.getUsername())  //token oluşturuyoruz burada 
		 .setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis()+ 1000*60*60*2))
		.signWith(getKey(), SignatureAlgorithm.HS256) //elimdeki secret keyi verip key oluşturmuş oldum. bu keyle tokeni oluştur diyoruz
		.compact();
	}	
	public <T> T exportToken(String token, Function<Claims, T> claimsFunc) {
		 Claims claims=	getClaims(token); //claimsleri alıyorum 
		return claimsFunc.apply(claims); //claimsi çözüp çözdüğü değeri return ediyor
		}	
		public Claims getClaims(String token) { //tokeni çözme yerii //claimsleri almam lazım
		Claims claims =	Jwts.parserBuilder() 
			.setSigningKey(getKey()) //keyi veriyoruz
			.build()
			.parseClaimsJws(token).getBody(); //yukarıdaki tokeni vererek claimslere erişiyoruz.
		return claims; //claims oalrak dön		
		}		
				public String getUsernameByToken(String token) {
		return	exportToken(token, Claims:: getSubject);  //usernameiy alıyoruz				
		}		
		public boolean isTokenValid(String token) {
			Date expireDate = exportToken(token, Claims ::getExpiration ); //tokenin bitiş süresini alıyorum.
			return new Date().before(expireDate); //token bitmiş mi diye true false dönüyor
		}			
	public Key getKey() { //keys dönüyoruz
	byte[] bytes =	Decoders.BASE64.decode(SECRET_KEY); //decoders sınıfndan base64 ile decode yapıyoruz byte tipine dönüş oluyor //KEY OLUŞTURUYORUZ
	return Keys.hmacShaKeyFor(bytes);  //keys sınıfından hmacshakeyfor metoduyla şu byteyi keye çeviriyoruz 
	
	
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
