package bean;
/**
 * 	An object to Store Shipping Address.
 */
public class AddressBean {
	//said, sauid, satime, safirst, salast, sastreet, saprovince, sacountry, sazip
	//private int id;
	private int userId;
	private String firstName;
	private String lastName;
	private String street;
	private String province;
	private String country;
	private String zip;
	@Override
	public String toString() {
		return "AddressBean [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", street=" + street + ", province=" + province + ", country=" + country + ", zip=" + zip + "]";
	}
	/**
	 * Wrapper to Store Whole Address
	 * @param userId
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param province
	 * @param country
	 * @param zip
	 */
	
	public AddressBean(int userId, String firstName, String lastName, String street, String province,
			String country, String zip) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.province = province;
		this.country = country;
		this.zip = zip;
	}
  
	/**
	 * Returns the User Id
	 * @return userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * Set the User Id
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * Returns the First Name
	 * @return firstname
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
	 * Set the Street Address
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
	 * Set the Province
	 * @param province
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * Returns the country
	 * @return country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * Sets the country
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * Returns the ZIP (Postal Code)
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
