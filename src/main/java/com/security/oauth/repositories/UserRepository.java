package com.security.oauth.repositories;


import org.springframework.data.repository.CrudRepository;

import com.security.oauth.entities.User;


public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String username);
}
