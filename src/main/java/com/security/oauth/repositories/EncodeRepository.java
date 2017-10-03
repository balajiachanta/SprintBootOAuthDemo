package com.security.oauth.repositories;


import org.springframework.data.repository.CrudRepository;

import com.security.oauth.entities.EncodeDetails;



public interface EncodeRepository extends CrudRepository<EncodeDetails,Long> {
   
	 EncodeDetails findByName(String name);
	 
	 EncodeDetails findByUserName(String name);
	
}
