package edu.bzu.soa.nutriserve.model;

public class Recipe {

	int id  ;
	String name ;
	String description;
	String  cuisine;
	String imageUrl ;
	String recipeurl;
	int calories ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getRecipeurl() {
		return recipeurl;
	}
	public void setRecipeurl(String recipeurl) {
		this.recipeurl = recipeurl;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	
}
