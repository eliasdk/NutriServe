package edu.bzu.soa.nutriserve.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.bzu.soa.nutriserve.common.GymsData;
import edu.bzu.soa.nutriserve.common.RecipesData;
import edu.bzu.soa.nutriserve.model.Gym;
import edu.bzu.soa.nutriserve.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/gym")
@Api(value = "NutriServe Gym", description = "Free Gyms subscriped in NutreServe Network")
public class GymController {

	 

	@ApiOperation(value = "Get All Gyms in the system", response = List.class)
	@RequestMapping(method = RequestMethod.GET)
	public List<Gym> getAllGyms() {
		return GymsData.gyms;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ApiOperation(value = "Get Gym with gym id = id from the system", response = Gym.class)
	public Gym getGymWithId(@PathVariable int id) {
		
		if (id < GymsData.gyms.size()  ) {
			return GymsData.gyms.get(id);
		}
		return GymsData.gyms.get(0);
	}

}
