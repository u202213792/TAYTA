package com.tayta.Security.Services;

import com.tayta.Entities.User;
import com.tayta.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/** Utilidades para conocer al usuario autenticado actual y su rol. */
@Service
public class CurrentUserService {

    @Autowired
    private UserRepository userRepository;

    public String username() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? auth.getName() : null;
    }

    public String role() {
        return userRepository.findByUsername(username())
                .map(User::getRole)
                .map(r -> r != null ? r.getRoleName() : "")
                .orElse("");
    }

    public boolean isAdmin() {
        return "ADMIN".equals(role());
    }

    public boolean isGuardian() {
        return "GUARDIAN".equals(role());
    }

    public boolean isNurse() {
        return "NURSE".equals(role());
    }
}
