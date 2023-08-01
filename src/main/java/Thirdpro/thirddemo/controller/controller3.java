package Thirdpro.thirddemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/control3")
public class controller3 {
	
		@GetMapping("/sample")
		public String sample() {
			return "Java Developers";
		}
	
	}

