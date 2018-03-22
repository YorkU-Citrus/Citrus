package bean;

import java.sql.Timestamp;
import java.util.Date;

public class UserBean {
	private int uid;  //AUTO
	private String uname;
	private String upassword;
	private Timestamp ulastactive; //timestamp
	
	
	
	public UserBean(int id, String name, String pwd, Timestamp ts) {
		this.uid= id;
		this.uname = name;
		this.upassword = pwd;
		this.ulastactive = ts;
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



	public String getUpassword() {
		return upassword;
	}



	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}



	public Timestamp getUlastactive() {
		return ulastactive;
	}



	public void setUlastactive(Timestamp ulastactive) {
		this.ulastactive = ulastactive;
	}



	

}
