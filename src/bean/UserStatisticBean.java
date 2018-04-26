package bean;

public class UserStatisticBean {
	/**
	 * Globals Declarations
	 */
	private String name;
	private String postalCode;
	private Integer OrderAmount;
	private Integer totalConsumption;
	/**
	 * Wrapper to Store User Statistics
	 * @param name
	 * @param postalCode
	 * @param orderAmount
	 * @param totalConsumption
	 */
	public UserStatisticBean(String name, String postalCode, Integer orderAmount, Integer totalConsumption) {
		super();
		this.name = name;
		this.postalCode = postalCode;
		OrderAmount = orderAmount;
		this.totalConsumption = totalConsumption;
	}
	@Override
	/**
	 * To String Method
	 */
	public String toString() {
		return "UserStatisticBean [name=" + name + ", postalCode=" + postalCode + ", OrderAmount=" + OrderAmount
				+ ", totalConsumption=" + totalConsumption + "]";
	}
	/**
	 * Returns the Name of User
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Returns the Annonimized Name of User
	 * @return 
	 */
	public String getUserAnnomizedName() {
		return this.getName().charAt(0) + "****" + this.getName().charAt(this.getName().length() - 1);
	}
	/**
	 * Sets the name of User
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Returns the postal code (ZIP)
	 * @return postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * Sets the postal code (ZIP) of User
	 * @param postalCode
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * Returns the Total Order Placed by User
	 * @return orderAmount
	 */
	public Integer getOrderAmount() {
		return OrderAmount;
	}
	/**
	 * Sets the Order Amount
	 * @param orderAmount
	 */
	public void setOrderAmount(Integer orderAmount) {
		OrderAmount = orderAmount;
	}
	/**
	 * Returns the Total Consumption By User
	 * @return totalConsumption
	 */
	public Integer getTotalConsumption() {
		return totalConsumption;
	}
	/**
	 * Sets the Total Consumption by User
	 * @param totalConsumption
	 */
	public void setTotalConsumption(Integer totalConsumption) {
		this.totalConsumption = totalConsumption;
	}
}
