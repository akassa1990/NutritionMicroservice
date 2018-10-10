package com.altimetrik.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.recipe.model.Ingredient;
import com.altimetrik.recipe.model.Meal;
import com.altimetrik.recipe.service.INutritionService;
import com.altimetrik.recipe.service.IRecipeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class RecipeController {
	@Autowired
	IRecipeService recipeService;
	
	@Autowired
	INutritionService nutritionService;
	
	@ApiOperation(value="Get Recipe of Meal")
	@GetMapping(value="/recipies",produces=MediaType.APPLICATION_JSON_VALUE)
	public Meal getEvents(@RequestParam("meal") String meal){
		System.out.println("Your request para is: "+ meal);
		return this.recipeService.searchRecipe(meal);
	}
	
	@ApiOperation(value="Get Nutriotional Value of Ingredients")
	@GetMapping(value="/nutritions",produces=MediaType.APPLICATION_JSON_VALUE)
	public Ingredient getNutritionInfo(@RequestParam("ingredient") String ingredient){
		System.out.println("Your request parameter is: " + ingredient);
		return this.nutritionService.calculateNutritionalValue(ingredient);
	}
}
