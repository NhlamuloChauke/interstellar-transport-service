package com.discovery.assignment.interstellartransportsystemservice.repositories;

import com.discovery.assignment.interstellartransportsystemservice.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PlanetRepository extends JpaRepository<Planet, String> {

}
