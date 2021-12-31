package com.recipes.demo.response;

import java.util.List;

public class RecipeRest {

	private String name;
	private String description;
	private String imagePath;
	private List<IngredientsRest> ingredients;

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

	public List<IngredientsRest> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientsRest> ingredients) {
		this.ingredients = ingredients;
	}

}
