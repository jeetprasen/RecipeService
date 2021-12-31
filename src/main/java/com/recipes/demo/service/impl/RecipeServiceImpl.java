package com.recipes.demo.service.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.recipes.demo.dto.IngredientsDTO;
import com.recipes.demo.dto.RecipeDTO;
import com.recipes.demo.dto.Utils;
import com.recipes.demo.entity.RecipeEntity;
import com.recipes.demo.repository.RecipeRepository;
import com.recipes.demo.request.RecipeRequest;
import com.recipes.demo.response.RecipeRest;
import com.recipes.demo.service.RecipeService;
import com.recipes.demo.transformer.service.RecipeTransformService;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	RecipeTransformService transformService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder; 

	@Autowired
	Utils utils;
	
	final Function<List<RecipeRequest>, List<RecipeDTO>> convertRequestToDTO = convertRequestToDTO();
	final Function<List<RecipeDTO>, List<RecipeDTO>> processRecipeDTO = processRecipeDTO();
	final Function<List<RecipeDTO>, List<RecipeEntity>> convertRequestToEntity = convertRequestToEntity();
	final Function<List<RecipeEntity>, List<RecipeDTO>> convertToDTO = convertToDTO();
	final Function<List<RecipeEntity>, List<RecipeEntity>> saveAll = saveAll();
	final Function<List<RecipeDTO>, List<RecipeRest>> processDtoAsReturn = processDtoAsReturn();

	@Override
	public List<RecipeRest> getRecipes() {

		List<RecipeEntity> recipes = recipeRepository.findAll();

		return convertToDTO.andThen(processDtoAsReturn).apply(recipes);
		
	}

	@Override
	public List<RecipeRest> createRecipe(List<RecipeRequest> recipereq) {

		
		return convertRequestToDTO
				.andThen(processRecipeDTO)
				.andThen(convertRequestToEntity)
				.andThen(saveAll)
				.andThen(convertToDTO)
				.andThen(processDtoAsReturn)
				.apply(recipereq);

	}
	
	@Override
	public String deleteRecipes() {
		long id = 5465;
		recipeRepository.deleteById(id);
		return "Deleted";
	}
	
	
	/**
	 * Functional Interface Implementations below
	 * 
	 */
	
	private Function<List<RecipeRequest>, List<RecipeDTO>> convertRequestToDTO() {
		Function<List<RecipeRequest>, List<RecipeDTO>> convertRequestToDTO = r -> r.stream()
				.map(transformService::recipeReqToDto).collect(Collectors.toList());
		return convertRequestToDTO;
	}
	
	private Function<List<RecipeDTO>, List<RecipeDTO>> processRecipeDTO() {
		Function<List<RecipeDTO>, List<RecipeDTO>> processRecipeDTO = r -> {
			r.forEach((recipe) -> {

				RecipeEntity storedRecipeDetails = recipeRepository.findByName(recipe.getName());

				if (storedRecipeDetails != null) {
					System.out.println("Object already found");
					recipeRepository.deleteById(storedRecipeDetails.getId());
				}
				for (int i = 0; i < recipe.getIngredients().size(); i++) {
					IngredientsDTO ing = recipe.getIngredients().get(i);
					ing.setRecipeDetails(recipe);
					ing.setIngredientId(utils.generateAddressId(5));
					recipe.getIngredients().set(i, ing);
				}
				recipe.setRecipeId(utils.generateUserId(5));
			});
			return r;
		};
		return processRecipeDTO;
	}
	
	private Function<List<RecipeDTO>, List<RecipeEntity>> convertRequestToEntity() {
		Function<List<RecipeDTO>, List<RecipeEntity>> convertRequestToEntity = a -> a.stream().map(transformService::recipeDtoToEntity).collect(Collectors.toList());
		return convertRequestToEntity;
	}
	
	private Function<List<RecipeEntity>, List<RecipeEntity>> saveAll() {
		Function<List<RecipeEntity>, List<RecipeEntity>> saveAll = s -> (List<RecipeEntity>) recipeRepository.saveAll(s);
		return saveAll;
	}
	
	private Function<List<RecipeEntity>, List<RecipeDTO>> convertToDTO() {
		Function<List<RecipeEntity>, List<RecipeDTO>> convertToDTO = a -> a.stream().map(transformService::recipeEntityToDTO).collect(Collectors.toList());
		return convertToDTO;
	}
	
	private Function<List<RecipeDTO>, List<RecipeRest>> processDtoAsReturn() {
		Function<List<RecipeDTO>, List<RecipeRest>> processDtoAsReturn = p -> p.stream().map(transformService::recipeDtoToRest).collect(Collectors.toList());
		return processDtoAsReturn;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
