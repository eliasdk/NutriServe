package edu.bzu.soa.nutriserve.common;

import java.util.ArrayList;
import java.util.List;

import edu.bzu.soa.nutriserve.model.Recipe;

public class RecipesData {
	
	public static List<Recipe> recipes = new ArrayList<Recipe>();

	public static void addIds() {
		for (int i = 0; i < recipes.size(); i++) {
			recipes.get(i).setId(i);
			if (i == 0) {
				recipes.get(i).setPreviousRecipeApi("");
				recipes.get(i).setNextRecipeApi("https://bzu-nutriserve.appspot.com/recipe/1");
				 
			} else if (i == recipes.size() - 1) {
				recipes.get(i).setPreviousRecipeApi("https://bzu-nutriserve.appspot.com/recipe/" + (i - 1));
				recipes.get(i).setNextRecipeApi("");
			} else {
				recipes.get(i).setPreviousRecipeApi("https://bzu-nutriserve.appspot.com/recipe/" + (i - 1));
				recipes.get(i).setNextRecipeApi("https://bzu-nutriserve.appspot.com/recipe/" + (i + 1));
			}

		}
	}
}
