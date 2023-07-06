package com.lexi.vlogapp.repository;

import com.lexi.vlogapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
