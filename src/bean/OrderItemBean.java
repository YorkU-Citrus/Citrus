package bean;

public class OrderItemBean {
	//oiid, oioid, oibid, oiamount
	//private int id;
	private int orderId;
	private int bookId;
	private int amount;
	private int price;

	
	public OrderItemBean(int orderId, int bookId, int amount) {
		super();
		this.orderId = orderId;
		this.bookId = bookId;
		this.amount = amount;
	}

	public OrderItemBean(int orderId, int bookId, int amount, int price) {
		super();
		this.orderId = orderId;
		this.bookId = bookId;
		this.amount = amount;
		this.price = price;
	}
	
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

}
