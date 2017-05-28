package edu.bzu.soa.nutriserve.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.bzu.soa.nutriserve.common.GymsData;
import edu.bzu.soa.nutriserve.common.RecipesData;
import edu.bzu.soa.nutriserve.model.Gym;
import edu.bzu.soa.nutriserve.model.Plan;
import edu.bzu.soa.nutriserve.model.Recipe;
import edu.bzu.soa.nutriserve.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/plan")
@Api(value="NutriServe daily plan", description="User daily food plan by NutriServe online service")
public class PlanController {

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get user Daily diet plan including recipes and newarby gyms",response = Plan.class)
	Plan getDailyPlan(){//@RequestParam//"userid") int userId, @RequestParam("location") String location, @RequestParam("preference") String foodPreference, @RequestParam("caloriesLimit")  int caloriesLimit){
		
		 Plan plan = new Plan();
		
		 
		 plan.addGym(GymsData.gyms.get(0));
		 //plan.addGym(gym2);
		 
		 
		 plan.addRecipe(RecipesData.recipes.get(0));
		// plan.addRecipe(recipe2);
		 return plan;

		 
		 
	}
}
