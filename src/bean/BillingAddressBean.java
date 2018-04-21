package bean;

import java.sql.Timestamp;

public class BillingAddressBean {
	/*
	 * baid
	 * bauid
	 * batime
	 * bafirst
	 * balast
	 * bacredit
	 * bacvv
	 * bastreet
	 * baprovince
	 * bacountry
	 * bazip
	 */
	private int id;
	private int userId;
	private Timestamp timestamp;
	private String firstName;
	private String lastName;
	private String street;
	private String province;
	private String country;
	private String zip;
	
	public BillingAddressBean() {
		
	}
	
	public BillingAddressBean(int userId, Timestamp timestamp, String firstName, String lastName,  String street, String province, String country, String zip) {
		this.userId = userId;
		this.timestamp = timestamp;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.province = province;
		this.country = country;
		this.zip = zip;
	}
	
	public BillingAddressBean(int userId, String firstName, String lastName,  String street, String province, String country, String zip) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.province = province;
		this.country = country;
		this.zip = zip;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public Timestamp getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
	
	
	
}
