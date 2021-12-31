package com.recipes.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "recipes")
public class RecipeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6021316643379037947L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private String recipeId;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 50)
	private String description;

	@Column(nullable = false, length = 120)
	private String imagePath;

	@JsonManagedReference
	@OneToMany(mappedBy = "recipeDetails", cascade = CascadeType.ALL)
	private List<IngredientsEntity> ingredients;

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

	public List<IngredientsEntity> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientsEntity> ingredients) {
		this.ingredients = ingredients;
	}

	public String getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}
	
	

}
