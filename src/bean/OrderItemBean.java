package bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="orderItemType")
@XmlAccessorType(XmlAccessType.NONE)
public class OrderItemBean {
	//oiid, oioid, oibid, oiamount
	/**
	 * Declaration of Globals and Assignment of Attributes for XML
	 */
	private int id;
	private int orderId;
	private int bookId;
	@XmlElement(name="book")
	private BookBean book;
	@XmlElement(name="amount")
	private int amount;
	/**
	 * Constructor
	 */
	public OrderItemBean() {
		
	}
	/**
	 * Wrapper to store OrderItem Object  (like Book)
	 * @param orderId
	 * @param bookId
	 * @param amount
	 */
	public OrderItemBean(int orderId, int bookId, int amount ) {
		this.orderId = orderId;
		this.bookId = bookId;
		this.amount = amount;
		
	}
	/**
	 * Wrapper to store OrderItem Object  (like Book)
	 * @param book
	 * @param amount
	 */
	public OrderItemBean(BookBean book, int amount ) {
		this.bookId = book.getBookId();
		this.book = book;
		this.amount = amount;		
	}
	
	/**
	 * Wrapper to store OrderItem Object  (like Book)
	 * @param bookId
	 * @param amount
	 */
	public OrderItemBean(int bookId, int amount ) {
		this.bookId = bookId;
		this.amount = amount;		
	}
	
	/**
	 * To String Method
	 */

	@Override
	public String toString() {
		return "OrderItemBean [orderId=" + orderId + ", bookId=" + bookId + ", amount=" + amount + "]";
	}
		
	/**
	 * Returns the ID
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
	 * Returns the Order Id
	 * @return orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * Sets the Order Id
	 * @param orderId
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * Returns the Book Id
	 * @return bookId
	 */
	public int getBookId() {
		return bookId;
	}
	/**
	 * Sets the Book Id
	 * @param bookId
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	/**
	 * Returns the Amount
	 * @return amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * Sets the Amount of Order
	 * @param amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * Returns Book
	 * @return book
	 */
	public BookBean getBook() {
		return this.book;
	}
}
