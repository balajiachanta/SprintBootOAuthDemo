package com.security.oauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.oauth.entities.User;
import com.security.oauth.repositories.UserRepository;
import com.security.oauth.util.CustomPasswordEncoder;

@Service
public class UserServiceDAO {

    @Autowired
    private UserRepository repo;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
    		return new CustomPasswordEncoder();
    }

    public void save(User user){
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        repo.save(user);
    }
    
    public void clear(){
        
        repo.deleteAll();
    }

}
