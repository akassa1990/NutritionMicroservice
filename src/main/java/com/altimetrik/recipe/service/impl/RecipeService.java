package com.altimetrik.recipe.service.impl;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.altimetrik.recipe.model.Ingredient;
import com.altimetrik.recipe.model.Meal;
import com.altimetrik.recipe.service.INutritionService;
import com.altimetrik.recipe.service.IRecipeService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("recipeService")
public class RecipeService implements IRecipeService {
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	INutritionService nutritionService;

	private static final String APP_ID = "5831cc68";
	private static final String APP_KEY = "4838ca85f041a918c52933ae349a9199";
	
	@Override
	public Meal searchRecipe(String meal) {

		final String RECIPE_URL = "https://api.edamam.com/search?q=" + meal + "&app_id=" + APP_ID + "&app_key=" + APP_KEY;

		ResponseEntity<String> eventsEntity = this.restTemplate.getForEntity(RECIPE_URL, String.class);
		String recipeJson = eventsEntity.getBody();

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = null;
		try {
			rootNode = objectMapper.readTree(recipeJson);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// reading array of objects
		JsonNode hits = rootNode.path("hits");
		System.out.println("HITS Size: " + hits.size());
		Iterator<JsonNode> hitsElements = hits.elements();
		// while(hitsElements.hasNext()) { ... } // iterate through all elements of hits array
		Iterator<JsonNode> fnode = hitsElements.next().path("recipe").path("ingredientLines").elements();
		Ingredient ing;
		String ingredientName = "";
		Meal mealObject = new Meal();
		mealObject.setMealName(meal);
		while(fnode.hasNext()) {
			ingredientName = fnode.next().asText();
			ing = this.nutritionService.calculateNutritionalValue(ingredientName);
			System.out.println("INGREDIENT: "+ing.getIngredientName());
			
			mealObject.addIngredient(ingredientName);
			System.out.println(ing.getTotalNutrition());
			mealObject.addINutritionalValue(ing.getTotalNutrition());		
		}	

		return mealObject;
	}
	
}
