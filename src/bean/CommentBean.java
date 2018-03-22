package bean;

import java.sql.Timestamp;
import java.util.Date;

public class CommentBean {
	private UserBean user;
	private int rating;
	private Timestamp timestamp;
	//private int status;
	private String status;
	private String content;
	 
	
	/*
	private int cmtid;
	private int userid;
	private int bookid;
	private int rating;
	private Timestamp timestamp;
	private String status;
	private String content;
	*/
	
	public CommentBean(UserBean ub,  Timestamp ts, int rate, String content, String status) {
		
		this.user = ub;
		this.rating = rate;
		this.timestamp = ts;
		this.content = content;
		this.status = status;
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
