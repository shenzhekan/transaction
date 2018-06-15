package com.szk.mapper;

import org.springframework.stereotype.Repository;

import com.szk.vo.Role;

@Repository("roleMapper")
public interface RoleMapper {

	
	public int insertRole(Role role);
}
