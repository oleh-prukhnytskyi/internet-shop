package com.olegpruh.internetshop.service;

import com.olegpruh.internetshop.model.Role;
import com.olegpruh.internetshop.security.RoleName;

public interface RoleService {
    Role save(Role role);

    Role getRoleByName(RoleName roleName);
}
