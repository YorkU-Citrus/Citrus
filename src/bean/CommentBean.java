package bean;

import java.sql.Timestamp;
import java.util.Date;

public class CommentBean {
	
	private int cmtid;
	private int userid;
	private int bookid;
	private int rating;
	private Timestamp timestamp;
	private String status;
	private String content;
	
	public CommentBean(int id, int uid, int bookid, Timestamp time, int rate, String content, String status) {
		
		this.cmtid = id;
		this.userid = uid;
		this.bookid = bookid;
		this.rating = rate;
		this.content = content;
		this.status = status;
	}

	public int getCmtid() {
		return cmtid;
	}

	public void setCmtid(int cmtid) {
		this.cmtid = cmtid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	

	

}
