package edu.bzu.soa.nutriserve.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.bzu.soa.nutriserve.model.Recipe;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/recipe")
@Api(value = "NutriServe Recipe", description = "Free Personal Recipes supported for NutreServe users")
public class RecipeController {

	List<Recipe> recipes = new ArrayList<Recipe>();

	@ApiOperation(value = "Get All Recipes in the system", response = List.class)
	@RequestMapping(method = RequestMethod.GET)
	public List<Recipe> getAllReciepes() {
		return recipes;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ApiOperation(value = "Get Recipe with recipe id = id from the system", response = Recipe.class)
	public Recipe getRecipeWithId(@PathVariable Long id) {
		Recipe recipe1 = new Recipe();

		recipe1.setCuisine("westren");
		recipe1.setCalories(1479);
		recipe1.setDescription("Checken Salad with carrots");
		recipe1.setId(236);
		recipe1.setImageUrl("http://images.media-allrecipes.com/userphotos/560x315/2204020.jpg");
		recipe1.setName("Carrot Salad");
		recipe1.setRecipeurl(
				"http://allrecipes.com/recipe/8854/broccoli-chicken-divan/?internalSource=popular&referringContentType=home%20page&clickId=cardslot%209");
		recipe1.setPreviousRecipeApi("https://bzu-nutriserve.appspot.com/recipe/1");
		recipe1.setNextRecipeApi("https://bzu-nutriserve.appspot.com/recipe/3");
		return recipe1;
	}

}
