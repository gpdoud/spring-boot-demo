package com.dsi.demo.customer;

import javax.persistence.*;

@Entity
@Table(name="customers", uniqueConstraints=@UniqueConstraint(name="UIDX_code", columnNames = {"code"} ))
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(length=15, nullable=false)
	private String code;
	
	@Column(length=30, nullable=false)
	private String name;
	
	public Customer() {}
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getCode() { return code; }
	public void setCode(String code) { this.code = code; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
}
