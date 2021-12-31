package com.recipes.demo.transformer.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipes.demo.dto.RecipeDTO;
import com.recipes.demo.entity.RecipeEntity;
import com.recipes.demo.request.RecipeRequest;
import com.recipes.demo.response.RecipeRest;
import com.recipes.demo.transformer.service.RecipeTransformService;

@Service
public class RecipeTransformServiceImpl implements RecipeTransformService {

	@Autowired
	private ModelMapper modelMapper;

	/*
	 * @Override public List<RecipeRest>
	 * transformRecipeResponseListOfDtoToRest(List<RecipeDTO> recipes) {
	 * 
	 * List<RecipeRest> returnValue = new ArrayList<RecipeRest>();
	 * 
	 * ModelMapper modelMapper = new ModelMapper();
	 * modelMapper.typeMap(RecipeDTO.class, RecipeRest.class).addMappings(mapper ->
	 * { mapper.<String>map(RecipeDTO::getName, RecipeRest::setName);
	 * mapper.<String>map(RecipeDTO::getDescription, RecipeRest::setDescription);
	 * mapper.<String>map(RecipeDTO::getImagePath, RecipeRest::setImagePath);
	 * mapper.<List>map(RecipeDTO::getIngredients, RecipeRest::setIngredients); });
	 * 
	 * for (RecipeDTO recipeDTO : recipes) { RecipeRest recipe = new RecipeRest();
	 * 
	 * modelMapper.map(recipeDTO, recipe); returnValue.add(recipe); }
	 * 
	 * return returnValue; }
	 */

	@Override
	public RecipeRest recipeDtoToRest(RecipeDTO recipes) {

		return modelMapper.map(recipes, RecipeRest.class);
	}

	@Override
	public RecipeDTO recipeEntityToDTO(RecipeEntity recipes) {

		return modelMapper.map(recipes, RecipeDTO.class);
	}
	
	@Override
	public RecipeEntity recipeDtoToEntity(RecipeDTO recipes) {

		return modelMapper.map(recipes, RecipeEntity.class);
	}
	
	@Override
	public RecipeDTO recipeReqToDto(RecipeRequest recipes) {

		return modelMapper.map(recipes, RecipeDTO.class);
	}

}
