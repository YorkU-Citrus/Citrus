package bean;

public class BookBean {
	// bookId, title, price, bcat, isbn, dscpt, amount, image
	private int bookId;
	private String title;
	private int price;
	private int category;
	private String categoryTitle;
	private String isbn;
	private String description;
	private int amount;
	private String image;
	private double rating;
	private int numberOfComment;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
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

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getNumberOfComment() {
		return numberOfComment;
	}

	public void setNumberOfComment(int numberOfComment) {
		this.numberOfComment = numberOfComment;
	}

	@Override
	public String toString() {
		return String.format(
				"BookBean [bookId=%s, title=%s, price=%s, category=%s, categoryTitle=%s, isbn=%s, description=%s, amount=%s, image=%s, rating=%s, numberOfComment=%s]",
				bookId, title, price, category, categoryTitle, isbn, description, amount, image, rating,
				numberOfComment);
	}

	public BookBean(int bookId, String title, int price, int category, String categoryTitle, String isbn,
			String description, int amount, String image, double rating, int numberOfComment) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.category = category;
		this.categoryTitle = categoryTitle;
		this.isbn = isbn;
		this.description = description;
		this.amount = amount;
		this.image = image;
		this.rating = rating;
		this.numberOfComment = numberOfComment;
	}

	public BookBean(String title, int price, int category, String isbn, String description, int amount, String image) {
		super();
		this.title = title;
		this.price = price;
		this.category = category;
		this.isbn = isbn;
		this.description = description;
		this.amount = amount;
		this.image = image;
	}

}