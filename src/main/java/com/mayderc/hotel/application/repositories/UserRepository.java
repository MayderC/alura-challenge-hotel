package com.mayderc.hotel.application.repositories;

import com.mayderc.hotel.application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
