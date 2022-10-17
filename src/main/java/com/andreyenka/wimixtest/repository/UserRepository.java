package com.andreyenka.wimixtest.repository;

import com.andreyenka.wimixtest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
