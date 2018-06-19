package com.szk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.szk.mapper.RoleMapper;
import com.szk.service.RoleService;
import com.szk.vo.Role;

/**
 * 这里需要注意的点：
 *     1、@Transaction是使用的SpringAOP技术，所以对于    静态和非public  方法是不能添加这个注释的，因为无用
 *     2、自调用问题：
 *         一个类的一个方法去调用自身的另外一个方法，这样是不存在代理对象的调用，不会产生AOP去设置@Transaction
 *       解决方法：
 *       	一、将这两个方法放在不同的业务类中
 *          二、调用的时候，先从容器中获取自身的业务对象（自身的代理对象），用这个对象去调用方法，就像当前这个类的方法，就是有了侵入性，可以参考一
 * @author admin
 *
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	@Qualifier("roleMapper")
	private RoleMapper roleMapper;
	
	/**
	 * 注入自身类的代理对象是为了调用自身的方法而不失效@Transaction的使用
	 */
	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	
	@Override
	@Transactional(propagation=Propagation.NESTED,
			isolation=Isolation.READ_COMMITTED)
	public int insertRole(Role role) {
		
		return roleMapper.insertRole(role);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,           //传播行为
			isolation=Isolation.READ_COMMITTED)				   //事务级别
	public int insertRoleList(List<Role> roleList) {
		int count = 0;
		for(Role role:roleList) {
			roleService.insertRole(role);
			count++;
		}
		return count;
	}

}
