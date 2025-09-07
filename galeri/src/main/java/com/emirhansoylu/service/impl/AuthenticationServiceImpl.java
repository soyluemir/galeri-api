package com.emirhansoylu.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.emirhansoylu.dto.AuthRequest;
import com.emirhansoylu.dto.AuthResponse;
import com.emirhansoylu.dto.DtoUser;
import com.emirhansoylu.dto.RefreshTokenRequest;
import com.emirhansoylu.exception.BaseException;
import com.emirhansoylu.exception.ErrorMessage;
import com.emirhansoylu.exception.MessageType;
import com.emirhansoylu.jwt.JWTService;
import com.emirhansoylu.model.RefreshToken;
import com.emirhansoylu.model.User;
import com.emirhansoylu.repository.RefreshTokenRepository;
import com.emirhansoylu.repository.UserRepository;
import com.emirhansoylu.service.IAuthenticationService;
import com.emirhansoylu.starter.GaleriApplicationStarter;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {


	@Autowired
	private UserRepository userRepository; // useri kaydedebilmek için

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationProvider authenticationProvider; //app configte beanini oluşturmuştuk

	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	private User createUser(AuthRequest input) { // user objesi oluşturmam lazım gelen 2 değerle
		User user = new User();
		user.setCreateTime(new Date());
		user.setUsername(input.getUsername());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		// encode metoduyla inputtan gelen mesajı kriptolanmış biçimde çevir ve
		// passworde setle useri geri dön
		return user;
	}
	private RefreshToken createRefreshToken(User user) { //tokenimi oluşturuyorum
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setCreateTime(new Date());
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setUser(user);
		return refreshToken;
	}

	@Override
	public DtoUser register(AuthRequest input) {
		DtoUser dtoUser = new DtoUser();
		User savedUser = userRepository.save(createUser(input)); // oluşturduğun useri savele
		BeanUtils.copyProperties(savedUser, dtoUser);
		return dtoUser;
	}

	@Override
	public AuthResponse authenticate(AuthRequest input) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					input.getUsername(), input.getPassword());
			authenticationProvider.authenticate(authenticationToken); // kullanıcı adı doğru mu şifrem veritabanında																	// aynı mı kontrolü
			Optional<User> optUser = userRepository.findByUsername(input.getUsername()); //veritabanından useri bul
			String accesToken = jwtService.generateToken(optUser.get()); //bulmuş olduğum userle token oluştur accestoken olarak
	RefreshToken savedRefreshToken =	refreshTokenRepository.save(createRefreshToken(optUser.get())); //tokeni saveledi ve aldım
	return new AuthResponse(accesToken, savedRefreshToken.getRefreshToken());
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
		}		
	}
	
	public boolean isValidRefreshToken(Date expireDate) {
		return new Date().before(expireDate);
	}
	
	@Override
	public AuthResponse refreshToken(RefreshTokenRequest input) {
	Optional<RefreshToken> optRefreshToken =	 refreshTokenRepository.findByRefreshToken(input.getRefreshToken());
	if (optRefreshToken.isEmpty()) { //yoksa
		throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, input.getRefreshToken()));
	}
		if (!isValidRefreshToken(optRefreshToken.get().getExpiredDate())) { //eğertoken expired olduysa
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, input.getRefreshToken()));

		}
		
	User user=	optRefreshToken.get().getUser(); //refresh tokenin bağlı olduğu useri aldım
	String accesToken =	jwtService.generateToken(user); //yeni accestoken oluştu diyorum
RefreshToken refreshToken =	createRefreshToken(user); //yeni refresh token

  RefreshToken savedRefreshToken =  refreshTokenRepository.save(refreshToken); //veritabanına kaydediyorum.
 
  return new AuthResponse(accesToken, savedRefreshToken.getRefreshToken());
	

	
		
	}
}
