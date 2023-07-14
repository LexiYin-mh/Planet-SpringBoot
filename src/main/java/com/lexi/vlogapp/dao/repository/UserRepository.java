package com.lexi.vlogapp.dao.repository;

import com.lexi.vlogapp.entity.Role;
import com.lexi.vlogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Set<User> findByName(String name);

    Optional<User> findByEmail(String email);

}
