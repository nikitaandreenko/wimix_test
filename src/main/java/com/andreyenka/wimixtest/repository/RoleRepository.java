package com.andreyenka.wimixtest.repository;

import com.andreyenka.wimixtest.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
