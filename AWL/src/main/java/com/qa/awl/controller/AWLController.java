package com.qa.awl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AWLController {
	
	@GetMapping("/test") //Type of Request
	public String test() {
		return "Yay, it works";
	}
	

}
