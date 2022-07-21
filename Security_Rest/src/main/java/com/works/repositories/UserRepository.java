package com.works.repositories;

import com.works.entities.UserX;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserX, Long> {

    Optional<UserX> findByEmailEqualsAllIgnoreCase(String email);

}

