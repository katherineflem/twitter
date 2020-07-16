package com.tts.TechTalentTwitter.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.TechTalentTwitter.model.User;
//creates the table in db and we just add in particular methods we need 
@Repository
//creates a user type that has built in crud operations to get, read, update, and delete a user
public interface UserRepository extends CrudRepository<User, Long> { //what is Long?
    User findByUsername(String username); 
    //upon logging in, this will query the db for the username you typed
}