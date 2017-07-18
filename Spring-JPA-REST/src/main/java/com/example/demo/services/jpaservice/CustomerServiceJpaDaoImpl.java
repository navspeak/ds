package com.example.demo.services.jpaservice;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Customer;
import com.example.demo.services.CustomerService;
@Service
@Profile("jpa")
public class CustomerServiceJpaDaoImpl extends AbstractJpaService implements CustomerService{

	@Override
	public List<Customer> listAll() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Customer", Customer.class).getResultList();
	}

	@Override
	public Customer getById(Integer id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Customer.class, id);
	}

	@Override
	public Customer saveOrUpdate(Customer domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Customer savedCustomer = em.merge(domainObject);
        em.getTransaction().commit();
        return savedCustomer;
	}

	@Override
	public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Customer.class, id));
        em.getTransaction().commit();
	}


}
