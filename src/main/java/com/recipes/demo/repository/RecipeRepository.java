package com.recipes.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recipes.demo.entity.RecipeEntity;

@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntity, Long>{ 
	List<RecipeEntity> findAll();
	RecipeEntity findByName(String name);
}
