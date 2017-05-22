package edu.bzu.soa.nutriserve.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.bzu.soa.nutriserve.model.Gym;
import edu.bzu.soa.nutriserve.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/gym")
@Api(value = "NutriServe Gym", description = "Free Gyms subscriped in NutreServe Network")
public class GymController {

	List<Gym> gyms = new ArrayList<Gym>();

	@ApiOperation(value = "Get All Gyms in the system", response = List.class)
	@RequestMapping(method = RequestMethod.GET)
	public List<Gym> getAllGyms() {
		return gyms;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ApiOperation(value = "Get Gym with gym id = id from the system", response = Gym.class)
	public Gym getGymWithId(@PathVariable Long id) {
		Gym gym1 = new Gym();

		gym1.setAddress("Aboud, main Street 24");
		gym1.setDescription("Best Gym in a village that you can find");
		gym1.setId(5);
		gym1.setLocation("32.123456, 32.223455 ");
		gym1.setName("Aboud Gym");
		gym1.setPreviousGymApi("https://bzu-nutriserve.appspot.com/gym/1");
		gym1.setNextGymApi("https://bzu-nutriserve.appspot.com/gym/3");
		return gym1;
	}

}
