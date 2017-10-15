package com.security.oauth.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Service;

@Service
public class CustomPasswordEncoder {
	
	
	public boolean pwdMatches(CharSequence rawPassword, byte[] encodedPassword,byte[] salt,int iterations, int keyLength) {
		
		byte[] rawPasswordEncoded = hashPassword(rawPassword.toString().toCharArray(), salt, iterations, keyLength);
		return equals(rawPasswordEncoded, encodedPassword);
	}
	
	public static boolean equals(byte[] b1, byte[] b2)
	
	{
		long diff = (long)b1.length ^ (long)b2.length;
		  for (int i=0; i<b1.length && i < b2.length; i++)
			  diff |= (long)(b1[i] ^ b2[i]);
		        return diff == 0; 
	}
	
	public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
		 
	       try {
	           SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
	           PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
	           SecretKey key = skf.generateSecret( spec );
	           byte[] res = key.getEncoded( );
	           return res;
	 
	       } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
	           throw new RuntimeException( e );
	       }
	   }
	
	public static byte[] getSaltByte() throws NoSuchAlgorithmException
	{
		
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[32];                       
        sr.nextBytes(salt);
        for(int i = 0; i<32; i++) {
            System.out.print(salt[i]);
        }
        return salt;
	    
	}

}