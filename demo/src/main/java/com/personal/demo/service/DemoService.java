package com.personal.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.personal.demo.domain.Demo;
import com.personal.demo.repository.DemoRepository;

@Service
public class DemoService {
	private static final Logger log = LogManager.getLogger(DemoService.class);

	@Autowired
	DemoRepository demoRepo;

	public Optional<Demo> getDetailsById(Long id) {
		Optional<Demo> detail = demoRepo.findById(id);
		System.out.println(detail.toString());
		return detail;
	}

	public List<Demo> getAllDetails() {
		return (List<Demo>) demoRepo.findAll();
	}

	public List<Demo> getDetailsByLastName(String lastName) {
		return demoRepo.findByLastName(lastName);
	}

	public Demo insertDetail(Demo demoDetail) {
		return demoRepo.save(demoDetail);
	}

	public Demo updateDetail(Demo demoDetail) {
		Optional<Demo> detail = demoRepo.findById(demoDetail.getId());
		if(!StringUtils.isEmpty(demoDetail)) {
			Demo updatedDetail = detail.get();
			BeanUtils.copyProperties(demoDetail, updatedDetail);
			return demoRepo.save(updatedDetail);
		}
		return null;
	}
	public String deleteDetail(long id) {
		if(id>0) {
			demoRepo.deleteById(id);
			return "Success";
		}
		return "Not Valid";
	}
	// Scheduler - Cron
	//	"0 0 * * * *" = the top of every hour of every day.
	//	"*/10 * * * * *" = every ten seconds.
	//	"0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
	//	"0 0 6,19 * * *" = 6:00 AM and 7:00 PM every day.
	//	"0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day.
	//	"0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
	//	"0 0 0 25 12 ?" = every Christmas Day at midnight

	@Scheduled(cron = "0 0 * * * *")
	public void runOnInterval() {
		log.info("Refreshing at : " + new Date());
	}
}
