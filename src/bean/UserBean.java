package bean;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Random;

public class UserBean {
	/**
	 * Declarations of Private Fields
	 */
	private int uid; // AUTO
	private String userName;
	private String hashedPassword;
	private String salt;
	private Timestamp lastActive; // Time Stamp
	private String role;

	// create an user from sign up
	/**
	 * Creates New User object and Stores it. 
	 * @param name
	 * @param password
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public UserBean(String name, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		Random random = new Random();
		this.userName = name;
		this.salt = (new Integer(random.nextInt(99999))).toString();
		this.hashedPassword = hashPassword(password, salt);
	}

	// get an user in database
	/**
	 * gets an user in database
	 * @param id
	 * @param name
	 * @param password
	 * @param salt
	 * @param timeStamp
	 * @param role
	 */
	public UserBean(Integer id, String name, String password, String salt, Timestamp timeStamp, String role) {
		this.uid = id;
		this.userName = name;
		this.hashedPassword = password;
		this.salt = salt;
		this.lastActive = timeStamp;
		this.role = role;
	}
	/**
	 * Verifies the User Password 
	 * @param password
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public boolean verifyPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		return this.hashedPassword.equals(hashPassword(password, this.salt));
	}
	/**
	 * Hashes the Password with SHA-256 and Salt
	 * @param password
	 * @param salt
	 * @return hashedPassword
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public static String hashPassword(String password, String salt) throws UnsupportedEncodingException, NoSuchAlgorithmException {
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    return new String(digest.digest((password + salt).getBytes("UTF-8")), StandardCharsets.UTF_8);
	}
	/**
	 * Tester 
	 * @param args
	 */
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
	/**
	 * Returns UID
	 * @return
	 */
	public int getUid() {
		return uid;
	}
	/**
	 * Sets UID
	 * @param uid
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}
	/**
	 * Returns User Name
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * Sets User Name
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * Returns Hassed Password
	 * @return hashedPassword
	 */
	public String getHashedPassword() {
		return hashedPassword;
	}
	/**
	 * Sets the Hashed Password
	 * @param hashedPassword
	 */
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	/**
	 * Returns the Salt 
	 * @return salt
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * Sets the Salt
	 * @param salt
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * Returns the Last Active Time Stamp
	 * @return lastActive
	 */
	public Timestamp getLastActive() {
		return lastActive;
	}
	/**
	 * Sets the Last Active Time stamp
	 * @param lastActive
	 */
	public void setLastActive(Timestamp lastActive) {
		this.lastActive = lastActive;
	}
	/**
	 * Returns the Role
	 * @return role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * Sets the Role
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
}
