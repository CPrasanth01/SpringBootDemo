package com.personal.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.demo.domain.Demo;
import com.personal.demo.service.DemoService;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	DemoService demoService;

	@Secured("ROLE_ADMIN")
	@GetMapping("/SayHello")
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok().body("Hello");
	}

	@GetMapping("/AllRecords")
	public ResponseEntity<List<Demo>> getAll() {
		return ResponseEntity.ok().body(demoService.getAllDetails());
	}
}
