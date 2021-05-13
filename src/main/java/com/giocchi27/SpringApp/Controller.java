package com.giocchi27.SpringApp;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class Controller {

	@GetMapping
	  public List<String> getAll() {
	    return List.of("uno", "due");
	  }

}
