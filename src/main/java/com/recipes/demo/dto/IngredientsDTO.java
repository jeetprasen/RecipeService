package com.recipes.demo.dto;

import java.io.Serializable;

public class IngredientsDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3446449744257109634L;
	
	private long id;
	private String ingredientId;
	private String name;
	private String amount;

	private RecipeDTO recipeDetails;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RecipeDTO getRecipeDetails() {
		return recipeDetails;
	}

	public void setRecipeDetails(RecipeDTO recipeDetails) {
		this.recipeDetails = recipeDetails;
	}

	public String getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(String ingredientId) {
		this.ingredientId = ingredientId;
	}

}
