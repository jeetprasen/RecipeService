package com.recipes.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ingredients")
public class IngredientsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4751673686873439257L;

	@Id
	@GeneratedValue
	@Column(nullable = false)
	private long id;

	@Column(nullable = false)
	private String ingredientId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String amount;

	@ManyToOne
	@JoinColumn(name = "recipes_id")
	@JsonBackReference
	private RecipeEntity recipeDetails;

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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public RecipeEntity getRecipeDetails() {
		return recipeDetails;
	}

	public void setRecipeDetails(RecipeEntity recipeDetails) {
		this.recipeDetails = recipeDetails;
	}

	public String getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(String ingredientId) {
		this.ingredientId = ingredientId;
	}

}
