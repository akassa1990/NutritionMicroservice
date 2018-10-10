package com.altimetrik.recipe.service.impl;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.altimetrik.recipe.model.Ingredient;
import com.altimetrik.recipe.model.NutritionRequest;
import com.altimetrik.recipe.service.INutritionService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("nutritionService")
public class NutritionService implements INutritionService {
	@Autowired
	RestTemplate restTemplate;

	private static final String X_APP_ID = "5222b785";
	private static final String X_APP_KEY = "da8d674bcee0ffebc72345f552270c3e";
	private static final String X_REMOTE_USER_ID = "ahmiatnutritionix";
	private static final String NUTRITION_SERVICE_URL = "https://trackapi.nutritionix.com/v2/natural/nutrients";
	
	public Ingredient calculateNutritionalValue(String ingr) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-app-id", X_APP_ID);
		headers.set("x-app-key", X_APP_KEY);
		headers.set("x-remote-user-id", X_REMOTE_USER_ID);

		NutritionRequest req = new NutritionRequest(ingr, "US/Eastern");
		HttpEntity<NutritionRequest> request = new HttpEntity<NutritionRequest>(req, headers);

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(NUTRITION_SERVICE_URL, request, String.class);
		String responseJson = responseEntity.getBody();

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = null;
		try {
			rootNode = objectMapper.readTree(responseJson);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Ingredient ingredient = new Ingredient();
		if (rootNode != null) {
			JsonNode foods = rootNode.path("foods");
			Iterator<JsonNode> elementsIterator = foods.elements();

			if (elementsIterator.hasNext()) {
				JsonNode element = elementsIterator.next();
				ingredient.setIngredientName(element.path("food_name").asText());
				ingredient.setProtein(element.path("nf_total_fat").asDouble());
				ingredient.setCarbohydrate(element.path("nf_total_carbohydrate").asDouble());
				ingredient.setFat(element.path("nf_total_fat").asDouble());
				ingredient.setTotalNutrition(calculateTotalNutrition(ingredient));
			}

		}

		return ingredient;
	}
	
	public double calculateTotalNutrition(Ingredient ingredient) {
		double total;
		total = ingredient.getFat()+ingredient.getCarbohydrate() + ingredient.getFat();
		return total;
	}
	
}
