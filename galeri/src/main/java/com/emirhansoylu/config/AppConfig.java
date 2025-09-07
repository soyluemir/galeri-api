package com.emirhansoylu.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.emirhansoylu.exception.BaseException;
import com.emirhansoylu.exception.ErrorMessage;
import com.emirhansoylu.exception.MessageType;
import com.emirhansoylu.model.User;
import com.emirhansoylu.repository.UserRepository;

@Configuration
public class AppConfig {
	
	@Autowired
	private UserRepository userRepository;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() { //almış olduğu usernameye göre veritabanına gidiyor bulursa userdetails tipinde dönüyor
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User>	optional =	userRepository.findByUsername(username);
		if (optional.isEmpty()) { //user yoksa
			throw new BaseException(new ErrorMessage(MessageType.USERNAME_NOT_FOUND, username));			
		}
		return optional.get();				
			}
		};		
	}
	//en önemli sınıfımız daoauthenticationprovider benden username şifreyi veritabanını alıyo ekrandan gelenle veritabanından gelenleri check ediyor
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService()); // yukarıdaki 
		provider.setPasswordEncoder(passwordEncoder()); //aşağıdaki 
		return provider; //içerisini doldurduk şifre kontrolleri vs yapılan yer
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {  //şifreleri kriptolayabilmek için nesne türetiyorum
		return new BCryptPasswordEncoder();
		
	}

}
