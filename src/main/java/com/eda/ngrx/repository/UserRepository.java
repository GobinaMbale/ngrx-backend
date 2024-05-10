package com.eda.ngrx.repository;

import com.eda.ngrx.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("select u.username from User u where u.username = :username and u.password = :password")
    String findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    List<User> findAllByUsername(String username);
}
