package com.altimetrik.recipe.model;

public class Ingredient {
	
	private String ingredientName;
	private double protein;
	private double carbohydrate;
	private double fat;
	private double totalNutrition;
	
	public Ingredient() {
		super();
	}

	public Ingredient(String ingredientName, double protein, double carbohydrate, double fat) {
		super();
		this.ingredientName = ingredientName;
		this.protein = protein;
		this.carbohydrate = carbohydrate;
		this.fat = fat;
	}

	public double getTotalNutrition() {
		return totalNutrition;
	}

	public void setTotalNutrition(double totalNutrition) {
		this.totalNutrition = totalNutrition;
	}
	
	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	public double getProtein() {
		return protein;
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}
	public double getCarbohydrate() {
		return carbohydrate;
	}
	public void setCarbohydrate(double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	public double getFat() {
		return fat;
	}
	public void setFat(double fat) {
		this.fat = fat;
	}


}
