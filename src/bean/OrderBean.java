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
@XmlAccessorType(XmlAccessType.NONE)
public class OrderBean {
	/**
	 * Declaration of Globals and Assignment of attributes for XML
	 */
	@XmlElement(name="orderId")
	private int id;
	@XmlElement(name="userId")
	private int userId;
	private Timestamp timestamp;
	@XmlElement(name="status")
	private String status;
	@XmlElement(name="priceInCents")
	private int totalPrice;
	private List<OrderItemBean> list;
	private int taxRate; // in percentage
	
	/**
	 * Constructor
	 */
	public OrderBean() {
		
	}
	/**
	 * Wrapper (Constructor) to store Order Object
	 * @param id
	 * @param userId
	 * @param timestamp
	 * @param status
	 * @param totalPrice
	 */
	public OrderBean(int id, int userId, Timestamp timestamp, String status, int totalPrice) {
		this.id = id;
		this.userId = userId;
		this.timestamp = timestamp;
		this.status = status;
		this.totalPrice = totalPrice;
		this.list = new ArrayList<OrderItemBean>();
	}
	/**
	 * Wrapper (Constructor) to store Order Object
	 * @param userId
	 */
	public OrderBean(int userId) {
		this.userId = userId;
		this.timestamp = new Timestamp(new Date().getTime());
		this.list = new ArrayList<OrderItemBean>();
	}
	/**
	 * Wrapper (Constructor) to store Order Object
	 * @param userId
	 * @param total
	 */

	public OrderBean(int userId, int total) {
		this.userId = userId;
		this.timestamp = new Timestamp(new Date().getTime());
		this.list = new ArrayList<OrderItemBean>();
		this.totalPrice = total;
		this.list = new ArrayList<OrderItemBean>();
	}
	/**
	 * Returns the Time Stamp of Order
	 * @return timestamp
	 */
	@XmlElement(name="timestamp")
	public String getTimeStampString() {
		return timestamp.toString();
	}
	/**
	 * To String Method
	 */
	@Override
	public String toString() {
		return "OrderBean [id=" + id + ", userId=" + userId + ", timestamp=" + timestamp + ", status=" + status
				+ ", totalPrice=" + totalPrice + "]";
	}
	/**
	 * Return the id which correspond to database id for order
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Sets the Id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Returns the User Id
	 * @return userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * Sets the User Id
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * Returns the Time Stamp
	 * @return timestamp
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
	 * Returns the Status of Order
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * Sets the status of order
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * Returns the total Price
	 * @return totalPrice
	 */
	public int getTotalPrice() {
		return totalPrice;
	}
	/**
	 * Sets the total price
	 * @param totalPrice
	 */
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * Sets The Tax rate.
	 * @param taxRate
	 */
	public void setTaxRate(int taxRate) {
		this.taxRate = taxRate;
	}
	/**
	 * Add orders to this List  (Cart) and updates the price of total Cart (orders)
	 * @param item
	 */
	public void appendList(OrderItemBean item) {
		this.list.add(item);
		updateTotalPrice();
	}
	/**
	 * Update the Total Price by adding each order price inside Order List
	 */
	public void updateTotalPrice() {
		int sum = 0;
		for (OrderItemBean order : this.list) {
			sum += order.getBook().getPrice() * order.getAmount();
		}
		this.totalPrice = (int) Math.round(sum * (this.taxRate / 100.0 + 1.0));
	}
	
	/**
	 * Generate Receipts for an Order
	 * @return bill
	 */
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
	/**
	 * Returns the Order List
	 * @return list
	 */
	public List<OrderItemBean> getOderList() {
		return this.list;
	}
	
}
