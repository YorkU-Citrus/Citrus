package bean;

public class CartItemBean {
	
	private int id;
	private int userId;
	private int bookId;
	private int amount;
	private int price;
	
	
	public CartItemBean(int userId, int bookId, int amount, int price) {
		super();
		this.userId = userId;
		this.bookId = bookId;
		this.amount = amount;
		this.price = price;
	}
	
	public CartItemBean(int id, int userId, int bookId, int amount, int price) {
		super();
		this.id = id;
		this.userId = userId;
		this.bookId = bookId;
		this.amount = amount;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "CartItemBean [id=" + id + ", userId=" + userId + ", bookId=" + bookId + ", amount=" + amount
				+ ", price=" + price + "]";
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
