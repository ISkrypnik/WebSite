package com.ilyaskrypnik.website.service;

import com.ilyaskrypnik.website.domain.Role;
import com.ilyaskrypnik.website.domain.User;
import com.ilyaskrypnik.website.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }


    public String addNewUser(User user, Map<String, Object> model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            log.warn("Попытка регистрации пользователя с существующим username = {}.", userFromDb.getUsername());
            model.put("message", "User already exists!");
            return "registration";
        }

        String passwordFromFront = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(passwordFromFront));
        user.setIsActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        User savedUser = userRepository.save(user);
        emailService.sendSuccessRegistrationEmail(savedUser.getId());

        return "redirect:/login";
    }
}
