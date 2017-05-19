package edu.bzu.soa.nutriserve.model;

import java.util.ArrayList;
import java.util.List;

public class Plan {
     
	List <Recipe> recipes = new ArrayList<Recipe> () ;
	List <Gym> gyms = new ArrayList<Gym> ();
	
	public List<Recipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
	public List<Gym> getGyms() {
		return gyms;
	}
	public void setGyms(List<Gym> gyms) {
		this.gyms = gyms;
	}

	public void addGym(Gym gym) {
		gyms.add(gym);
	}
	public void addRecipe(Recipe recipe) {
		recipes.add(recipe);
	}
	
}
