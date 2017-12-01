package com.portal.auto.register.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.auto.bean.UserBean;

@Transactional
@Repository
public class RegisterDAOImpl implements RegisterDAO {

	@PersistenceContext	
	private EntityManager entityManager;	
	
	public boolean registerUser(UserBean user) {
		boolean flag = true;
		try {
			entityManager.persist(user);
			
		} catch(Exception e){
			flag = false;
		}
		return flag;
	}

	public boolean checkUser(String user) {
		boolean flag = false;
		String hql = "FROM UserBean as user where user.userName = :userName";
		try {
			Object obj = entityManager.createQuery(hql).setParameter("userName", user).getSingleResult();
			if(null != obj){
				flag = true;
			}
		} catch(Exception e){
			System.out.println("User Not Found");
		}
		return flag;
	}
}
