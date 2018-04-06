package bean;

public class OrderItemBean {
	//oiid, oioid, oibid, oiamount
	private int id;
	private int orderId;
	private int bookId;
	private int amount;
	//private int price;

	
	public OrderItemBean(int orderId, int bookId, int amount ) {
		super();
		this.orderId = orderId;
		this.bookId = bookId;
		this.amount = amount;
		
	}

	public OrderItemBean(int bookId, int amount ) {
		super();
		this.bookId = bookId;
		this.amount = amount;
		
	}
	
	
	

	@Override
	public String toString() {
		return "OrderItemBean [orderId=" + orderId + ", bookId=" + bookId + ", amount=" + amount + "]";
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
