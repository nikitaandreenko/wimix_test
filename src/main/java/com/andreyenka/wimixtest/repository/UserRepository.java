package com.andreyenka.wimixtest.repository;

import com.andreyenka.wimixtest.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
