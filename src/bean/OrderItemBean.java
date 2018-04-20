package bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="orderItemType")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItemBean {
	//oiid, oioid, oibid, oiamount

	private int id;
	private int orderId;
	private int bookId;
	@XmlElement(name="amount")
	private int amount;
	//private int price;
	@XmlElement(name="book")
	private BookBean book;

	
	public OrderItemBean(int orderId, int bookId, int amount ) {
		this.orderId = orderId;
		this.bookId = bookId;
		this.amount = amount;
		
	}

	public OrderItemBean(BookBean book, int amount ) {
		this.bookId = book.getBookId();
		this.book = book;
		this.amount = amount;		
	}
	

	public OrderItemBean(int bookId, int amount ) {
		this.bookId = bookId;
		this.amount = amount;		
	}
	
	

	@Override
	public String toString() {
		return "OrderItemBean [orderId=" + orderId + ", bookId=" + bookId + ", amount=" + amount + "]";
	}
		

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public BookBean getBook() {
		return this.book;
	}
	

}
