package com.example.mongodb;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String save(User user) {
        return userRepository.save(user).getId();
    }

    public User findById(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User update(String id, User user) {
        Optional<User> userOpt = userRepository.findById(id);
        userOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return userRepository.save(user);
    }

    public void delete(String id) {
        Optional<User> userOpt = userRepository.findById(id);
        User user = userOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        userRepository.delete(user);
    }

}
