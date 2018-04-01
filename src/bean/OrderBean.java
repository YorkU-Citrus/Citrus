package bean;

import java.sql.*;

public class OrderBean {
	//oid, ouid, otime, ostatus, oprice
	private int id;
	private int userId;
	private Timestamp timestamp;
	private String status;
	private int totalPrice;
	
	public OrderBean(int id, int userId, Timestamp timestamp, String status, int totalPrice) {
		super();
		this.id = id;
		this.userId = userId;
		this.timestamp = timestamp;
		this.status = status;
		this.totalPrice = totalPrice;
	}
	
	public OrderBean(int userId, Timestamp timestamp, String status, int totalPrice) {
		super();
		this.userId = userId;
		this.timestamp = timestamp;
		this.status = status;
		this.totalPrice = totalPrice;
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
	
	
}
