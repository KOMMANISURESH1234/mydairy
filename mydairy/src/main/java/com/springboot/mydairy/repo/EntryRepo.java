package com.springboot.mydairy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.mydairy.entity.Entry;

@Repository
public interface EntryRepo extends JpaRepository<Entry, Long> {
	
	@Query(value="select * from entries where userid=:id",nativeQuery = true)
	public List<Entry> findByUserid(long id);

}
