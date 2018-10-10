package com.altimetrik.recipe.service;

import com.altimetrik.recipe.model.Ingredient;

public interface INutritionService {
	public Ingredient calculateNutritionalValue(String ingredient);
}
