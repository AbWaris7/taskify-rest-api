package com.khan.code.taskify_rest_api.repository;

import com.khan.code.taskify_rest_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT COUNT(u) from USER u join u.authorities a where a.authority = 'ROLE_ADMIN'")
    long countAdminUsers();
}
