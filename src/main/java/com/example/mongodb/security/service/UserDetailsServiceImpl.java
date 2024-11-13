package com.example.mongodb.security.service;

import com.example.mongodb.core.exception.CustomException;
import com.example.mongodb.user.model.User;
import com.example.mongodb.user.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;

    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new CustomException("اطلاعات حساب کاربری صحبح نمی باشد."));

        return UserDetailsImpl.build(user);
    }
}
