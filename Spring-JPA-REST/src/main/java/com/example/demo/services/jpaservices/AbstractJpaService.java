package com.example.demo.services.jpaservices;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public abstract class AbstractJpaService {
	@PersistenceUnit
	public EntityManagerFactory emf;
}
