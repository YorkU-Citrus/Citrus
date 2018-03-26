package bean;

import java.sql.Timestamp;
import java.util.Date;

public class UserBean {
	private int uid;  //AUTO
	private String uname;
	private Timestamp ulastactive; //timestamp
	
	
	
	public UserBean(int id, String name, Timestamp timeStamp) {
		this.uid= id;
		this.uname = name;
		this.ulastactive = timeStamp;
	}
	
	public UserBean(String name, Timestamp timeStamp) {
		this.uname = name;
		this.ulastactive = timeStamp;
	}
	
	@Override
	public String toString() {
		return String.format(
				"UserBean [userId=%s, userName=%s, price=%s, lastActive=%s]",
				uid, uname, ulastactive.toString());
	}
	
	public int getUid() {
		return uid;
	}



	public void setUid(int uid) {
		this.uid = uid;
	}



	public String getUname() {
		return uname;
	}



	public void setUname(String uname) {
		this.uname = uname;
	}

	public Timestamp getUlastactive() {
		return ulastactive;
	}



	public void setUlastactive(Timestamp ulastactive) {
		this.ulastactive = ulastactive;
	}



	

}
