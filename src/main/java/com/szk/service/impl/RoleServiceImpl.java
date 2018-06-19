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
 * ������Ҫע��ĵ㣺
 *     1��@Transaction��ʹ�õ�SpringAOP���������Զ���    ��̬�ͷ�public  �����ǲ���������ע�͵ģ���Ϊ����
 *     2���Ե������⣺
 *         һ�����һ������ȥ�������������һ�������������ǲ����ڴ������ĵ��ã��������AOPȥ����@Transaction
 *       ���������
 *       	һ�����������������ڲ�ͬ��ҵ������
 *          �������õ�ʱ���ȴ������л�ȡ�����ҵ���������Ĵ�����󣩣����������ȥ���÷���������ǰ�����ķ������������������ԣ����Բο�һ
 * @author admin
 *
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	@Qualifier("roleMapper")
	private RoleMapper roleMapper;
	
	/**
	 * ע��������Ĵ��������Ϊ�˵�������ķ�������ʧЧ@Transaction��ʹ��
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
	@Transactional(propagation=Propagation.REQUIRED,           //������Ϊ
			isolation=Isolation.READ_COMMITTED)				   //���񼶱�
	public int insertRoleList(List<Role> roleList) {
		int count = 0;
		for(Role role:roleList) {
			roleService.insertRole(role);
			count++;
		}
		return count;
	}

}
