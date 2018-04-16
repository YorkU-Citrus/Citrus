package bean;

import java.sql.Timestamp;

public class UserBean {
	private int uid; // AUTO
	private String uname;
	private String hashedPassword;
	private String salt;
	private Timestamp ulastactive; // Time Stamp

	// create an user from sign up
	public UserBean(String name, String password) {
		// TODO: create user from sign up
	}

	// get an user in database
	public UserBean(Integer id, String name, String password, String salt, Timestamp timeStamp) {
		// TODO: load user from database
	}

	@Override
	public String toString() {
		return "UserBean [uid=" + uid + ", uname=" + uname + ", hashedPassword=" + hashedPassword + ", salt=" + salt
				+ ", ulastactive=" + ulastactive + "]";
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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
