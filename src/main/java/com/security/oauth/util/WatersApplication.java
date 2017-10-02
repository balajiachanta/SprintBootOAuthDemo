package com.security.oauth.util;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Base64;


public class WatersApplication {
	
	private static final String DECRYPT_STRING_ERROR = "DECRYPT_STRING_ERROR";

	public static void main(String[] args) {
		
		int max = 10000;
		int min = 1;
		
		String formPassword = "shirehplc";
		String dataBasePassword = "xjMjQ5Mw==c2hpcmVocG";
		
		Random rand = new Random(); 
		
		int value = rand.nextInt((max - min) + 1) + min;
		int length = rand.nextInt((max - min) + 1) + min;
		
		String decrypted = decrypt(dataBasePassword);
//		System.out.println("decrypted password " +decrypted);
		
		byte[] randomSalt = getSaltByte();
		
		byte[] password = hashPassword(getUserPasswordDatabase(decrypted), randomSalt, value, length);
		System.out.println("hashed password " +password+ " random value " +value+ "random salt " +randomSalt);
		
		byte[] password1 = hashPassword(getUserFormPassword(formPassword), randomSalt, value, length);
		System.out.println("hashed password " +password1+ " random value " +value+ "random salt " +randomSalt);
		
		
		if(equals(password, password1))
			System.out.println("password is equal");
		
		else 
			System.out.println("password is not equal");

	}



	public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
		 
	       try {
	           SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
	           PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
	           SecretKey key = skf.generateSecret( spec );
	           byte[] res = key.getEncoded( );
	           return res;
	 
	       } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) 
	       {
	           throw new RuntimeException( e );
	       }
	   }
	
	public static char[] getUserPasswordDatabase(String dataBaseInput)
	{
		return dataBaseInput.toCharArray();
	}
	
	public static char[] getUserFormPassword(String formInput)
	{
		return formInput.toCharArray();
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
	
	
	public static boolean equals(byte[] b1, byte[] b2)
	
	{
		long diff = (long)b1.length ^ (long)b2.length;
		  for (int i=0; i<b1.length && i < b2.length; i++)
			  diff |= (long)(b1[i] ^ b2[i]);
		        return diff == 0; 
	}
	
	public static String decrypt(String estr)
	{
		try
		{
			
			String aestr = revString(estr,false);
			byte[] bytes = Base64.decodeBase64(aestr.getBytes("UTF-8"));
			
			String str = new String(bytes);
			
			int len = str.length();
			String lastChr = str.substring(len-1, len);
			int dlen = Integer.parseInt(lastChr);
			String val = str.substring(0,len-dlen-1);
					
			return val;

		}
		catch(Exception e)
		{
			System.out.println("Problem decrypting estr["+estr+"]");
			throw new RuntimeException(DECRYPT_STRING_ERROR,e);
		}
	}
	
	private static String revString(String str,boolean direction)
	{
		int len = str.length();
		int midlen = len/2;
		
		int clen = midlen*2;
		if(clen != len)//odd Length
		{
			if(!direction)//reverse
			{
				midlen = midlen+1;
			}
		}
		
		String str1 = str.substring(0, midlen);
		String str2 = str.substring(midlen, len);
		String rstr = 	str2 + str1;
		return rstr;		
	}

}