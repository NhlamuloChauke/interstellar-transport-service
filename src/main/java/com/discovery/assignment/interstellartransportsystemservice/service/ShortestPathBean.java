package com.discovery.assignment.interstellartransportsystemservice.service;

import com.discovery.assignment.interstellartransportsystemservice.utils.PathPlanningAlgorithm;
import com.discovery.assignment.interstellartransportsystemservice.model.Planet;
import com.discovery.assignment.interstellartransportsystemservice.model.Route;
import com.discovery.assignment.interstellartransportsystemservice.repositories.PlanetRepository;
import com.discovery.assignment.interstellartransportsystemservice.repositories.RouteRepository;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * 
 * @author nhlamulo
 */
@Service
@Lazy
public class ShortestPathBean implements PathPlanningAlgorithm {


	private static Logger LOG = LoggerFactory.getLogger(ShortestPathBean.class);

	
	@Autowired
	private PlanetRepository planetRepository;

	@Autowired
	private RouteRepository routesRepository;

	private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(
			DefaultWeightedEdge.class);
		

	@PostConstruct
	private void initWeightedGraph(){
		addVertex();
		addEdges();
	}


	public void addVertex(){
		for (Planet planet : planetRepository.findAll()) {
			this.graph.addVertex(Long.toString(planet.getPlanetID()));
		}
	}

	private void addEdges(){
		DefaultWeightedEdge edge = null;
		for (Route route : routesRepository.findAll()) {
			String source = Long.toString(route.getSource().getPlanetID());

			String destination = Long.toString(route.getPanetDestination().getPlanetID());
			if(source != destination){
				edge = this.graph.addEdge(source,destination);
			}
			addWeight(edge, route.getDistance());
		}
	}

	private void addWeight(DefaultWeightedEdge edge, float weight) {
		this.graph.setEdgeWeight(edge, weight);
	}


	@Override
	public String findShortestPath(String source, String destination) {
		return DijkstraShortestPath.findPathBetween(this.graph, source,destination).toString();
	}

	@PreDestroy
	public void cleanup(){
		this.graph = null;
	}
}

