package com.example.demo.services.mapservices;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Customer;
import com.example.demo.domain.DomainObject;
import com.example.demo.services.CustomerService;

@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService implements CustomerService{
    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer) super.getById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return (Customer) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }


}
