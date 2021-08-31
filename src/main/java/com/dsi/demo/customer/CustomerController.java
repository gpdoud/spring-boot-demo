package com.dsi.demo.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository custRepo;
	
	@GetMapping
	public ResponseEntity<List<Customer>> GetAll() {
		System.out.println("GetAll()");
		var customers = custRepo.findAll();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<Customer> GetById(@PathVariable Integer id) {
		System.out.println("GetById()");
		var customer = custRepo.findById(id);
		if(customer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
	}

	@GetMapping("name/{name}")
	public ResponseEntity<List<Customer>> SearchName(@PathVariable String name) {
		System.out.println("SearchName()");
		var customer = custRepo.findByName(name);
		return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Customer> Insert(@RequestBody Customer cust) {
		if(cust == null) {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		custRepo.save(cust);
		return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity Update(@PathVariable Integer id, @RequestBody Customer cust) {
		if(cust == null) {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if(cust.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		custRepo.save(cust);
		return new ResponseEntity<>(cust, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Customer> Delete(@PathVariable Integer id) {
		var customer = custRepo.findById(id);
		if(customer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		custRepo.delete(customer.get());
		return new ResponseEntity<Customer>(customer.get(), HttpStatus.ACCEPTED);
	}
}
