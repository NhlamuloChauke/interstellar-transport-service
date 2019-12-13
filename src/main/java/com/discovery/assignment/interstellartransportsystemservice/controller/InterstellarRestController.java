package com.discovery.assignment.interstellartransportsystemservice.controller;

import com.discovery.assignment.interstellartransportsystemservice.utils.PathPlanningAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * @author nhlamulo
 */
@RestController
/*@Api(tags = {"BucketClassification"})
@SwaggerDefinition(tags = {
		@Tag(name = "BucketClassification", description = "Bucket classification static data service")
})*/
public class InterstellarRestController {
	
	@Autowired
	@Lazy
	private PathPlanningAlgorithm shortestPathService;

	@RequestMapping(method = RequestMethod.GET, value="/shortestpath/{source}/{destination}")
	@ResponseBody
	public String getShortestPath(@PathVariable("source") String source, @PathVariable("destination") String destination){
		return shortestPathService.findShortestPath(source, destination);
	}
}
