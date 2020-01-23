package com.personal.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
	
	
// Scheduler - Cron
//	"0 0 * * * *" = the top of every hour of every day.
//	"*/10 * * * * *" = every ten seconds.
//	"0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
//	"0 0 6,19 * * *" = 6:00 AM and 7:00 PM every day.
//	"0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day.
//	"0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
//	"0 0 0 25 12 ?" = every Christmas Day at midnight
	
	@Scheduled(cron ="*/15 * * * * *")
	public void runOnInterval() {
		System.out.println("Refreshing at : " +new Date());
	}
}
