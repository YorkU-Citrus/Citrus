package bean;

import java.sql.Timestamp;
import java.util.Date;

public class UserBean {
	private int uid;  //AUTO
	private String uname;
	private String hashedPassword;
	private Timestamp ulastactive; //timestamp
	
	
	
	public UserBean(int id, String name, String Password, Timestamp timeStamp) {
		this.uid= id;
		this.uname = name;
		this.hashedPassword = Password.hashCode() + "";
		this.ulastactive = timeStamp;
	}
	
	public UserBean(String name, String Password, Timestamp timeStamp) {
		this.uname = name;
		this.hashedPassword = Password.hashCode() + "";
		this.ulastactive = timeStamp;
	}
	
	@Override
	public String toString() {
		return String.format(
				"UserBean [userId=%s, userName=%s, userHashedPassword=%s lastActive=%s]",
				uid, uname, hashedPassword, ulastactive.toString());
	}
	
	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
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
