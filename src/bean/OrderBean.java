package bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="orderType")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderBean {
	@XmlElement(name="orderId")
	private int id;
	@XmlElement(name="userId")
	private int userId;
	private Timestamp timestamp;
	@XmlElement(name="status")
	private String status;
	@XmlElement(name="priceInCents")
	private int totalPrice;
	@XmlElement(name="items")
	private List<OrderItemBean> list;
	private int taxRate; // in percentage
	
	public OrderBean(int id, int userId, Timestamp timestamp, String status, int totalPrice) {
		this.id = id;
		this.userId = userId;
		this.timestamp = timestamp;
		this.status = status;
		this.totalPrice = totalPrice;
	}

	public OrderBean(int userId) {
		this.userId = userId;
		this.timestamp = new Timestamp(new Date().getTime());
		this.list = new ArrayList<OrderItemBean>();
	}
	

	public OrderBean(int userId, int total) {
		this.userId = userId;
		this.timestamp = new Timestamp(new Date().getTime());
		this.list = new ArrayList<OrderItemBean>();
		this.totalPrice = total;
	}
	
	@Override
	public String toString() {
		return "OrderBean [id=" + id + ", userId=" + userId + ", timestamp=" + timestamp + ", status=" + status
				+ ", totalPrice=" + totalPrice + "]";
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void setTaxRate(int taxRate) {
		this.taxRate = taxRate;
	}
	
	public void appendList(OrderItemBean item) {
		this.list.add(item);
		updateTotalPrice();
	}
	
	public void updateTotalPrice() {
		int sum = 0;
		for (OrderItemBean order : this.list) {
			sum += order.getBook().getPrice() * order.getAmount();
		}
		this.totalPrice = (int) Math.round(sum * (this.taxRate / 100.0 + 1.0));
	}
	
	
	public String receipt() {
		updateTotalPrice();
		String bill = "";
		int sum = 0;
		for (OrderItemBean order : this.list) {
			bill += order.getBook().getTitle() + "\n" + String.format("%.2f",order.getBook().getPrice()/100.0) + " CAD x " +  order.getAmount() + "\n\n";
			sum += order.getBook().getPrice() * order.getAmount();
		}
		
		bill += "Subtotal: " + String.format("%.2f",sum/100.0) + " CAD\n";
		bill += "HST: " + String.format("%.2f",sum* (this.taxRate / 100.0) /100.0) + " CAD\n";
		bill += "\n";
		bill += "Total: " + String.format("%.2f",this.totalPrice/100.0) + " CAD\n";
		
		return bill;
	}

	public List<OrderItemBean> getOderList() {
		return this.list;
	}
	
}
