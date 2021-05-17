package com.giocchi27.SpringApp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EntityBL {

	private List<Entity> entityList;

	public EntityBL() {
		super();
		this.entityList = new ArrayList<Entity>();
		this.entityList.add(new Entity("1", "uno"));
		this.entityList.add(new Entity("2", "due"));
		this.entityList.add(new Entity("3", "tre"));
		this.entityList.add(new Entity("4", "quattro"));
		this.entityList.add(new Entity("5", "cinque"));
	}

	public List<Entity> getAll() {
		return this.entityList;
	}

	public Entity findById(String id) {

		Entity res = this.entityList.stream()
				  .filter((x) -> x.getId().equals(id))
				  .findFirst()
				  .orElse(new Entity("", ""));
		return res;
	}

}
