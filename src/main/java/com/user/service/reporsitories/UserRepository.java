package com.user.service.reporsitories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.service.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	//if you want to any custom method or query 
}
