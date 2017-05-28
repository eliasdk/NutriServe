package edu.bzu.soa.nutriserve.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.bzu.soa.nutriserve.model.HealthStatus;
import edu.bzu.soa.nutriserve.model.User;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/healthstatus")
@Api(value="NutriServe User Status", description="User health status provided by NutriServe online service")
public class HealthStatusController {
	
 
	@RequestMapping(method = RequestMethod.GET)
	public HealthStatus getHealthStatus(@RequestParam("userid") String userId) {
		HealthStatus status = new HealthStatus();
		status.setUserId(Long.parseLong(userId));
		status.setBmi(25);
		status.setHealthStatus("Healthy");
		return status;
		
	}

}
