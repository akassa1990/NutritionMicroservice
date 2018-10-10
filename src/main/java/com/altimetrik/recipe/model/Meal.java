package com.altimetrik.recipe.model;

import java.util.ArrayList;
import java.util.List;

public class Meal {
	private String mealName;
	private List<String> ingredients = new ArrayList<>();
	private double totalNutritionalValue;
	
	
	public Meal() {
		super();
	}
	
	public void addIngredient(String ingredient) {
		this.ingredients.add(ingredient);
	}
	
	public void addINutritionalValue(double value) {
		this.totalNutritionalValue+=value;	
	}
	
	public double getTotalNutritionalValue() {
		return totalNutritionalValue;
	}

	public void setTotalNutritionalValue(double nutritionalValue) {
		this.totalNutritionalValue = nutritionalValue;
	}

	public String getMealName() {
		return mealName;
	}
	public void setMealName(String recipeName) {
		this.mealName = recipeName;
	}
	public List<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}	
	
		
}
