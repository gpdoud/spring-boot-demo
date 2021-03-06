package com.dsi.demo.order;

import javax.persistence.*;

import com.dsi.demo.customer.Customer;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=30, nullable=false)
	private String description;
	@Column(columnDefinition="decimal(10,2) NOT NULL DEFAULT 0.0")
	private double total;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="customerId")
	private Customer customer;
	
	public Order() {}
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public double getTotal() { return total; }
	public void setTotal(double total) { this.total = total; }
	
	public Customer getCustomer() { return customer; }
	public void setCustomer(Customer customer) { this.customer = customer; }
}
