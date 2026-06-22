package com.tayta.Repositories;

import com.tayta.Entities.GuardianElderly;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuardianElderlyRepository extends JpaRepository<GuardianElderly, Long> {

    // Vínculos del apoderado (ownership de adultos mayores)
    List<GuardianElderly> findByGuardian_User_Username(String username);
}
