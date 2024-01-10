package com.springboot.mydairy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.mydairy.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
	@Query(value="select * from users where username=:username LIMIT 1",nativeQuery = true)
	public User findByUsername(String username); 

}
