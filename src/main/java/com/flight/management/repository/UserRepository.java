package com.flight.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.management.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    Optional<User> findById(Long userId);

    User findByUsername(String username);

    boolean existsByUsername(String username);
}
