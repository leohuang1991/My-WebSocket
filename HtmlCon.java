package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlCon {

	@GetMapping(path="/aa1")
	public String aa10() {
		return "index";
	}
	
	@GetMapping(path="/aa2")
	public String aa30() {
		return "super2";
	}
	
	@GetMapping(path="/aa3")
	public String aa60() {
		return "super3";
	}
	
	@GetMapping(path="/aa5")
	public String aa70() {
		return "super5";
	}
}
