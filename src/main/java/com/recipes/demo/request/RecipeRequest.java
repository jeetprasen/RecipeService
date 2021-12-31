package com.recipes.demo.request;

import java.io.Serializable;
import java.util.List;

import com.recipes.demo.response.IngredientsRest;

public class RecipeRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1025386655005728151L;
	
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
