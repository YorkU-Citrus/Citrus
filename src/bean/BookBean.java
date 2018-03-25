package bean;

public class BookBean {
	//bid, title, price, bcat, isbn, dscpt, amount, image
	private int bid;
	private String title;
	private int price;
	private int category;
	private String isbn; 
	private String description;
	private int amount;
	private String image;
	
	public BookBean(int bid, String title, int price, int bcat, String isbn, String dscpt, int amount, String image){
		this.bid = bid;
		this.title = title;
		this.price = price;
		this.category = bcat;
		this.isbn = isbn;
		this.description = dscpt;
		this.amount = amount;
		this.image = image;
	}
	

	public BookBean(String title, int price, int bcat, String isbn, String dscpt, int amount, String image){
		this.title = title;
		this.price = price;
		this.category = bcat;
		this.isbn = isbn;
		this.description = dscpt;
		this.amount = amount;
		this.image = image;
	}

	@Override
	public String toString() {
		return "BookBean [bid=" + bid + ", title=" + title + ", price=" + price + ", category=" + category + ", isbn="
				+ isbn + ", description=" + description + ", amount=" + amount + ", image=" + image + "] \n";
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
