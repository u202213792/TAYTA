package com.tayta.Services;

import com.tayta.Entities.User;
import com.tayta.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        user.setId(id);
        // Conservar la contraseña existente si no viene en la petición
        // (el password ya no se expone en las respuestas, así que el frontend no la reenvía).
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            userRepository.findById(id).ifPresent(existing -> user.setPassword(existing.getPassword()));
        }
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
