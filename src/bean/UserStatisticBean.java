package bean;

public class UserStatisticBean {
	private String name;
	private String postalCode;
	private Integer OrderAmount;
	private Integer totalConsumption;
	
	
	
	
	
	public UserStatisticBean(String name, String postalCode, Integer orderAmount, Integer totalConsumption) {
		super();
		this.name = name;
		this.postalCode = postalCode;
		OrderAmount = orderAmount;
		this.totalConsumption = totalConsumption;
	}
	
	@Override
	public String toString() {
		return "UserStatisticBean [name=" + name + ", postalCode=" + postalCode + ", OrderAmount=" + OrderAmount
				+ ", totalConsumption=" + totalConsumption + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public Integer getOrderAmount() {
		return OrderAmount;
	}
	public void setOrderAmount(Integer orderAmount) {
		OrderAmount = orderAmount;
	}
	public Integer getTotalConsumption() {
		return totalConsumption;
	}
	public void setTotalConsumption(Integer totalConsumption) {
		this.totalConsumption = totalConsumption;
	}
	
	
}
