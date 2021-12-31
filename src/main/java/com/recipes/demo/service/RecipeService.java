package com.recipes.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.recipes.demo.request.RecipeRequest;
import com.recipes.demo.response.RecipeRest;

public interface RecipeService extends UserDetailsService {
	List<RecipeRest> getRecipes();

	List<RecipeRest> createRecipe(List<RecipeRequest> recipeDetails);

	String deleteRecipes();
}