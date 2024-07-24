package br.edu.ifsp.complementary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/hello")
public class WebController {

	@GetMapping
	public String hello() {
    	return "Hello world";
	}

}
