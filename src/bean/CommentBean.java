package bean;

import java.sql.Timestamp;

public class CommentBean {
	/**
	 * Declaration of Globals
	 */
	private int commentId;
	private int userId;
	private String userName;
	private Integer bookId;
	private Timestamp timetamp;
	private int rating;
	private String content;
	private String status;
	/**
	 * Returns the Comment Id
	 * @return commentId
	 */
	public int getCommentId() {
		return commentId;
	}
	/**
	 * Sets the Comment Id
	 * @param commentId
	 */
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	/**
	 * Gets the User Id (Commenter)
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
	 * Gets the User Name.
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * Sets the User Name 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * Returns the Book Id
	 * @return bookId
	 */
	public Integer getBookId() {
		return bookId;
	}
	/**
	 * Sets the Book Id
	 * @param bookId
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	/**
	 * Returns the Time Stamp of Comment
	 * @return timetamp
	 */
	public Timestamp getTimetamp() {
		return timetamp;
	}
	/**
	 * Sets the Time Stamp of Comment
	 * @param timetamp
	 */
	public void setTimetamp(Timestamp timetamp) {
		this.timetamp = timetamp;
	}
	/**
	 * Returns the rating
	 * @return rating
	 */
	public int getRating() {
		return rating;
	}
	/**
	 * Sets the rating
	 * @param rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	/**
	 * Returns the Content
	 * @return content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * Sets the content
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * Returns the Status
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * Sets the status
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * To String method
	 */
	@Override
	public String toString() {
		return String.format(
				"CommentBean [commentId=%s, userId=%s, userName=%s, bookId=%s, timetamp=%s, rating=%s, content=%s, status=%s]",
				commentId, userId, userName, bookId, timetamp, rating, content, status);
	}
	/**
	 * Wrapper to store Comment Object
	 * @param commentId
	 * @param userId
	 * @param userName
	 * @param bookId
	 * @param timetamp
	 * @param rating
	 * @param content
	 * @param status
	 */
	public CommentBean(int commentId, int userId, String userName, Integer bookId, Timestamp timetamp, int rating,
			String content, String status) {
		super();
		this.commentId = commentId;
		this.userId = userId;
		this.userName = userName;
		this.bookId = bookId;
		this.timetamp = timetamp;
		this.rating = rating;
		this.content = content;
		this.status = status;
	}
	/**
	 * Wrapper to store Comment Object
	 * @param userId
	 * @param bookId
	 * @param rating
	 * @param content
	 * @param status
	 */
	public CommentBean(int userId, Integer bookId, int rating, String content, String status) {
		super();
		this.userId = userId;
		this.bookId = bookId;
		this.rating = rating;
		this.content = content;
		this.status = status;
	}

}
