package com.ilyaskrypnik.website.repo;

import com.ilyaskrypnik.website.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
