package com.scavettapps.wedding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scavettapps.wedding.domain.WeddingUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<WeddingUser, Integer> {

    Optional<WeddingUser> findByUsername(String username);
}
