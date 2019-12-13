package com.discovery.assignment.interstellartransportsystemservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Data
@Getter
@Setter
public class Route {

	@Id
	private long routeID;

	@OneToOne
	@JoinColumn(name = "source")
	private Planet source;

	@OneToOne
	@JoinColumn(name = "panetDestination")
	private Planet panetDestination;

	private float distance;

	public Route(long routeID, Planet source, Planet panetDestination, float distance) {
		this.routeID = routeID;
		this.source = source;
		this.panetDestination = panetDestination;
		this.distance = distance;
	}
}