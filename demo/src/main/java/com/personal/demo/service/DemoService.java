package com.personal.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.demo.domain.Demo;
import com.personal.demo.repository.DemoRepository;

@Service
public class DemoService {

	@Autowired
	DemoRepository demoRepo;

	public Optional<Demo> getDetailsById(Long id) {
		return demoRepo.findById(id);
	}

	public List<Demo> getAllDetails() {
		return (List<Demo>) demoRepo.findAll();
	}

	public List<Demo> getDetailsByLastName(String lastName){
		return demoRepo.findByLastName(lastName);
	}
}
