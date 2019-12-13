package com.discovery.assignment.interstellartransportsystemservice.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Getter
@Setter
public class Planet {
	@Id
	private long planetID;

	private String planetName;

	public Planet(long id, String name) {
		this.planetID = id;
		this.planetName = name;
	}

	@Override
	public String toString() {
		return planetName;
	}

}


