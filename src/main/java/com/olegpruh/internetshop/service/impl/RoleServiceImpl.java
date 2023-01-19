package com.olegpruh.internetshop.service.impl;

import com.olegpruh.internetshop.repository.RoleRepository;
import com.olegpruh.internetshop.security.RoleName;
import com.olegpruh.internetshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.olegpruh.internetshop.model.Role;


@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleByName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
