package edu.bzu.soa.nutriserve.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		 Gym gym1 = new Gym ();
		 Gym gym2 = new Gym ();
		 gym1.setAddress("Rammalah, Irsal Street 15");
		 gym1.setDescription("Best Gym in Ramallah. Open 24 hours");
		 gym1.setId(5);
		 gym1.setLocation("31.9146700, 35.206752 ");
		 gym1.setName("Gym el abtal");
		 gym1.setPreviousGymApi("https://bzu-nutriserve.appspot.com/gym/1");
		 gym1.setNextGymApi("https://bzu-nutriserve.appspot.com/gym/3");
		 
		 gym2.setAddress("Rammalah, Massyon, Naji ali street 26");
		 gym2.setDescription("Most classy Gym in the country ");
		 gym2.setId(44);
		 gym2.setLocation("33.8876700, 34.234752 ");
		 gym2.setName("Super Gym");
		 gym2.setPreviousGymApi("https://bzu-nutriserve.appspot.com/gym/1");
		 gym2.setNextGymApi("https://bzu-nutriserve.appspot.com/gym/3");
		 
		 plan.addGym(gym1);
		 //plan.addGym(gym2);
		 
		 Recipe recipe1 = new Recipe();
		 Recipe recipe2 = new Recipe();
		 
		 recipe1.setCuisine("westren");
		 recipe1.setCalories(1479);
		 recipe1.setDescription("Checken Salad with carrots");
		 recipe1.setId(236);
		 recipe1.setImageUrl("http://images.media-allrecipes.com/userphotos/560x315/2204020.jpg");
		 recipe1.setName("Carrot Salad");
		 recipe1.setRecipeurl("http://allrecipes.com/recipe/8854/broccoli-chicken-divan/?internalSource=popular&referringContentType=home%20page&clickId=cardslot%209");
		 recipe1.setPreviousRecipeApi("https://bzu-nutriserve.appspot.com/recipe/1");
		 recipe1.setNextRecipeApi("https://bzu-nutriserve.appspot.com/recipe/3");
		 
		 recipe2.setCuisine("Orental");
		 recipe2.setCalories(2345);
		 recipe2.setDescription("A well done Beef Burger with cheese and spicy topups");
		 recipe2.setId(555);
		 recipe2.setImageUrl("https://www.bbcgoodfood.com/sites/default/files/recipe_images/recipe-image-legacy-id--1035715_11.jpg");
		 recipe2.setName("Beef Burger");
		 recipe2.setRecipeurl("https://www.bbcgoodfoodme.com/recipes/1514/beef-burgers-learn-to-make?GEO_REDIRECTED=true#.WR1sUxOGOAw");
		 
		 
		 plan.addRecipe(recipe1);
		// plan.addRecipe(recipe2);
		 return plan;

		 
		 
	}
}
