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
	/**
	 * Wrapper to Store Shipping Address to Ship.
	 * @param userId
	 * @param timestamp
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param province
	 * @param country
	 * @param zip
	 */
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
	/**
	 * Wrapper to Store Shipping Address to Ship. (Without Timestamp)
	 * @param userId
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param province
	 * @param country
	 * @param zip
	 */
	public BillingAddressBean(int userId, String firstName, String lastName,  String street, String province, String country, String zip) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.province = province;
		this.country = country;
		this.zip = zip;
	}
	/**
	 * Returns the id of that entry in database. 
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Sets the Id of that entry in database.
	 * @param id
	 */

	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Returns the User ID
	 * @return userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * Sets the User ID
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * Returns the Time Stamp
	 * @return timeStamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}
	/**
	 * Sets the Time Stamp
	 * @param timestamp
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * Returns the First Name
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Sets the First Name
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Returns the Last Name
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Sets the Last Name
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Returns the Street Address
	 * @return street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the Street Address
	 * @param street
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * Returns the Province
	 * @return province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * Sets the Province
	 * @param province
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * Returns the Country
	 * @return country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * Sets the Country
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * Returns the postal code (ZIP)
	 * @return zip
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * Sets the postal code (ZIP)
	 * @param zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
}
