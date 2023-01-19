package com.olegpruh.internetshop.repository;

import com.olegpruh.internetshop.model.Role;
import com.olegpruh.internetshop.security.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(RoleName roleName);
}
