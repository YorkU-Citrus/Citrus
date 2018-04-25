package bean;

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
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
