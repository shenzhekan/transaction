package com.szk.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.szk.service.RoleService;
import com.szk.vo.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TransactionTest {

	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	
	@Test
	public void test1() {
		List<Role> roleList = new ArrayList<>();
		for(int i = 0; i < 2; i++) {
			Role role = new Role();
			role.setRoleName("szk"+i);
			role.setNote("note"+i);
			roleList.add(role);
		}
		int count = roleService.insertRoleList(roleList);
		System.out.println("成功插入了"+count);
	}
	
}
