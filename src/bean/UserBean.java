package bean;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Random;

public class UserBean {
	private int uid; // AUTO
	private String userName;
	private String hashedPassword;
	private String salt;
	private Timestamp lastActive; // Time Stamp
	private String role;

	// create an user from sign up
	public UserBean(String name, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		Random random = new Random();
		this.userName = name;
		this.salt = (new Integer(random.nextInt(99999))).toString();
		this.hashedPassword = hashPassword(password, salt);
	}

	// get an user in database
	public UserBean(Integer id, String name, String password, String salt, Timestamp timeStamp, String role) {
		this.uid = id;
		this.userName = name;
		this.hashedPassword = password;
		this.salt = salt;
		this.lastActive = timeStamp;
		this.role = role;
	}

	public boolean verifyPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		return this.hashedPassword.equals(hashPassword(password, this.salt));
	}
	
	public static String hashPassword(String password, String salt) throws UnsupportedEncodingException, NoSuchAlgorithmException {
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    return new String(digest.digest((password + salt).getBytes("UTF-8")), StandardCharsets.UTF_8);
	}

	public static void main(String[] args) {
		// Testing
		try {
			System.out.println(hashPassword("funny", "666666"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	


	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Timestamp getLastActive() {
		return lastActive;
	}

	public void setLastActive(Timestamp lastActive) {
		this.lastActive = lastActive;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
