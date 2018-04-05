package bean;

import java.sql.Timestamp;
import java.util.Date;

import org.eclipse.jdt.internal.compiler.ast.WhileStatement;

import security.Encryption;

public class UserBean {
	private int uid;  //AUTO
	private String uname;
	private String hashedPassword;
	private String salt;
	private Timestamp ulastactive; //timestamp
	
	
	//for signing up, signing in
	public UserBean(String name, String password, Timestamp timeStamp) {
		this.uname = name;
		this.salt = Encryption.generateSalt();
		this.hashedPassword = Encryption.getHashedPassword(password, salt);
		this.ulastactive = timeStamp;
	}
	
	
	//for UserDAO to return a user in database
	public UserBean(Integer id, String name, String password, String salt, Timestamp timeStamp) {
		this.uid= id;
		this.uname = name;
		this.hashedPassword = password;
		this.salt = salt;
		this.ulastactive = timeStamp;
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
