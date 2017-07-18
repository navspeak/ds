package com.example.demo.services.jpaservice;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.User;
import com.example.demo.services.UserService;
import com.example.demo.services.security.EncryptionService;

public class UserServiceJpaDaoImpl extends AbstractJpaService implements UserService{

	@Autowired
	private EncryptionService encryptionService;

	@Override
	public List<?> listAll() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery(" from User", User.class).getResultList();
	}

	@Override
	public User getById(Integer id) {
		EntityManager em = emf.createEntityManager();
		return em.find(User.class, id);
	}

	@Override
	public User saveOrUpdate(User domainObject) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		if(domainObject.getPassword() != null){
			domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
		}

		User saveduser = em.merge(domainObject);
		em.getTransaction().commit();

		return saveduser;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.remove(em.find(User.class, id));
		em.getTransaction().commit();

	}

}
