package com.dsi.demo.order;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderRepository orderRepo;
	
	@GetMapping
	public ResponseEntity<List<Order>> GetAll() {
		System.out.println("GetAll()");
		var orders = orderRepo.findAll();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<Order> GetById(@PathVariable Integer id) {
		System.out.println("GetById()");
		var order = orderRepo.findById(id);
		if(order.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Order>(order.get(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Order> Insert(@RequestBody Order ord) {
		if(ord == null) {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		orderRepo.save(ord);
		return new ResponseEntity<Order>(ord, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity Update(@PathVariable Integer id, @RequestBody Order ord) {
		if(ord == null) {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if(ord.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		orderRepo.save(ord);
		return new ResponseEntity<>(ord, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Order> Delete(@PathVariable Integer id) {
		var order = orderRepo.findById(id);
		if(order.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		orderRepo.delete(order.get());
		return new ResponseEntity<Order>(order.get(), HttpStatus.ACCEPTED);
	}

}
