package com.emirhansoylu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emirhansoylu.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username); // veritabanına gidecek usernameyi şu olan useri bana getir apacak bu metot

}
