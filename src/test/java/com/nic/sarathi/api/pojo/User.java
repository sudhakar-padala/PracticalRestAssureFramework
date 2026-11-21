package com.nic.sarathi.api.pojo;

public class User {

	/*
	 * { id:20, email:String, username:String, password:String, name:{
	 * firstname:String, lastname:String }, address:{ city:String, street:String,
	 * number:Number, zipcode:String, geolocation:{ lat:String, long:String } },
	 * phone:String }
	 */

	private String username;
	private String password;
	private String email;
	private String phoneNo;
	private Name name;
	private Address address;
	

	public User( String email,String username, String password, Name name,Address address, String phoneNo) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNo = phoneNo;
		this.name=name;
		this.address= address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
