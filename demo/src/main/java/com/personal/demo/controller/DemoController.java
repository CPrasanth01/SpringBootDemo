package com.personal.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.demo.domain.Demo;
import com.personal.demo.service.DemoService;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	DemoService demoService;

	@Secured("ROLE_ADMIN")
	@GetMapping("/SayHello")
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok().body("Hello");
	}

	@Secured("ROLE_TL")
	@GetMapping("/AllRecords")
	public ResponseEntity<List<Demo>> getAll() {
		return ResponseEntity.ok().body(demoService.getAllDetails());
	}

	// Path Param
	@GetMapping("/id/{value}")
	public ResponseEntity<Optional<Demo>> getById(@PathVariable(required = true) String value) {
		return ResponseEntity.ok().body(demoService.getDetailsById(Long.parseLong(value)));
	}

	//	Query Param
	//Input format will be like localhost:8090/demo/?id=1
	@GetMapping("/")
	public ResponseEntity<Optional<Demo>> getByIdQueryParam(@RequestParam(name = "id" , required = true) String value) {
		return ResponseEntity.ok().body(demoService.getDetailsById(Long.parseLong(value)));
	}

	//Input format will be like localhost:8090/demo/all?id=1&firstName=Hi
	@GetMapping("/all")
	public ResponseEntity<List<Demo>> getAllFilteredUsers(@RequestParam MultiValueMap<String,String> filterConditions) {
		System.out.println("Inside MultiValueMap" + filterConditions.toString());
		List<Demo> allUserDetails = demoService.getAllDetails();
		List<String> filter = filterConditions.get("id");
		List<Demo> filteredDetails = (List<Demo>) allUserDetails.stream().filter(user ->
		          (user.getId().compareTo(Long.parseLong(filter.get(0)))==0)?true:false).collect(Collectors.toList());
		return ResponseEntity.ok().body(allUserDetails);
	}
	//Delete details Query Param
	@DeleteMapping("/")
	public ResponseEntity<String> deleteUser(@RequestParam(name = "id", required = true) String id){
		return ResponseEntity.ok().body(demoService.deleteDetail(Long.parseLong(id)));
	}
	
	// Insert Post Mapping
	@PostMapping("/detail")
	public ResponseEntity<Demo> addNewUser(@RequestBody Demo demoDetail ) {
		System.out.println(demoDetail.toString());
		return ResponseEntity.ok().body(demoService.insertDetail(demoDetail));
	}
	//Update Details
	@PutMapping("/detail/update")
	public ResponseEntity<Demo> updateNewUser(@RequestBody Demo demoDetail ) {
		return ResponseEntity.ok().body(demoService.updateDetail(demoDetail));
	}
	

}
