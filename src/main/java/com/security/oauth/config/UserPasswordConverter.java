package com.security.oauth.config;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.oauth.entities.EncodeDetails;
import com.security.oauth.repositories.EncodeRepository;
import com.security.oauth.util.CustomPasswordEncoder;



@Service
public class UserPasswordConverter {

	@Autowired
	private EncodeRepository encodeRepository;



	public boolean passwordEncryptor(String password, Long Id) throws NoSuchAlgorithmException
	{
		EncodeDetails encode = null;
		int max = 12000;
		int min = 8000;
		int keyLength = 160;
		Random rand = new Random(); 
		int value = rand.nextInt((max - min) + 1) + min;
		byte[] randomSalt = CustomPasswordEncoder.getSaltByte();
		byte[] rawPasswordEncoded = CustomPasswordEncoder.hashPassword(password.toCharArray(), randomSalt, value, keyLength);

		if(rawPasswordEncoded != null)
		{
			String encodedPassword = Base64.getEncoder().encodeToString(rawPasswordEncoded);
			String encodedSalt = Base64.getEncoder().encodeToString(randomSalt);
			encode = new EncodeDetails(encodedSalt, value, encodedPassword, Id);
			encodeRepository.save(encode);
			return true;
		}

		else
		{
			return false;
		}

	}

}
