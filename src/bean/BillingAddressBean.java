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
	private String credit;
	private String cvv;
	private String street;
	private String province;
	private String country;
	private String zip;
	
	
	
	public BillingAddressBean(int userId, Timestamp timestamp, String firstName, String lastName, String credit,
			String cvv, String street, String province, String country, String zip) {
		super();
		this.userId = userId;
		this.timestamp = timestamp;
		this.firstName = firstName;
		this.lastName = lastName;
		this.credit = credit;
		this.cvv = cvv;
		this.street = street;
		this.province = province;
		this.country = country;
		this.zip = zip;
	}


	@Override
	public String toString() {
		return "BillingAddressBean [id=" + id + ", userId=" + userId + ", timestamp=" + timestamp + ", firstName="
				+ firstName + ", lastName=" + lastName + ", credit=" + credit + ", cvv=" + cvv + ", street=" + street
				+ ", province=" + province + ", country=" + country + ", zip=" + zip + "]";
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


	public String getCredit() {
		return credit;
	}


	public void setCredit(String credit) {
		this.credit = credit;
	}


	public String getCvv() {
		return cvv;
	}


	public void setCvv(String cvv) {
		this.cvv = cvv;
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
