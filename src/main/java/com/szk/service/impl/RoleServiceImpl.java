package com.szk.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.szk.service.RoleService;
import com.szk.vo.Role;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,
			isolation=Isolation.READ_COMMITTED)
	public int insertRole(Role role) {
		
		return 0;
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,           //传播行为 
			isolation=Isolation.READ_COMMITTED)				   //事务级别
	public int insertRoleList(List<Role> roleList) {
		int count = 0;
		for(Role role:roleList) {
			insertRole(role);
			count++;
		}
		return count;
	}

}
