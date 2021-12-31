package com.recipes.demo.transformer.service;

import com.recipes.demo.dto.RecipeDTO;
import com.recipes.demo.entity.RecipeEntity;
import com.recipes.demo.request.RecipeRequest;
import com.recipes.demo.response.RecipeRest;

public interface RecipeTransformService {

	RecipeDTO recipeEntityToDTO(RecipeEntity recipes);

	RecipeRest recipeDtoToRest(RecipeDTO recipes);

	RecipeEntity recipeDtoToEntity(RecipeDTO recipes);

	RecipeDTO recipeReqToDto(RecipeRequest recipes);
}
