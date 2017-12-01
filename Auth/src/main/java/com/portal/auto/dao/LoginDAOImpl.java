package com.portal.auto.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.auto.bean.UserBean;

@Transactional
@Repository
public class LoginDAOImpl implements LoginDAO {

	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	public UserBean findByUserName(String userName) {
		UserBean bean = null;
		String hql = "FROM UserBean as user where user.userName = :userName";
		try {
			Object obj = entityManager.createQuery(hql).setParameter("userName", userName).getSingleResult();
			if(obj instanceof UserBean){
				bean = (UserBean)obj;
			}
		} catch(Exception e){
			System.out.println("User Not Found");
		}
		return bean;
	}

	@Override
	public boolean updateToken(UserBean userBean) {
		
		boolean flag = true;
		try {
			entityManager.flush();
			
		} catch(Exception e){
			flag = false;
		}
		
		return flag;
		
	}
}
