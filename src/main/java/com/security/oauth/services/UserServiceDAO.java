package com.security.oauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.oauth.entities.User;
import com.security.oauth.repositories.UserRepository;

@Service
public class UserServiceDAO {

    @Autowired
    private UserRepository repo;


    public void save(User user){
        repo.save(user);
    }
    
    public void clear(){
        
        repo.deleteAll();
    }

}
