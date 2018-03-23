package bean;

import java.sql.Timestamp;
import java.util.Date;

public class CommentBean {
	private int cmtid;
	private UserBean user;
	private Integer bookid;
	private Timestamp timestamp;
	private int rating;
	private String content;
	private String status;
	
	 
	
	/*
	private int cmtid;
	private int userid;
	private int bookid;
	private int rating;
	private Timestamp timestamp;
	private String status;
	private String content;
	*/
	
	public CommentBean(int cmtid, UserBean ub, Integer bid, Timestamp ts, int rate, String content, String status) {
		this.cmtid = cmtid;
		this.user = ub;
		this.bookid = bid;
		this.rating = rate;
		this.timestamp = ts;
		this.content = content;
		this.status = status;
	}

	
	public int getCmtid() {
		return cmtid;
	}


	public void setCmtid(int cmtid) {
		this.cmtid = cmtid;
	}


	public Integer getBookid() {
		return bookid;
	}


	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	
	
	public UserBean getUser() {
		return user;
	}


	public void setUser(UserBean user) {
		this.user = user;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public Timestamp getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

	
	
	
	
	
	

	

}
