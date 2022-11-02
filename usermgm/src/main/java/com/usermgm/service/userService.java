package com.usermgm.service;

import java.util.List;

import com.usermgm.dto.userDTO;
import com.usermgm.exception.globalException;
import com.usermgm.model.user;
import com.usermgm.repository.userRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {

	//Dependency injection for Repository
	@Autowired
	private userRepository userepo;

	//Adding the new user satisfies user validation
	public  user addUser(userDTO u1) {
		
		user u2 = user.build(u1.getUserId(), u1.getUserName(),u1.getUserPassword(),u1.getUserEmail(), u1.getUserPhone()); 
		return userepo.save(u2);
	
	}
	
	//Retriving all the Users
	public List<user> getUser(){
		return userepo.findAll();
	}
	
	//Deleting the already existed user from DataBase
	//If The user is not existed it throws GlobalException
	public user deleteUser(int id) throws globalException {
		
		user _u2 = userepo.findByUserId(id);
		if(_u2!=null) {
			userepo.deleteById(id);
			return _u2;
		}
		else {
			throw new globalException("user not found!...");
		}
		
	}	
	
	//Updating user details
	public user updateUser(userDTO u1) {
		
		user u2 = user.build(u1.getUserId(), u1.getUserName(),u1.getUserPassword(),u1.getUserEmail(), u1.getUserPhone()); 
		return userepo.save(u2);

	}
	
	//Retriving details of specific User
	public user getUser(int userid) throws globalException {
		user _u1 = userepo.findByUserId(userid);
		if(_u1!=null) {
			return _u1;
		}
		else {
			throw new globalException("user not found with userid "+userid);
		}
	}
	
	
	
}

