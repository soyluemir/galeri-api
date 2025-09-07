package com.emirhansoylu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emirhansoylu.model.RefreshToken;



@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	
	Optional<RefreshToken>  findByRefreshToken(String refreshToken); //refreeshtokeni bu olanı bulacak

}
