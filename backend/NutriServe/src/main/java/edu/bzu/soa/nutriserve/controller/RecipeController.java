package edu.bzu.soa.nutriserve.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.bzu.soa.nutriserve.model.Recipe;
import edu.bzu.soa.nutriserve.service.RecipeService;
import edu.bzu.soa.nutriserve.common.RecipesData;
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
		RecipeService recipeService = new RecipeService();
		try {
			if (RecipesData.recipes.size() < 7) {
				recipeService.getRecipes("onions");
				recipeService.getRecipes("pizza");
				recipeService.getRecipes("salad");
				recipeService.getRecipes("tuna");
				recipeService.getRecipes("tomato");
				recipeService.getRecipes("broccoli");

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RecipesData.recipes;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ApiOperation(value = "Get Recipe with recipe id = id from the system", response = Recipe.class)
	public Recipe getRecipeWithId(@PathVariable int id) {

		if (id < RecipesData.recipes.size()  ) {
			return RecipesData.recipes.get(id);
		}
		return RecipesData.recipes.get(0);

	}

}
