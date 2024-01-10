package com.springboot.mydairy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mydairy.entity.Entry;
import com.springboot.mydairy.repo.EntryRepo;

@Service
public class EntryServiceimpl implements EntryService {
	
	@Autowired
	private EntryRepo entryrepo;
	
	@Override
	public Entry saveEntry(Entry entry) {
		return entryrepo.save(entry);
	}

	@Override
	public Entry updateEntry(Entry entry) {
		return entryrepo.save(entry);
	}

	@Override
	public void deleteEntry(Entry entry) {
		entryrepo.delete(entry);
	}

	@Override
	public Entry findById(long id) {
		return entryrepo.findById(id).get();
	}

	@Override
	public List<Entry> findAll() {
		return entryrepo.findAll();
	}

	@Override
	public List<Entry> findByUserid(long id) {
		return entryrepo.findByUserid(id);
	}

}
