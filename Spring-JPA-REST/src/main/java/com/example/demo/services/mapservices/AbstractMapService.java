package com.example.demo.services.mapservices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.domain.Customer;
import com.example.demo.domain.DomainObject;

public class AbstractMapService {
	
	private Map<Integer, DomainObject> domainMap;

    private Integer getNextKey(){
        if (domainMap.isEmpty()) 
        	return 1;
        else
        	return Collections.max(domainMap.keySet()) + 1;
    }
	
	public AbstractMapService() {
		domainMap = new HashMap<>();
	}

	public List<DomainObject> listAll() {
		return new ArrayList<>(domainMap.values());
	}

	public DomainObject getById(Integer id) {
		return domainMap.get(id);
	}

	public DomainObject saveOrUpdate(DomainObject domainObject) {
		if (domainObject != null){

            if (domainObject.getId() == null){
                domainObject.setId(getNextKey());
            }
            domainMap.put(domainObject.getId(), domainObject);

            return domainObject;
        } else {
            throw new RuntimeException("Object Can't be null");
        }
	}

	public void delete(Integer id) {
		domainMap.remove(id);
		
	}

}
