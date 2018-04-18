package bean;

import java.sql.Timestamp;

public class CommentBean {
	private int commentId;
	private int userId;
	private String userName;
	private Integer bookId;
	private Timestamp timetamp;
	private int rating;
	private String content;
	private String status;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Timestamp getTimetamp() {
		return timetamp;
	}

	public void setTimetamp(Timestamp timetamp) {
		this.timetamp = timetamp;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format(
				"CommentBean [commentId=%s, userId=%s, userName=%s, bookId=%s, timetamp=%s, rating=%s, content=%s, status=%s]",
				commentId, userId, userName, bookId, timetamp, rating, content, status);
	}

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

	public CommentBean(int userId, Integer bookId, int rating, String content, String status) {
		super();
		this.userId = userId;
		this.bookId = bookId;
		this.rating = rating;
		this.content = content;
		this.status = status;
	}

}
