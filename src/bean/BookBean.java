package bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="bookType")
@XmlAccessorType(XmlAccessType.NONE)
public class BookBean {
	// bookId, title, price, bcat, isbn, dscpt, amount, image
	/**
	 * Declaration of Global Variable and Assignment of attribute for XML
	 */
	private int bookId;
	@XmlElement(name="title")
	private String title;
	@XmlElement(name="price")
	private int price;
	private String categoryTitle;
	private int category;
	@XmlElement(name="isbn")
	private String isbn;
	private String description;
	private int amount;
	private String image;
	private double rating;
	private int numberOfComment;
	
	private int orderAmount;
	private int rank;
	
	/**
	 * Gets the Rank of book (Popularity)
	 * @return rank
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * Sets the Rank of Book (Popularity)
	 * @param rank
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	/**
	 * Gets the quantity in which order is requested.
	 * @return orderAmount
	 */
	public int getOrderAmount() {
		return orderAmount;
	}
	/**
	 * Sets the Quantity of books which are requested in an order
	 * @param orderAmount
	 */
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	/**
	 * Returns the Book Unique ID (Also used in database)
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
	 * Return the title of book
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Sets the title of book
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Returns the Price of Book
	 * @return
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * Sets the price of book
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * Return the category to which a book is associated (1 , 2, 3 ....)
	 * @return category
	 */
	public int getCategory() {
		return category;
	}
	/**
	 * Sets the Category of Book
	 * @param category
	 */
	public void setCategory(int category) {
		this.category = category;
	}
	/**
	 * Returns the Category Name to which a book is associated with (Science, Fiction .......)
	 * @return categoryTitle
	 */
	public String getCategoryTitle() {
		return categoryTitle;
	}
	/**
	 * Sets the Category Name to which a book is associated.
	 * @param categoryTitle
	 */
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	/**
	 * Returns the ISBN  number of Book
	 * @return
	 */
	public String getIsbn() {
		return isbn;
	}
	/**
	 * Sets the ISBN number of Book
	 * @param isbn
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	/**
	 * Return a small paragraph about the book (Description)
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Sets the Description for a Book which to be stored
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Return the Stock of Book (example: 661 available)
	 * @return amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * Sets the Quantity in which a book is available
	 * @param amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * Returns the path to an Image
	 * @return image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * Set the path and name of Image (../picture/dataAlgorithm.jpg)
	 * @param image
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * Returns the Rating of book.
	 * @return rating
	 */
	public double getRating() {
		return rating;
	}
	/**
	 * Sets the rating of a Book
	 * @param rating
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}
	/**
	 * Return the Number of Comments on a book
	 * @return numberOfComment
	 */
	public int getNumberOfComment() {
		return numberOfComment;
	}
	/**
	 * Sets the number of comment for a particular book
	 * @param numberOfComment
	 */
	public void setNumberOfComment(int numberOfComment) {
		this.numberOfComment = numberOfComment;
	}
	/**
	 * To string method.
	 */
	@Override
	public String toString() {
		return String.format(
				"BookBean [bookId=%s, title=%s, price=%s, category=%s, categoryTitle=%s, isbn=%s, description=%s, amount=%s, image=%s, rating=%s, numberOfComment=%s, orderAmount=%s]",
				bookId, title, price, category, categoryTitle, isbn, description, amount, image, rating,
				numberOfComment, orderAmount);
	}
	/**
	 * Constructor (Wrapper) to store a Book Object.
	 * @param bookId
	 * @param title
	 * @param price
	 * @param category
	 * @param categoryTitle
	 * @param isbn
	 * @param description
	 * @param amount
	 * @param image
	 * @param rating
	 * @param numberOfComment
	 */
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
	/**
	 * Constructor (Wrapper) to store a Book Object.
	 * @param bookId
	 * @param title
	 * @param price
	 * @param category
	 * @param categoryTitle
	 * @param isbn
	 * @param description
	 * @param amount
	 * @param image
	 * @param rating
	 * @param numberOfComment
	 * @param orderAmount
	 */
	public BookBean(int bookId, String title, int price, int category, String categoryTitle, String isbn,
			String description, int amount, String image, double rating, int numberOfComment, int orderAmount) {
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
		this.orderAmount = orderAmount;
	}
	/**
	 * Constructor (Wrapper) to store a Book Object.
	 * @param bookId
	 * @param title
	 * @param price
	 * @param category
	 * @param categoryTitle
	 * @param isbn
	 * @param description
	 * @param amount
	 * @param image
	 * @param rating
	 * @param numberOfComment
	 * @param orderAmount
	 * @param rank
	 */
	public BookBean(int bookId, String title, int price, int category, String categoryTitle, String isbn,
			String description, int amount, String image, double rating, int numberOfComment, int orderAmount, int rank) {
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
		this.orderAmount = orderAmount;
		this.rank = rank;
	}
	/**
	 * Constructor (Wrapper) to store a Book Object.
	 * @param title
	 * @param price
	 * @param category
	 * @param isbn
	 * @param description
	 * @param amount
	 * @param image
	 */
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
	/**
	 * Constructor
	 */
	public BookBean() {
		super();
	}
	/**
	 * Constructor (Wrapper) to store a Book Object.
	 * @param bookId
	 * @param title
	 * @param price
	 * @param isbn
	 */
	public BookBean(int bookId, String title, int price, String isbn) {
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.isbn = isbn;
	}

}
