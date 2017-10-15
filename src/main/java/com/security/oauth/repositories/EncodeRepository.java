package com.security.oauth.repositories;


import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.security.oauth.entities.EncodeDetails;

@Transactional
public interface EncodeRepository extends CrudRepository<EncodeDetails,Long> {
   
	public EncodeDetails findByUsername(String username);
	
}
