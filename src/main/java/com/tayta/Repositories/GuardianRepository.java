package com.tayta.Repositories;

import com.tayta.Entities.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuardianRepository extends JpaRepository<Guardian, Long> {

    Optional<Guardian> findByUser_Username(String username);
}
