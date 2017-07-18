package com.example.demo.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Customer;
import com.example.demo.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping({"","/"})
	List<Customer> listAll(){
		return (List<Customer>) customerService.listAll();
	}
	
	@GetMapping("/{id}")
	Customer getById(@PathVariable int id){
		return customerService.getById(id);
	}
	
	@PostMapping
	Customer saveOrUpdate(@RequestBody Customer customer){
		return customerService.saveOrUpdate(customer);
	}
	
	@DeleteMapping
	void delete(@PathVariable int id){
		 customerService.delete(id);
	}
	
}
