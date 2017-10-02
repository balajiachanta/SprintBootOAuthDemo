package com.security.oauth.util;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomPasswordEncoder implements PasswordEncoder{
	
	int max = 10000;
	int min = 1;
	
	Random rand = new Random(); 
	
	int value = rand.nextInt((max - min) + 1) + min;
	int length = rand.nextInt((max - min) + 1) + min;
	
	byte[] randomSalt = getSaltByte();

	@Override
	public String encode(CharSequence rawPassword) {
		System.out.println("@@@@ " + rawPassword.toString() + " @@@" + hashPassword(rawPassword.toString().toCharArray(), "salt".getBytes(), 100, 256));
		return hashPassword(rawPassword.toString().toCharArray(), "salt".getBytes(), 100, 256);
	}


	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String rawPasswordEncoded = hashPassword(rawPassword.toString().toCharArray(), "salt".getBytes(), 100, 256);
		
		
		String enteredPwd = Base64.encodeBase64String(rawPasswordEncoded.getBytes());
		boolean match = enteredPwd.equals(encodedPassword);
		return match;

	}
	
	public static boolean equals(byte[] b1, byte[] b2)
	
	{
		long diff = (long)b1.length ^ (long)b2.length;
		  for (int i=0; i<b1.length && i < b2.length; i++)
			  diff |= (long)(b1[i] ^ b2[i]);
		        return diff == 0; 
	}
	
	public static String hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
		 
	       try {
	           SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
	           PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
	           SecretKey key = skf.generateSecret( spec );
	           byte[] res = key.getEncoded( );
	           return new String(res);
	 
	       } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
	           throw new RuntimeException( e );
	       }
	   }
	
	public static byte[] getSaltByte()
	{
		
	        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder salt = new StringBuilder();
	        Random rnd = new Random();
	        while (salt.length() < 18) { // length of the random string.
	            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	            salt.append(SALTCHARS.charAt(index));
	        }
	        String saltStr = salt.toString();
	        System.out.println("random salt " +saltStr);
	        return saltStr.getBytes();
	    
	}

}