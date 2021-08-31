package com.dsi.demo.customer;

import java.util.List;

import org.springframework.data.jpa.repository.*;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	Customer findByCode(String code);
	
	@Query("select c from Customer c where c.name = ?1")
	List<Customer> findByName(String name); 
}
