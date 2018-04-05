package security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import sun.misc.BASE64Encoder;

public class Encryption {

	public static String generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[32];
		random.nextBytes(bytes);
		return new BASE64Encoder().encode(bytes);
		
	}
	
	public static String getHashedPassword(String password, String salt) {
		
		try {
		    MessageDigest digest = MessageDigest.getInstance("SHA-256");
		    byte[] hashedBytes = digest.digest((password + salt).getBytes("UTF-8"));
		    
		    
		    return new BASE64Encoder().encode(hashedBytes);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
		    return null;
		}
		
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(generateSalt());
		System.out.println(getHashedPassword("u1password", "aXWREtSv09UPQgasjUVuuwHtilXSPpFT2pAqk/J9O+A="));
		System.out.println(getHashedPassword("u1pass"+"word", "aXWREtSv09UPQgasjUVuuwHtilXSPpFT2pAqk/J9O+A="));

	}

}
