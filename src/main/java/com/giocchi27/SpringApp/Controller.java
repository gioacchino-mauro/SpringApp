package com.giocchi27.SpringApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test/api/v1")
public class Controller {

	private final EntityBL entityService;

	@Autowired
	Controller(EntityBL entity) {
		this.entityService = entity;
	}


	@GetMapping
	public List<String> getTest() {
		return List.of("uno", "due");
	}

	@GetMapping("/all")
	public List<Entity> getAll() {
		return entityService.getAll();
	}

	@GetMapping("/{id}")
	Entity getById(@PathVariable String id) {

		return entityService.findById(id);
	}

}
