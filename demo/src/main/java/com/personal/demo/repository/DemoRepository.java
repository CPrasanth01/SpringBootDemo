package com.personal.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.personal.demo.domain.Demo;

public interface DemoRepository extends CrudRepository<Demo, Long> {
	public List<Demo> findByLastName(String lastName);

	public Demo findById(long id);
}
