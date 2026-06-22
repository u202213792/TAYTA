package com.tayta.Controllers;

import com.tayta.Entities.Role;
import com.tayta.Entities.User;
import com.tayta.Repositories.RoleRepository;
import com.tayta.Repositories.UserRepository;
import com.tayta.Security.Dtos.LoginRequest;
import com.tayta.Security.Dtos.LoginResponse;
import com.tayta.Security.Dtos.RegisterRequest;
import com.tayta.Security.Jwt.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication);

        User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        String roleName = user.getRole() != null ? user.getRole().getRoleName() : "USER";

        return ResponseEntity.ok(new LoginResponse(jwt, user.getId(), user.getUsername(), roleName));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username already exists");
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email already in use");
        }

        // El registro público siempre crea un GUARDIAN (apoderado).
        // Los roles NURSE/ADMIN los asigna un ADMIN desde su panel.
        Role role = roleRepository.findByRoleName("GUARDIAN")
                .orElseThrow(() -> new RuntimeException("Role GUARDIAN not found"));

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(role);
        user.setCreatedAt(LocalDate.now());

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
