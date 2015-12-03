package com.happytrip.dao;

import com.happytrip.model.Role;

public interface RoleDao {
	Role getRoleForUsername(String username);
}
