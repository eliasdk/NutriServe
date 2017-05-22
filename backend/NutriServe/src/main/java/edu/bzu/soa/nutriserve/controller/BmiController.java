package edu.bzu.soa.nutriserve.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.bzu.soa.nutriserve.model.Bmi;
import edu.bzu.soa.nutriserve.model.Plan;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/bmi")
@Api(value = "NutriServe daily plan", description = "User daily food plan by NutriServe online service")
public class BmiController {

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get user Daily diet plan including recipes and newarby gyms", response = Plan.class)
	Bmi getBmi(@RequestParam("weight") float weight, @RequestParam("length") float length) {
		Bmi bmi = new Bmi(weight, length);
        
		return bmi;

	}
}
