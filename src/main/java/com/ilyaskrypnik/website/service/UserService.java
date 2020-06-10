package com.ilyaskrypnik.website.service;

import com.ilyaskrypnik.website.domain.Role;
import com.ilyaskrypnik.website.domain.User;
import com.ilyaskrypnik.website.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }


    public String addNewUser(User user, Map<String, Object> model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User already exists!");
            return "registration";
        }

        String passwordFromFront = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(passwordFromFront));

        user.setIsActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        userRepository.save(user);

        return "redirect:/login";
    }
}
