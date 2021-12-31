package com.recipes.demo.dto;

import java.io.Serializable;
import java.util.List;

public class RecipeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7879658622225536204L;
	
	private long id;
	private String name;
	private String description;
	private String imagePath;
	private String recipeId;
	private List<IngredientsDTO> ingredients;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}

	public List<IngredientsDTO> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientsDTO> ingredients) {
		this.ingredients = ingredients;
	}

}
