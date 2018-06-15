package com.szk.service;

import java.util.List;

import com.szk.vo.Role;

public interface RoleService {

	/**
	 * 添加单个角色
	 * @param role
	 * @return
	 */
	public int insertRole(Role role);
	
	/**
	 * 添加多个角色
	 * @param roleList
	 * @return
	 */
	public int insertRoleList(List<Role> roleList);
	
}
