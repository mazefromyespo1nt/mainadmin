package com.service.accesspointv2.service;

import com.service.accesspointv2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.accesspointv2.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User userService) {
        return userRepository.save(userService);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
