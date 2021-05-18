package com.giocchi27.SpringApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@RestController
@RequestMapping("api/v1")
public class Controller {

	private final EntityBL entityService;
	Logger logger = LogManager.getLogger(Controller.class);

	@Autowired
	Controller(EntityBL entity) {
		this.entityService = entity;
	}


	@GetMapping
	public List<String> getTest() {
		System.out.println("GET test");
		logger.error("GET test");
		return List.of("uno", "due");
	}

	@GetMapping("/all")
	public List<Entity> getAll() {
		System.out.println("GET all");
		List<Entity> res = entityService.getAll();

		return res;
	}

	@GetMapping("/{id}")
	Entity getById(@PathVariable String id) {
		System.out.println("GET by id");
		Entity res = entityService.findById(id);
		System.out.println(res.toString());
		logger.error(res.toString());
		return res;
	}

}
